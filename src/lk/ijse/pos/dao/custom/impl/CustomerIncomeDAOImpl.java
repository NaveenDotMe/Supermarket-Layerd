package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.CustomerIncomeDAO;
import lk.ijse.pos.dao.custom.ItemMoveDAO;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import static javax.print.attribute.Size2DSyntax.MM;
import static javax.swing.text.html.HTML.Tag.DD;



public class CustomerIncomeDAOImpl implements CustomerIncomeDAO {


    private Time HH;
    private Date YYYY;

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
    public ArrayList<Orders> getCustomerIncome() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> sumTotal = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery
                ("SELECT custId, SUM(total) FROM orders GROUP BY custId ORDER BY total ASC");
        while (rst.next()) {
            sumTotal.add(new Orders(
                        "",
                    rst.getString("custId"),
                    YYYY,
                    HH,
                    rst.getInt("SUM(total)")
                    )
            );
        }
        return sumTotal;
    }
}
