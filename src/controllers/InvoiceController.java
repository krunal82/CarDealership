package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.Customer;
import util.Employee;
import util.Session;
import util.Vehicle;

import java.net.URL;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    @FXML private Label dateLabel;
    //@FXML private Label dateLabel;

    @FXML private Label cNameLabel;
    @FXML private Label cPhoneLabel;
    @FXML private Label cAddressLabel;
    @FXML private Label cCityLabel;

    @FXML private Label vUsedLabel;
    @FXML private Label vMakeLabel;
    @FXML private Label vModelLabel;
    @FXML private Label vYearLabel;
    @FXML private Label vColorLabel;
    @FXML private Label vPriceLabel;

    public InvoiceController() {
    }

    @FXML public void saveInvoice(ActionEvent event) {
        // do something
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Employee employee = Session.sessionUser;


        Customer customer = Session.selectedCustomer;
        cNameLabel.setText(customer.getFirstName() + " " + customer.getLastName());
        cPhoneLabel.setText(customer.getPhone());
        cAddressLabel.setText(customer.getAddress());
        cAddressLabel.setText(customer.getAddress());
        cCityLabel.setText(customer.getCity());

        Vehicle vehicle = Session.selectedVehicle;
        vUsedLabel.setText(vehicle.getUsed());
        vMakeLabel.setText(vehicle.getMake());
        vModelLabel.setText(vehicle.getModel());
        vYearLabel.setText(vehicle.getYear());
        vColorLabel.setText(vehicle.getColor());
        vPriceLabel.setText(vehicle.getPrice());

    }
}
