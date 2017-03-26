package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import util.DataHandler;
import util.Employee;
import util.Init;
import util.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeSet;

public class SearchEmployeeTabController implements Init {

    private AlphaController alphaController;
    private ResultSet resultSet;

    @FXML private TitledPane tPane;
    @FXML private TitledPane employeeResultsTP;
    @FXML private TextField employeeID;
    @FXML private ListView<Employee> listView;
    @FXML private Button viewDetailsButton;

    void displayResultSet() {

        tPane.setText(String.format(
                "Search Employees - {EID = %s}",
                !employeeID.getText().isEmpty() ? employeeID.getText() : "Any"
        ));

        try {

            boolean hasResults = resultSet.next();
            if (hasResults) {

                listView.getItems().clear();
                TreeSet<Employee> employees = new TreeSet<>();

                int numberOfResults = 0;
                while (hasResults) {
                    Employee employee = new Employee(resultSet);
                    employees.add(employee);
                    numberOfResults++;
                    hasResults = resultSet.next();
                }

                employeeResultsTP.setText(String.format("Results - %d", numberOfResults));
                listView.getItems().addAll(employees);

            } else {
                System.out.println("DB empty!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML public void showAll(ActionEvent event) {

        try {

            // Clear old resultSet
            employeeID.clear();

            // Display all Employees
            String sql = "SELECT * FROM EMPLOYEES";
            Connection connection = DataHandler.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            displayResultSet();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML public void search(ActionEvent event) {
        tPane.setExpanded(false);

        try {

            final String EID = employeeID.getText();

            Connection connection = DataHandler.getConnection();

            if (!EID.isEmpty()) {

                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `EMPLOYEES` WHERE ID=?");
                preparedStatement.setString(1, EID);
                resultSet = preparedStatement.executeQuery();
                displayResultSet();

            } else {

                System.out.println("Empty search parameters!");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `EMPLOYEES`");
                resultSet = preparedStatement.executeQuery();
                displayResultSet();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML public void select(ActionEvent event) {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            Session.selectedEmployee = listView.getSelectionModel().getSelectedItem();
            viewDetailsButton.setDisable(false);
        }
    }

    @FXML public void viewDetails(ActionEvent event) throws IOException {

        if (Session.selectedEmployee != null) {
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader();
            newStage.setTitle("View Employee");

            fxmlLoader.setLocation(getClass().getResource("../views/EmployeeDetails.fxml"));
            Parent newResource = fxmlLoader.load();
            Scene newScene = new Scene(newResource);
            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.showAndWait();
        } else {
            System.out.println("Error: No Employee selected!");
        }

    }

    @Override
    public void init(AlphaController alphaController) {

        this.alphaController = alphaController;
        viewDetailsButton.setDisable(true);

        listView.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>() {
            @Override
            public ListCell<Employee> call(ListView<Employee> param) {
                return new ListCell<Employee>() {
                    @Override
                    protected void updateItem(Employee item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty && item != null) {
                            setText(item.toString());
                        } else {
                            setText("");
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        updateResultSet();
        displayResultSet();

    }

    void updateResultSet() {
        try {

            Connection connection = DataHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEES");
            resultSet = preparedStatement.executeQuery();
            employeeID.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
