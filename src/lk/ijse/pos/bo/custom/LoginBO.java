package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.entity.Login;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    Login ifUserExists(String userName, String password) throws SQLException, ClassNotFoundException;
}
