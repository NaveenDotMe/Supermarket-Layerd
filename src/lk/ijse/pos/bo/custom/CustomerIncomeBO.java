package lk.ijse.pos.bo.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerIncomeBO extends SuperBO {
    ArrayList<Orders> getAllCustomerIncome() throws SQLException, ClassNotFoundException;
}
