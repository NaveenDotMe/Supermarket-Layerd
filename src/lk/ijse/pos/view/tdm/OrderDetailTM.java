package lk.ijse.pos.view.tdm;

import java.math.BigDecimal;

public class OrderDetailTM {

    private String code;
    private String description;
    private int qty;
    private double discount;
    private double total;

    public OrderDetailTM() {
    }

    public OrderDetailTM(String code, String description, int qty, double discount, double total) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.discount = discount;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}
