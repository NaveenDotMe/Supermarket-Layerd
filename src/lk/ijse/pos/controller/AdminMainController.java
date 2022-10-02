package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainController {
    public ImageView imgViewReport;
    public ImageView imgItemMove;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgBusinessIncome;
    public ImageView imgItem;
    public ImageView imgCustomerIncome;
    public AnchorPane root;

    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgItem":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/ItemManage.fxml"));
                    break;
                case "imgCustomerIncome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/CustomerWiseIncome.fxml"));
                    break;
                case "imgItemMove":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/ItemMovable.fxml"));
                    break;
                case "imgBusinessIncome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/BusinessIncome.fxml"));
                    break;
                case "imgViewReport":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/ViewReport.fxml"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgCustomerIncome":
                    lblMenu.setText("Customer Wise Income");
                    lblDescription.setText("Click to View Customer wise Income");
                    break;
                case "imgItem":
                    lblMenu.setText("Manage Items");
                    lblDescription.setText("Click to add, edit, delete, search or view items");
                    break;
                case "imgItemMove":
                    lblMenu.setText("Item Movable");
                    lblDescription.setText("Click to view most movable items and least movable items");
                    break;
                case "imgBusinessIncome":
                    lblMenu.setText("Business Income");
                    lblDescription.setText("Click to view daily, monthly or annual income");
                    break;
                case "imgViewReport":
                    lblMenu.setText("Reports");
                    lblDescription.setText("Click to view all reports");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    public void navigateToHome(MouseEvent event) throws IOException {

        URL resource = this.getClass().getResource("/lk/ijse/pos/view/LogInPage.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setTitle("SUPERMARKET POS SYSTEM");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}
