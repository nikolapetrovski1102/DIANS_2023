package com.example.wineriesapi.config;

import com.example.wineriesapi.Model.Wineries;
import com.example.wineriesapi.Repository.WineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DataInitializer {

    private final WineRepository wineRepository;

    public DataInitializer(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @PostConstruct
    public void initData() {
        if(this.isEmpty()){
            this.deleteAllData();
            this.addInitialData();
        }
    }

    public void addInitialData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/actual_wineries.csv"));
            String line;
            while ((line = br.readLine()) != null){
                String [] data = line.split(",",-1);
                if (data[3].equals("Winery")){
                    Wineries wineries = new Wineries();
                    wineries.setId(Long.parseLong(data[0]));
                    wineries.setLat(Double.parseDouble(data[1]));
                    wineries.setLon(Double.parseDouble(data[2]));
                    wineries.setLanduse(data[3]);
                    wineries.setWineryName(data[4]);
                    wineries.setAddress(data[5]);
                    wineRepository.save(wineries);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return this.wineRepository.count() == 0;
    }

    public void deleteAllData() {
        this.wineRepository.deleteAll();
    }

}
