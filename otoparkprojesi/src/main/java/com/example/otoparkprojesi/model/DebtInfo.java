package com.example.otoparkprojesi.model;

import java.util.List;

public class DebtInfo {
	  private String plateNo;
	    private List<DebtDetail> debtDetails;  // Bu field'ın ismi çok önemli
	    private double totalDebt;

    // Getters & Setters
    public String getPlateNo() { return plateNo; }
    public void setPlateNo(String plateNo) { this.plateNo = plateNo; }

    public List<DebtDetail> getDebtDetails() { return debtDetails; }
    public void setDebtDetails(List<DebtDetail> debtDetails) { this.debtDetails = debtDetails; }

    public double getTotalDebt() { return totalDebt; }
    public void setTotalDebt(double totalDebt) { this.totalDebt = totalDebt; }

    public boolean hasDebt() { return totalDebt > 0; }
}
