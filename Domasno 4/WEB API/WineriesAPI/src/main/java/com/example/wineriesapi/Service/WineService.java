package com.example.wineriesapi.Service;

import com.example.wineriesapi.Model.Wineries;

import java.util.List;

public interface WineService {

    List<Wineries> listWineries();
    Wineries add();
    List<Wineries> bestWineries ();

}
