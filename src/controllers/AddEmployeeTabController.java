package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import util.Employee;
import util.Init;
import util.Session;

import java.util.ArrayList;

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

            alphaController.getSearchEmployeeTabController().updateResultSet();
            alphaController.getSearchEmployeeTabController().displayResultSet();

            Session.alert("Employee Added!");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void init(AlphaController alphaController) {

        this.alphaController = alphaController;

        ArrayList<String> jobs = new ArrayList<>();
        jobs.add("Sales");
        jobs.add("Accountant");
        jobs.add("Manager");
        jobTitleCB.getItems().addAll(jobs);

    }

}
