package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.ItemMoveDAO;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemMoveDAOImpl implements ItemMoveDAO {


    @Override
    public boolean add(Orders orders) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
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
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<OrderDetails> getItemMovable() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> qtyCount = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery
                ("SELECT itemCode, SUM(orderQty) FROM order_detail GROUP BY itemCode ORDER BY orderQty ASC");
        while (rst.next()) {
            qtyCount.add(new OrderDetails(
                            "",
                            rst.getString("itemCode"),
                            rst.getInt("SUM(orderQty)"),
                            0
                    )
            );
        }
        return qtyCount;
    }
}
