package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Orders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Orders dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO orders VALUES (?,?,?,?,?)",
                dto.getOrderId(), dto.getCustId(), dto.getOrderDate(), dto.getOrderTime(), dto.getTotal());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Orders search(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM orders WHERE orderId=?", orderId);
        rst.next();
        return new Orders(
                rst.getString("orderId"),
                rst.getString("custId"),
                Date.valueOf(rst.getString("orderDate")),
                Time.valueOf(rst.getString("orderTime")),
                rst.getDouble("total")
        );
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> allOrders = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM orders");
        while (rst.next()) {
            allOrders.add(new Orders(rst.getString("orderId"),
                            rst.getString("custId"),
                            rst.getDate("orderDate"),
                            rst.getTime("orderTime"),
                            rst.getDouble("total")
                    )
            );
        }
        return allOrders;
    }

    @Override
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT orderId FROM orders WHERE orderId=?", oid);
        return rst.next();
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1;");
        return rst.next() ? String.format("OD%03d", (Integer.parseInt(rst.getString("orderId").replace("OD", "")) + 1)) : "OD001";
    }
}
