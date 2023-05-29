package com.tutorial.guifx.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TaskPopUpController implements EventHandler<ActionEvent> {

    @Autowired
    private ConfigurableApplicationContext applicationContext;


    //    @Autowired
//    private ApplicationContext applicationContext;
    @Override
    public void handle(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            fxmlLoader.setLocation(getClass().getResource("../addTaskPopUp.fxml"));
            Parent root = fxmlLoader.load();
            stage.setTitle("Sample app");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
