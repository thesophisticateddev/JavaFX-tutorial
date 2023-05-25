package com.tutorial.guifx.services;

import com.tutorial.guifx.repository.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoTaskService {

    @Autowired
    private TodoTaskRepository todoTaskRepository;
}
