package lk.ijse.pos.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class OrderDTO implements Serializable {

    private String orderId;
    private String customerId;
    private String customerName;
    private Date orderDate;
    private Time orderTime;
    private double orderTotal;
    private List<OrderDetailDTO> orderDetail;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String customerId, String customerName, Date orderDate, Time orderTime,
                    double orderTotal, List<OrderDetailDTO> orderDetail) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderTotal = orderTotal;
        this.orderDetail = orderDetail;
    }

    public OrderDTO(String orderId, String customerId, Date orderDate, Time orderTime, List<OrderDetailDTO> orderDetails, double total) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderTotal = total;
        this.orderDetail = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<OrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", orderTotal=" + orderTotal +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
