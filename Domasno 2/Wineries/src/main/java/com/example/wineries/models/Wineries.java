package com.example.wineries.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Wineries")
public class Wineries{
    @Id
    private Long id;
    private double lat;
    private double lon;
    private String wineryName;
    private String address;
    private String landuse;
}