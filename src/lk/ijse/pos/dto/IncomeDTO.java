package lk.ijse.pos.dto;

import java.io.Serializable;

public class IncomeDTO implements Serializable {
    private String custId;
    private double total;

    public IncomeDTO() {
    }

    public IncomeDTO(String custId, double total) {
        this.custId = custId;
        this.total = total;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "IncomeDTO{" +
                "custId='" + custId + '\'' +
                ", total=" + total +
                '}';
    }
}
