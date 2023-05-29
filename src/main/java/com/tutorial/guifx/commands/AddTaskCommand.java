package com.tutorial.guifx.commands;

import com.tutorial.guifx.events.AddTaskEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Setter
@Getter
public class AddTaskCommand{
    @Autowired
    private ApplicationContext context;

    private String name;
    private String description;

    public AddTaskCommand(ApplicationContext context) {
        this.context = context;

    }

    public void trigger(){
        Platform.runLater(() -> {
            context.publishEvent(new AddTaskEvent(this.name, this.description));
        });
    }


}
