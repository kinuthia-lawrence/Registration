package com.larrykin343.registration;

import com.almasb.fxgl.entity.action.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
            checkLoginCredentials();


        } else {
            invalidTextField.setText("Please enter your username and password!!!");
        }
    }

    //this method is called when the cancel button is clicked
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //TODO: check if the login credentials are correct
    //if the login credentials are correct, the user will be taken to the main application
    //if the login credentials are incorrect, the user will be prompted to enter the correct login credentials
    public void checkLoginCredentials() {
        DatabaseConn connectNow = new DatabaseConn();//Creating an instance of the DatabaseConn class
        Connection connectDB = connectNow.getConnection(); //this is the connection to the database
        //taking the user inputs
        String username = usernameTextField.getText();
        String password = enterPasswordField.getText();
        //this is the query to check if the login credentials are correct
        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + username + "' AND password = '" + password + "'";

        try {
            Statement statement = connectDB.createStatement();//
            ResultSet queryResult = statement.executeQuery(verifyLogin);//executing the query
            //the queryResult will return a 1 if the login credentials are correct and a 0 if the login credentials are incorrect
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
//                    invalidTextField.setText("Login Successful");
                    registerForm();
                } else {
                    invalidTextField.setText("Invalid Login Credentials. Please try again!!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        } finally {
            try {
                connectDB.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.getCause();
            }
        }
    }

    private void registerForm() {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("register.fxml"));
            Stage RegisterStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            RegisterStage.initStyle(StageStyle.UNDECORATED);
            RegisterStage.setScene(scene);
            RegisterStage.show();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}