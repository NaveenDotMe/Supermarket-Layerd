package lk.ijse.pos.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class Orders {
    private String orderId;
    private String custId;
    private Date orderDate;
    private Time orderTime;
    private double total;

    public Orders() {
    }

    public Orders(String orderId, String custId, Date orderDate, Time orderTime, double total) {
        this.orderId = orderId;
        this.custId = custId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", custId='" + custId + '\'' +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", total=" + total +
                '}';
    }
}
