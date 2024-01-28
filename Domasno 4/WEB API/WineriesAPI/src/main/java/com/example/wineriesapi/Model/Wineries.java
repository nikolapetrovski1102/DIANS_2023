package com.example.wineriesapi.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Wineries")
public class Wineries{
    @Id
    @GeneratedValue
    private Long id;
    private double lat;
    private double lon;
    private String wineryName;
    private String address;
    private String landuse;

    public Wineries() {
    }

    public Wineries(double lat, double lon, String wineryName, String address, String landuse) {
        this.lat = lat;
        this.lon = lon;
        this.wineryName = wineryName;
        this.address = address;
        this.landuse = landuse;
    }



}