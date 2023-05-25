package com.tutorial.guifx.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class TodoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String description;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt = LocalDateTime.now();
}
