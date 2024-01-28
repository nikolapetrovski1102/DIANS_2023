package com.example.wineriesapi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
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

    public Users(String username, String name, String surname, String email, String password) {
        Username = username;
        Name = name;
        Surname = surname;
        Email = email;
        Password = password;
    }
}