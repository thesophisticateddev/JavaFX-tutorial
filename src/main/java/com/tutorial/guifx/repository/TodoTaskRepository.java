package com.tutorial.guifx.repository;

import com.tutorial.guifx.entities.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoTaskRepository extends JpaRepository<TodoTask,Long> {

}
