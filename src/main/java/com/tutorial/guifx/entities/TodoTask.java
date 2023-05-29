package com.tutorial.guifx.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "is_deleted")
    @Where(clause = "is_deleted = false")
    private Boolean isDeleted = false;
}
