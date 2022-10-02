package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ReportDTO;
import lk.ijse.pos.view.tdm.CustomerTM;
import lk.ijse.pos.view.tdm.OrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewReportController {

    private final lk.ijse.pos.bo.custom.OrderBO orderBO = (OrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ORDER);

    public AnchorPane root;
    public TableView<OrderTM>tblOrder;
    public JFXButton btnPrintOrder;

    public void initialize() throws SQLException, ClassNotFoundException {
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderTotal"));

        loadAllOrders();

    }

    private void loadAllOrders() throws SQLException, ClassNotFoundException {
        try{
            tblOrder.getItems().clear();
            ArrayList<ReportDTO> allOrders = orderBO.getAllOrders();
            for (ReportDTO orders : allOrders) {
                tblOrder.getItems().add(new OrderTM(
                        orders.getOrderId(), orders.getDate(), orders.getTime(), orders.getCustId(),
                        orders.getTotal()));
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

    public void printOrderDetails(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/lk/ijse/pos/view/report/OrderDetail.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getDbConnection().getConnection());
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
