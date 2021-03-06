package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DataHandler;
import util.Employee;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button login;
    @FXML private VBox root;

    @FXML
    public void login(ActionEvent event) {

        try {

            Connection connection = DataHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `USERS` WHERE USERNAME = ? AND PASSWORD = ?");
            preparedStatement.setString(1, username.getText());
            preparedStatement.setString(2, password.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean hasResults = resultSet.next();

            if (hasResults) {

                String employeeID = resultSet.getString(1);
                preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEES WHERE ID = ?");
                preparedStatement.setString(1, employeeID);
                resultSet = preparedStatement.executeQuery();
                hasResults = resultSet.next();

                if (hasResults) {

                    Session.getInstance().sessionUser = new Employee(resultSet);
                    loadResource(event, "../views/Alpha.fxml");

                } else {
                    Session.getInstance().alert("Invalid login combo!");
                }

            } else {
                Session.getInstance().alert("Invalid login combo!");
            }

        } catch (Exception e) {
            Session.getInstance().alert(e.getMessage());
        }

    }

    private void loadResource(ActionEvent event, String resourcePath) throws IOException {
        Parent register_page = FXMLLoader.load(getClass().getResource(resourcePath));
        Scene register_scene = new Scene(register_page);
        Stage register_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        register_stage.hide();
        register_stage.setScene(register_scene);
        register_stage.setResizable(true);
        register_stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    login.fire();
                    break;
            }
        });
    }
}
