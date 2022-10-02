package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.CustomerIncomeBO;
import lk.ijse.pos.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case ITEM:
                return new ItemBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ITEMMOVE:
                return new ItemMoveBOImpl();
            case CUSTOMERINCOME:
                return new CustomerIncomeBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        CUSTOMER, ITEM, ORDER, ITEMMOVE, CUSTOMERINCOME, LOGIN
    }
}
