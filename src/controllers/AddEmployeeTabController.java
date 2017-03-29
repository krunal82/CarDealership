package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import util.Employee;
import util.Init;
import util.Session;

public class AddEmployeeTabController implements Init {

    private AlphaController alphaController;

    @FXML private TextField fNameTF;
    @FXML private TextField lNameTF;
    @FXML private TextField phoneTF;
    @FXML private TextField emailTF;
    @FXML private TextField addressTF;
    @FXML private TextField cityTF;
    @FXML private TextField salaryTF;
    @FXML private DatePicker dateOfBirthDP;
    @FXML private ComboBox<String> jobTitleCB;

    @FXML public void save(ActionEvent event) {
        try {

            Employee employee = new Employee(fNameTF, lNameTF, phoneTF, emailTF, addressTF, cityTF, dateOfBirthDP, jobTitleCB, salaryTF);
            Employee.insertEntry(employee);
            Session.getInstance().reloadEmployees();
            Session.getInstance().alert("Employee Added!");

        } catch (Exception e) {
            Session.getInstance().alert(e.getMessage());
        }

    }

    @Override
    public void init(AlphaController alphaController) {

        this.alphaController = alphaController;
        jobTitleCB.getItems().addAll(Employee.getJobList());

    }

}
