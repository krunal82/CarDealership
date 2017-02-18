package ui;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button login;
    @FXML private VBox root;

    @FXML
    public void login(ActionEvent event) throws IOException {

        //legit user & isAdmit=true
        if (username.getText().toLowerCase().equals("reed")) {
            loadResource(event, "alpha.fxml");

            //legit user & isAdmit=false
        } else if (username.getText().toLowerCase().equals("krunal")) {
            loadResource(event, "beta.fxml");

        } else {
            Main.alertUser(event, this, "Invalid Login Combo!", "login.fxml");
        }
    }

    private void loadResource(ActionEvent event, String resourcePath) throws IOException {
        Parent register_page = FXMLLoader.load(getClass().getResource(resourcePath));
        Scene register_scene = new Scene(register_page);
        Stage register_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        register_stage.hide();
        register_stage.setScene(register_scene);
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
