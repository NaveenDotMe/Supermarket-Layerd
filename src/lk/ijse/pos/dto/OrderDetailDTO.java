package lk.ijse.pos.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetailDTO implements Serializable {
    private String orderId;
    private String itemCode;
    private double discount;
    private int qty;


    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderId, String itemCode, double discount, int qty) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.discount = discount;
        this.qty = qty;
    }

    public OrderDetailDTO(String orderId, String code, int qty, double discount) {
        this.orderId = orderId;
        this.itemCode = code;
        this.discount = discount;
        this.qty = qty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", discount=" + discount +
                ", qty=" + qty +
                '}';
    }
}
