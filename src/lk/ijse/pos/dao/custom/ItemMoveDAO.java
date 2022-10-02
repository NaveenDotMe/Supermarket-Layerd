package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemMoveDAO extends CrudDAO<Orders, String> {
    ArrayList<OrderDetails> getItemMovable() throws SQLException, ClassNotFoundException;
}
