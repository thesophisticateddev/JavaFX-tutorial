package com.tutorial.guifx;

import com.tutorial.guifx.views.AddTaskController;
import com.tutorial.guifx.views.TaskPopUpController;
import com.tutorial.guifx.services.ApplicationLogo;
import com.tutorial.guifx.services.TodoTaskFactory;
import com.tutorial.guifx.services.TodoTaskService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Service
@Slf4j
public class MainController implements Initializable {

    private static final int TOLERANCE_THRESHOLD = 0xCF;
    public Button addTask;
    @FXML
    public VBox todoList;
    @FXML
    public ImageView mainLogo;

    @Autowired
    private TodoTaskFactory todoTaskFactory;

//    @Autowired
//    private TaskPopUpController taskPopUpController;

    @Autowired
    private AddTaskController addTaskController;
    @FXML
    public void createTaskAction(ActionEvent event){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setControllerFactory(applicationContext::getBean);
            fxmlLoader.setLocation(getClass().getResource("addTaskPopUp.fxml"));
            fxmlLoader.setController(addTaskController);
            Parent root = fxmlLoader.load();
            stage.setTitle("Sample app");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void loadList() {

        List<VBox> listOfItems = todoTaskFactory.getTasks();
        if (CollectionUtils.isEmpty(listOfItems)) {
            VBox box = new VBox();
            Label label = new Label("No content");
            box.getChildren().add(label);
            box.setFillWidth(true);
            todoList.getChildren().add(box);

        } else {
            todoList.getChildren().addAll(listOfItems);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mainLogo.setImage(ApplicationLogo.getLogo());
//            addTask.setOnAction(new TaskPopUpController());
            loadList();
        } catch (Exception e) {
            log.error("Failed to initialize screen", e);
        }
    }
}