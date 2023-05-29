package com.tutorial.guifx.services;

import com.tutorial.guifx.dto.TodoDto;
import com.tutorial.guifx.views.TodoCellController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoTaskFactory {
    @Autowired
    private TodoTaskService taskService;

    private VBox createBox(TodoDto task){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../todoCell.fxml"));
        try {
            VBox box = loader.load();
            TodoCellController cell = loader.getController();
            cell.setData(task);
            box.setFillWidth(true);
            return box;
        } catch (IOException e) {
            log.error("Exception occured", e);
        }
        return null;
    }

    public List<VBox> getTasks(){
        List<TodoDto> tasks = taskService.findAllTasks(0,100);
        return tasks.stream().map(this::createBox).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
