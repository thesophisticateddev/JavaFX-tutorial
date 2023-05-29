package com.tutorial.guifx.views;

import com.tutorial.guifx.commands.AddTaskCommand;
import com.tutorial.guifx.services.TodoTaskService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AddTaskController implements Initializable {
    @FXML
    public TextField nameField;
    @FXML
    public TextArea descriptionField;

    @FXML
    public Button addButton;
    @Autowired
    private ApplicationContext context;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

      addButton.setOnAction(this::createTaskCommand);
    }

    public void createTaskCommand(ActionEvent event){
        AddTaskCommand command = new AddTaskCommand(context);
        command.setName(nameField.getText());
        command.setDescription(descriptionField.getText());
        command.trigger();
    }
}
