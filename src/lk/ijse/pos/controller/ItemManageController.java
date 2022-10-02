package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.view.tdm.ItemTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemManageController {
    public AnchorPane root;
    public TableView<ItemTM>tblItems;
    public JFXTextField txtItemCode;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemQtyOnHand;
    public JFXButton btnAddNewItem;
    public JFXComboBox cmbItemSize;
    public JFXButton btnSave;
    public JFXButton btnDelete;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    public void initialize(){

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("size"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        initUI();

        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtItemCode.setText(newValue.getItemCode());
                txtItemDescription.setText(newValue.getDescription());
                cmbItemSize.setValue(newValue.getSize());
                txtItemUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
                txtItemQtyOnHand.setText(newValue.getQtyOnHand() + "");
                txtItemCode.setDisable(false);
                txtItemDescription.setDisable(false);
                cmbItemSize.setDisable(false);
                txtItemUnitPrice.setDisable(false);
                txtItemQtyOnHand.setDisable(false);
            }
        });

        txtItemQtyOnHand.setOnAction(event -> btnSave.fire());
        loadAllItems();

        cmbItemSize.getItems().addAll(
                "Small", "Medium", "Large", "Bottle"
        );
        cmbItemSize.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });

    }

    private void loadAllItems() {
        tblItems.getItems().clear();
        try {
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            for (ItemDTO item : allItems) {
                tblItems.getItems().add(new ItemTM(item.getItemCode(), item.getDescription(), item.getPackSize(),
                        item.getUnitPrice(), item.getQtyOnHand()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtItemCode.clear();
        txtItemDescription.clear();
        cmbItemSize.getSelectionModel().clearSelection();
        txtItemUnitPrice.clear();
        txtItemQtyOnHand.clear();
        txtItemCode.setDisable(true);
        txtItemDescription.setDisable(true);
        cmbItemSize.setDisable(true);
        txtItemUnitPrice.setDisable(true);
        txtItemQtyOnHand.setDisable(true);
        txtItemCode.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
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

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtItemCode.setDisable(false);
        txtItemDescription.setDisable(false);
        cmbItemSize.setDisable(false);
        txtItemUnitPrice.setDisable(false);
        txtItemQtyOnHand.setDisable(false);
        txtItemCode.clear();
        txtItemCode.setText(generateNewId());
        txtItemDescription.clear();
        cmbItemSize.getSelectionModel().clearSelection();
        txtItemUnitPrice.clear();
        txtItemQtyOnHand.clear();
        txtItemDescription.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblItems.getSelectionModel().clearSelection();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        String description = txtItemDescription.getText();
        String size = cmbItemSize.getValue().toString();
        int qtyOnHand = Integer.parseInt(txtItemQtyOnHand.getText());
        BigDecimal unitPrice = new BigDecimal(txtItemUnitPrice.getText()).setScale(2);

        if (!description.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtItemDescription.requestFocus();
            return;
        } else if (!txtItemUnitPrice.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid unit price").show();
            txtItemUnitPrice.requestFocus();
            return;
        } else if (!txtItemQtyOnHand.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty on hand").show();
            txtItemQtyOnHand.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(itemCode)) {
                    new Alert(Alert.AlertType.ERROR, itemCode + " already exists").show();
                }

                ItemDTO dto = new ItemDTO(itemCode, description, size, unitPrice, qtyOnHand);
                itemBO.addItem(dto);

                tblItems.getItems().add(new ItemTM(itemCode, description, size, unitPrice, qtyOnHand));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!existItem(itemCode)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + itemCode).show();
                }

                ItemDTO dto = new ItemDTO(itemCode, description, size, unitPrice, qtyOnHand);
                itemBO.updateItem(dto);

                ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();

                selectedItem.setDescription(description);
                selectedItem.setSize(size);
                selectedItem.setUnitPrice(unitPrice);
                selectedItem.setQtyOnHand(qtyOnHand);

                tblItems.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewItem.fire();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

        String itemCode = tblItems.getSelectionModel().getSelectedItem().getItemCode();
        try {
            if (!existItem(itemCode)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + itemCode).show();
            }

            itemBO.deleteItem(itemCode);
            tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
            tblItems.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + itemCode).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean existItem(String itemCode) throws SQLException, ClassNotFoundException {
        return itemBO.ifItemExist(itemCode);
    }

    private String generateNewId() {
        try {
            return itemBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I001";
    }
}
