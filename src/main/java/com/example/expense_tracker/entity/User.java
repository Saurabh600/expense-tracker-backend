package com.example.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = {"emailId"})
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String emailId;

    @Column(nullable = false)
    private String password;
}
