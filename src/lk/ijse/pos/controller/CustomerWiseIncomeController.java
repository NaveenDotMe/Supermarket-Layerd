package lk.ijse.pos.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.CustomerIncomeBO;
import lk.ijse.pos.bo.custom.ItemMoveBO;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;
import lk.ijse.pos.view.tdm.IncomeTM;
import lk.ijse.pos.view.tdm.ItemMoveTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerWiseIncomeController {
    public AnchorPane root;
    public TableView <IncomeTM>tblCustomerWiseIncome;

    private final CustomerIncomeBO customerIncomeBO = (CustomerIncomeBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMERINCOME);

    public void initialize() {

        tblCustomerWiseIncome.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("custId"));
        tblCustomerWiseIncome.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("total"));

        loadAllCustomerIncome();
    }

    private void loadAllCustomerIncome() {
        tblCustomerWiseIncome.getItems().clear();
        try {
            ArrayList<Orders> allIncome = customerIncomeBO.getAllCustomerIncome();
            for (Orders incomeDTO : allIncome) {
                tblCustomerWiseIncome.getItems().add(new IncomeTM(
                        incomeDTO.getCustId(), incomeDTO.getTotal()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void navigateToBack(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/pos/view/AdminMain.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("ADMIN MAIN VIEW");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}
