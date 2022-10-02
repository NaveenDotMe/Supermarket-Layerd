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
import lk.ijse.pos.bo.custom.ItemMoveBO;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.view.tdm.ItemMoveTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemMovableController {
    public AnchorPane root;
    public TableView<ItemMoveTM>tblItemMovables;

    private final ItemMoveBO itemMoveBO = (ItemMoveBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEMMOVE);

    public void initialize() {

        tblItemMovables.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItemMovables.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderQty"));

        loadAllItemMove();
    }

    private void loadAllItemMove() {
        tblItemMovables.getItems().clear();
        try {
            ArrayList<OrderDetails> allItemsCount = itemMoveBO.getAllOrderDetails();
            for (OrderDetails customDTO : allItemsCount) {
                tblItemMovables.getItems().add(new ItemMoveTM(
                        customDTO.getItemCode(), customDTO.getOrderQty()));
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
