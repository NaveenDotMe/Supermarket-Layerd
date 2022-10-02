package lk.ijse.pos.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.LoginBO;
import lk.ijse.pos.dto.LoginDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LogInPageController{

    private final LoginBO loginBO = (LoginBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.LOGIN);

    public AnchorPane mainLayer;
    public JFXTextField txtUserName;
    public JFXPasswordField pswPassword;
    public JFXButton btnLogIn;

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        String userName = txtUserName.getText();
        String password = pswPassword.getText();

        LoginDTO loginDTO = new LoginDTO(userName, password);
        loginBO.ifUserExists(userName, password);

        if (loginDTO.getUserName().equals("Naveen") && loginDTO.getPassword().equals("1234")){
            Stage logStage = (Stage) btnLogIn.getScene().getWindow();
            logStage.close();

            URL resource = this.getClass().getResource("/lk/ijse/pos/view/AdminMain.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setTitle("ADMIN MAIN VIEW");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();

        }else if(loginDTO.getUserName().equals("Pabhath") && loginDTO.getPassword().equals("1234")){
            Stage logStage = (Stage) btnLogIn.getScene().getWindow();
            logStage.close();

            URL resource = this.getClass().getResource("/lk/ijse/pos/view/CashierMain.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setTitle("CASHIER MAIN VIEW");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        }else
            new Alert(Alert.AlertType.WARNING, "Invalid User Name or Password").show();

        }

}
