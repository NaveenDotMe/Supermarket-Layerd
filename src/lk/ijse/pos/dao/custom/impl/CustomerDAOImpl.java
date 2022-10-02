package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO customer VALUES (?,?,?,?,?,?,?)",
                dto.getCustId(), dto.getCustTitle(), dto.getCustName(), dto.getCustAddress(), dto.getCity(),
                dto.getProvince(), dto.getPostalCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM customer WHERE custId=?", id);
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(
                "UPDATE customer SET custTitle=?, custName=?, custAddress=?, city=?, province=?, postalCode=? WHERE custId=?",
                dto.getCustTitle(), dto.getCustName(), dto.getCustAddress(), dto.getCity(), dto.getProvince(),
                dto.getPostalCode(), dto.getCustId());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer WHERE custId=?", id);
        rst.next();
        return new Customer(id, rst.getString("custTitle"),
                rst.getString("custName"),
                rst.getString("custAddress"),
                rst.getString("city"),
                rst.getString("province"),
                rst.getString("postalCode")
        );
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer");
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString("custId"),
                    rst.getString("custTitle"),
                    rst.getString("custName"),
                    rst.getString("custAddress"),
                    rst.getString("city"),
                    rst.getString("province"),
                    rst.getString("postalCode")
                    )
            );
        }
        return allCustomers;
    }

    @Override
    public boolean ifCustomerExist(String custId) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT custId FROM customer WHERE custId=?", custId).next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT custId FROM customer ORDER BY custId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("custId");
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C%03d", newCustomerId);
        } else {
            return "C001";
        }
    }
}
