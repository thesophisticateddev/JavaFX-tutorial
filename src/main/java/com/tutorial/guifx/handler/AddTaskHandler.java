package com.tutorial.guifx.handler;

import com.tutorial.guifx.events.AddTaskEvent;
import com.tutorial.guifx.services.TodoTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddTaskHandler {
    @Autowired
    private TodoTaskService todoTaskService;

    @EventListener
    public void addTask(AddTaskEvent event){
        log.info("Add task event triggered");
        todoTaskService.createTask(event.getName(), event.getDescription());
        log.info("Event successfully handled");
    }

}
