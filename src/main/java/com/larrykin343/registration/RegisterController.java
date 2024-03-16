package com.larrykin343.registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public Button closeButton;
    @FXML
    public Button registerButton;
    @FXML
    public ImageView shieldImageView;
    @FXML
    public Label registrationSuccessLabel;
    @FXML
    public Label confirmPasswordLabel;
    @FXML
    public PasswordField enterPasswordField;
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public TextField firstnameTextField;
    @FXML
    public TextField lastnameTextField;


    public void initialize(URL url, ResourceBundle resourceBundle) {//this method is called when the register-view.fxml file is loaded
        File ShieldFile = new File("src/main/resources/com/larrykin343/registration/shield.png");
        Image shieldImage = new Image(ShieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }
    public void registerButtonOnAction(ActionEvent event){//this method is called when the register button is clicked
       if(!firstnameTextField.getText().isBlank() && !lastnameTextField.getText().isBlank() &&
               !usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()
               && !confirmPasswordField.getText().isBlank()){
           registerUser();
       }else{
              registrationSuccessLabel.setText("Please enter all the details!!!");
       }

    }
    public void closeButtonOnAction(ActionEvent event){//this method is called when the close button is clicked
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void registerUser() {
        if(enterPasswordField.getText().equals(confirmPasswordField.getText())){
            confirmPasswordLabel.setText("");
            registrationSuccessLabel.setText("User has been registered successfully!!!");
            DatabaseConn connectNow = new DatabaseConn();
            Connection connectDB = connectNow.getConnection();

            String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();
            String username = usernameTextField.getText();
            String password = enterPasswordField.getText();
            String insertFields = "INSERT INTO user_account(firstname,lastname,username,password) VALUES('";
            String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
            String insertToRegister = insertFields + insertValues;
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                registrationSuccessLabel.setText("User has been registered successfully!!!");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            confirmPasswordLabel.setText("Password does not match!!!");
        }
    }
}
