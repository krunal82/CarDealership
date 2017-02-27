package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.DataHandler;
import util.Init;
import util.Session;

public class SessionTabController implements Init {

    private AlphaController alphaController;

    @FXML private Label employeeLabel;
    @FXML private Label customerLabel;
    @FXML private Label vehicleLabel;
    @FXML private Button logoutButton;
    @FXML private Button createInvoiceButton;


    private void updateSessionInfo() {

        boolean allSet = true;

        if (Session.employee != null) {
            employeeLabel.setText(Session.employee.getRow());
        } else {
            allSet = false;
        }

        if (Session.customer != null) {
            customerLabel.setText(Session.customer.getRow());
        } else {
            allSet = false;
        }

        if (Session.vehicle != null) {
            vehicleLabel.setText(Session.vehicle.getRow());
        } else {
            allSet = false;
        }

        if (allSet) {
            createInvoiceButton.setDisable(false);
        } else {
            createInvoiceButton.setDisable(true);
        }

    }

    @FXML public void createInvoice(ActionEvent event) throws Exception {
        Parent register_page = FXMLLoader.load(getClass().getResource("../views/Invoice.fxml"));
        Scene register_scene = new Scene(register_page);
        Stage register_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        register_stage.hide();
        register_stage.setScene(register_scene);
        register_stage.show();
    }

    @FXML public void hoverIn(MouseEvent event) {
        logoutButton.setStyle("-fx-background-color:red;-fx-text-fill:white");
    }
    @FXML public void hoverOut(MouseEvent event) {
        logoutButton.setStyle("-fx-background-color:crimson;-fx-text-fill:white");
    }
    @FXML public void logout(ActionEvent event) {
        try {
            DataHandler.killConnection();
            Parent register_page = FXMLLoader.load(getClass().getResource("../Login.fxml"));
            Scene register_scene = new Scene(register_page);
            Stage register_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            register_stage.hide();
            register_stage.setScene(register_scene);
            register_stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void init(AlphaController alphaController) {

        this.alphaController = alphaController;
        updateSessionInfo();

        Tab tab = alphaController.getUserSessionInfoTab();
        tab.setOnSelectionChanged(event -> {
            updateSessionInfo();
        });

    }
}
