package com.example.wineriesapi.Service.impl;

import com.example.wineriesapi.Model.Wineries;
import com.example.wineriesapi.Repository.WineRepository;
import com.example.wineriesapi.Service.WineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;

    public WineServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public List<Wineries> listWineries() {
        return wineRepository.findAll();
    }

    @Override
    public Wineries add() {
        Wineries wineries = new Wineries(41.235963, 22.7000651, "Vineyard","Vineyard Surface", "Nov Dojran");

        return wineRepository.save(wineries);
    }

    @Override
    public List<Wineries> bestWineries() {
        return wineRepository.findAll().stream()
                .filter(winery -> "Winery".equals(winery.getLanduse()))
                .collect(Collectors.toList());
    }
}
