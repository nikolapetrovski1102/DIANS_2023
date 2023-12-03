package com.example.wineries.services;

import com.example.wineries.models.Wineries;

import java.util.List;

public interface WineryService {
    List<Wineries> getWineries();
    List<Wineries> getALlWineries();
}
