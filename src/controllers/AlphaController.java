package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import util.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class AlphaController implements Initializable {

    @FXML private AddCustomerTabController addCustomerTabController;
    @FXML private AddVehicleTabController addVehicleTabController;

    @FXML private SearchCustomerTabController searchCustomerTabController;
    @FXML private SearchVehicleTabController searchVehicleTabController;

    @FXML private AddEmployeeTabController addEmployeeTabController;
    @FXML private SearchEmployeeTabController searchEmployeeTabController;

    @FXML private SessionTabController sessionTabController;

    @FXML private Tab userSessionInfoTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userSessionInfoTab.setText(Session.sessionUser.getFirstName() + " " + Session.sessionUser.getLastName());

        addEmployeeTabController.init(this);
        addVehicleTabController.init(this);
        searchCustomerTabController.init(this);
        searchVehicleTabController.init(this);
        searchEmployeeTabController.init(this);
        sessionTabController.init(this);
    }

    public Tab getUserSessionInfoTab() {
        return userSessionInfoTab;
    }
}
