package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemMoveBO extends SuperBO {
    ArrayList<OrderDetails> getAllOrderDetails() throws SQLException, ClassNotFoundException;
}
