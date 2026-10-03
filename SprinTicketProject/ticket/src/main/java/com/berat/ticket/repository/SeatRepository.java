package com.berat.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.berat.ticket.entity.PhysicalSeatLayout;
import com.berat.ticket.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    // SeatLayout'a göre koltukları getir
    List<Seat> findBySeatLayout(PhysicalSeatLayout seatLayout);

    // Stored Function ile boş koltukları getir
    @Query(value = "SELECT * FROM dbo.GetFreeSeatsForEvent(:eventId)", nativeQuery = true)
    List<Object[]> getFreeSeatsForEvent(@Param("eventId") Long eventId);
    
}
