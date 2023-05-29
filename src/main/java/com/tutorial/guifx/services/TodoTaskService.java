package com.tutorial.guifx.services;

import com.tutorial.guifx.dto.TodoDto;
import com.tutorial.guifx.entities.TodoTask;
import com.tutorial.guifx.repository.TodoTaskRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoTaskService {

    @Autowired
    private TodoTaskRepository todoTaskRepository;

    private ModelMapper getModelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper;
    }

    public TodoDto createTask(String name, String description){
        TodoTask task = TodoTask.builder().name(name).description(description).createdAt(LocalDateTime.now()).build();
        ModelMapper mapper = getModelMapper();
        TodoTask entity = todoTaskRepository.save(task);
        return mapper.map(entity, TodoDto.class);
    }

    public List<TodoDto> findAllTasks(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        Page<TodoTask> currentPage =  todoTaskRepository.findAll(pageable);
        ModelMapper mapper = getModelMapper();
        return currentPage.getContent().stream().map(eachItem ->
                mapper.map(eachItem, TodoDto.class)).collect(Collectors.toList());
    }

}
