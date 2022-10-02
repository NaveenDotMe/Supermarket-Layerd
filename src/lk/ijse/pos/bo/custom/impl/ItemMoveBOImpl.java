package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemMoveBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemMoveDAO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemMoveBOImpl implements ItemMoveBO {

    private final ItemMoveDAO itemMoveDAO = (ItemMoveDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEMMOVE);

    @Override
    public ArrayList<OrderDetails> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> allOrderDetails = new ArrayList<>();
        ArrayList<OrderDetails> all = itemMoveDAO.getItemMovable();
        for (OrderDetails orderDetails : all) {
            allOrderDetails.add(new OrderDetails(orderDetails.getOrderId(), orderDetails.getItemCode(), orderDetails.getOrderQty(),
                    orderDetails.getDiscount()));
        }
        return allOrderDetails;
    }
}
