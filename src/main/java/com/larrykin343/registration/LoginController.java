package com.larrykin343.registration;

import com.almasb.fxgl.entity.action.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//this is a controller class for the login-view.fxml file
public class LoginController {

    @FXML
    public Button cancelButton;//this is the cancel button

    //this method is called when the cancel button is clicked
    public  void cancelButtonOnAction(ActionEvent event) {
        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}