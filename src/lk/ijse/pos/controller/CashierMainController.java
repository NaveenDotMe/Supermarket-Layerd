package lk.ijse.pos.controller;

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

public class CashierMainController {
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgCustomer;
    public ImageView imgOrders;
    public ImageView imgPayment;
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
                case "imgCustomer":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/CustomerManage.fxml"));
                    break;
                case "imgOrders":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/ManageOrder.fxml"));
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

    public void playMouseEnterAnimation(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgCustomer":
                    lblMenu.setText("Manage Customers");
                    lblDescription.setText("Click to add, edit, delete, search or view customers");
                    break;
                case "imgOrders":
                    lblMenu.setText("Place Orders");
                    lblDescription.setText("Click here if you want to place a new order");
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
        primaryStage.setScene(scene);
        primaryStage.setTitle("SUPERMARKET POS SYSTEM");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}
