package com.example.otoparkprojesi.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.otoparkprojesi.exception.VehicleNotFoundException;
import com.example.otoparkprojesi.model.DebtDetail;
import com.example.otoparkprojesi.model.DebtInfo;
import com.example.otoparkprojesi.model.PaymentResult;
import com.example.otoparkprojesi.model.Vehicle;
import com.example.otoparkprojesi.repository.VehicleRepository;


@Service

public class VehicleServiceImpl implements VehicleService {
    private static final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);


    private static final double HOURLY_RATE = 150.0;  // Saatlik ücret (TL)

    private final VehicleRepository vehicleRepository;
    private final EmailService emailService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, EmailService emailService) {
        this.vehicleRepository = vehicleRepository;
        this.emailService = emailService;
    }

    @Override
    public Vehicle addEntry(String plateNo, LocalDateTime entryTime) {
        log.info("Yeni giriş denemesi: {}", plateNo);
        List<Vehicle> vehicles = vehicleRepository.findByPlateNo(plateNo);

        boolean hasActiveEntry = vehicles != null && vehicles.stream()
                .anyMatch(v -> v.getExitTime() == null && "giris".equalsIgnoreCase(v.getStatus()));

        if (hasActiveEntry) {
            throw new RuntimeException("Bu Araç Çıkış Yapmadan Tekrar Giriş Yapamaz!");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNo(plateNo);
        vehicle.setEntryTime(entryTime != null ? entryTime : LocalDateTime.now());
        vehicle.setPaid(false);
        vehicle.setStatus("giris");
        vehicle.setOwnerName("Demo Kullanıcı");
        vehicle.setOwnerEmail("demo@example.com");
        log.debug("Entry time: {}", entryTime);

        return vehicleRepository.save(vehicle);
        
    }

    @Override
    public Vehicle makeExit(String plateNo, LocalDateTime exitTime) {
        List<Vehicle> vehicles = vehicleRepository.findByPlateNo(plateNo);
        if (vehicles == null || vehicles.isEmpty()) {
            throw new VehicleNotFoundException(plateNo);
        }

        Vehicle vehicle = vehicles.stream()
                .filter(v -> v.getExitTime() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Çıkış yapılmamış kayıt bulunamadı"));

        if (exitTime == null) {
            exitTime = LocalDateTime.now();
        }

        // ❌ Giriş zamanından önce çıkış yapılmış mı kontrol et
        if (exitTime.isBefore(vehicle.getEntryTime())) {
            throw new RuntimeException("Çıkış zamanı, giriş zamanından önce olamaz!");
        }

        vehicle.setExitTime(exitTime);

        long hours = Duration.between(vehicle.getEntryTime(), exitTime).toHours();
        if (hours == 0) hours = 1;

        double fee = hours * HOURLY_RATE;
        vehicle.setTotalFee(fee);
        vehicle.setStatus("çıkış");

        vehicleRepository.save(vehicle);

        return vehicle;
    }


    @Override
    public DebtInfo getDebt(String plateNo) {
        List<Vehicle> vehicles = vehicleRepository.findByPlateNo(plateNo);
        if (vehicles == null || vehicles.isEmpty()) {
            throw new VehicleNotFoundException(plateNo);
        }

        List<DebtDetail> debtDetails = vehicles.stream()
                .filter(v -> !v.isPaid() && v.getTotalFee() > 0)
                .map(v -> {
                    DebtDetail debtDetail = new DebtDetail();
                    debtDetail.setPlateNo(v.getPlateNo());
                    debtDetail.setDebtAmount(v.getTotalFee());
                    debtDetail.setEntryTime(v.getEntryTime() != null ? v.getEntryTime().toString() : null);
                    debtDetail.setExitTime(v.getExitTime() != null ? v.getExitTime().toString() : null);
                    return debtDetail;
                })
                .collect(Collectors.toList());


        double totalDebt = debtDetails.stream()
                .mapToDouble(DebtDetail::getDebtAmount)
                .sum();

        DebtInfo debtInfo = new DebtInfo();
        debtInfo.setPlateNo(plateNo);
        debtInfo.setDebtDetails(debtDetails);
        debtInfo.setTotalDebt(totalDebt);

        return debtInfo;
    }

    @Override
    public PaymentResult makePayment(String plateNo) {
        List<Vehicle> vehicles = vehicleRepository.findByPlateNo(plateNo);
        if (vehicles == null || vehicles.isEmpty()) {
            throw new VehicleNotFoundException(plateNo);
        }

        Vehicle vehicle = vehicles.stream()
                .filter(v -> !v.isPaid() && v.getTotalFee() > 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ödenecek borç bulunamadı"));

        vehicle.setPaid(true);
        vehicle.setStatus("ödendi");

        double paidAmount = vehicle.getTotalFee();
        vehicleRepository.save(vehicle);

        String subject = "Otopark Ödeme Bilgilendirmesi";
        String content = String.format(
                "Plaka No      : %s\n" +   // ✅ Plaka başa eklendi
                "Giriş saatiniz: %s\n" +
                "Çıkış saatiniz: %s\n" +
                "Toplam ücret  : %.2f TL\n" +
                "Durum         : ÖDENDİ\n\n" +
                "İyi günler dileriz.",
                vehicle.getPlateNo(),       // plaka bilgisi
                vehicle.getEntryTime(),
                vehicle.getExitTime(),
                paidAmount
        );
        emailService.sendEmail(vehicle.getOwnerEmail(), subject, content);

        PaymentResult result = new PaymentResult();
        result.setSuccess(true);
        result.setMessage("Ödeme başarıyla tamamlandı ve mail gönderildi.");
        result.setPaidAmount(paidAmount);

        return result;
    }

    @Override
    public PaymentResult makePayment(String plateNo, int debtId) {
        List<Vehicle> vehicles = vehicleRepository.findByPlateNo(plateNo);
        if (vehicles == null || vehicles.isEmpty()) {
            throw new VehicleNotFoundException(plateNo);
        }

        Vehicle vehicle = vehicles.stream()
                .filter(v -> !v.isPaid() && v.getTotalFee() > 0 && v.getId() == debtId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ödenecek borç bulunamadı"));

        vehicle.setPaid(true);
        vehicle.setStatus("ödendi");

        double paidAmount = vehicle.getTotalFee();
        vehicleRepository.save(vehicle);

        // ✅ Sade mail gönder
        String subject = "Otopark Ödeme Bilgilendirmesi";
        String content = String.format(
                "Giriş saatiniz : %s\n" +
                "Çıkış saatiniz: %s\n" +
                "Toplam ücret  : %.2f TL\n" +
                "Durum         : ÖDENDİ\n\n" +
                "İyi günler dileriz.",
                vehicle.getEntryTime(),
                vehicle.getExitTime(),
                paidAmount
        );
        emailService.sendEmail(vehicle.getOwnerEmail(), subject, content);

        PaymentResult result = new PaymentResult();
        result.setSuccess(true);
        result.setMessage("Ödeme başarıyla tamamlandı ve mail gönderildi.");
        result.setPaidAmount(paidAmount);

        return result;
    }


    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getEntryVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findByStatus("giris");
        if (vehicles == null || vehicles.isEmpty()) {
            throw new RuntimeException("Giriş yapan araç bulunamadı.");
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getExitVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findByStatus("çıkış");
        return vehicles != null ? vehicles : List.of();
    }
}
