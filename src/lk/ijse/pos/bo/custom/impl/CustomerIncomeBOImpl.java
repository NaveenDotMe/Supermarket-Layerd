package lk.ijse.pos.bo.custom.impl;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.pos.bo.custom.CustomerIncomeBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerIncomeDAO;
import lk.ijse.pos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerIncomeBOImpl implements CustomerIncomeBO{

    private final CustomerIncomeDAO customerIncomeDAO = (CustomerIncomeDAO) DAOFactory.getDAOFactory().getDAO
            (DAOFactory.DAOTypes.CUSTOMERINCOME);

    @Override
    public ArrayList<Orders> getAllCustomerIncome() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> allCustomerIncome = new ArrayList<>();
        ArrayList<Orders> all = customerIncomeDAO.getCustomerIncome();
        for (Orders order : all) {
            allCustomerIncome.add(new Orders(order.getOrderId(), order.getCustId(), order.getOrderDate(),
                    order.getOrderTime(), order.getTotal()));
        }
        return allCustomerIncome;
    }
}
