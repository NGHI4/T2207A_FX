package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CreateClassController {

    public void gotoClassList(ActionEvent actionEvent) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/classes/list.fxml"));
            HomeController.rootStage.setScene(new Scene(root, 600, 400));
        }

}