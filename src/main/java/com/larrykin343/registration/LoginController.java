package com.larrykin343.registration;

import com.almasb.fxgl.entity.action.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//this is a controller class for the login-view.fxml file
public class LoginController {

    @FXML
    public Button cancelButton;//this is the cancel button
    @FXML
    public Button loginButton;//this is the login button
    @FXML
    public Label invalidTextField;//this is the label that will display the invalid login message
    @FXML
    public TextField usernameTextField;//this is the textfield for the username
    @FXML
    public PasswordField enterPasswordField;//this is the Passwordfield for the password

    //this method is called when the login button is clicked
    public void loginButtonOnAction(ActionEvent event) {
        if (true) {
            if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
                //TODO: check if the login credentials are correct
                //if the login credentials are correct, the user will be taken to the main application
                //if the login credentials are incorrect, the user will be prompted to enter the correct login credentials
                Stage stage =(Stage) loginButton.getScene().getWindow();
                stage.close();
            } else {
                invalidTextField.setText("Please enter your username and password!!!");
            }
        } else {

            invalidTextField.setText("Invalid Login Credentials. Please try again!!!");
        }
    }

    //this method is called when the cancel button is clicked
    public  void cancelButtonOnAction(ActionEvent event) {
        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}