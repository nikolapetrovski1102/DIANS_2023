package com.example.wineries.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    private Long Id;
    private String Username;
    private String Name;
    private String Surname;
    private String Email;
    private String Password;

}
