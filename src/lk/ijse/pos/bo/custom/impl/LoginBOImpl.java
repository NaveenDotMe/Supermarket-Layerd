package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.LoginBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.LoginDAO;
import lk.ijse.pos.entity.Login;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    private final LoginDAO loginDAO = (LoginDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public Login ifUserExists(String userName, String password) throws SQLException, ClassNotFoundException {
        return loginDAO.userSearch(userName, password);
    }
}
