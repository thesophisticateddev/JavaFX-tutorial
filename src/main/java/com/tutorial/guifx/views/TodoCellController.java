package com.tutorial.guifx.views;

import com.tutorial.guifx.dto.TodoDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class TodoCellController implements Initializable {
    @FXML
    private Label description;

    @FXML
    private Label name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(TodoDto dt){
        name.setText(dt.getName());
        description.setText(dt.getDescription());
    }
}
