package com.example.wineriesapi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue
    private Long Id;
    private String Username;
    private String Name;
    private String Surname;
    private String Email;
    private String Password;

}