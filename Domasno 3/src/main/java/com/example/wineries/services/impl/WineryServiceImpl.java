package com.example.wineries.services.impl;

import com.example.wineries.models.Wineries;
import com.example.wineries.services.WineryService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WineryServiceImpl implements WineryService {

    @Override
    public List<Wineries> getWineries(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/actual_wineries.csv"));
            String line;
            List<Wineries> wineriesList = new ArrayList<>();
            String header = br.readLine();
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
                    wineriesList.add(wineries);
                }
            }

            return wineriesList;

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Wineries> getALlWineries () {
        try {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/actual_wineries.csv"));
        String line;
        List<Wineries> wineriesList = new ArrayList<>();
        String header = br.readLine();
        while ((line = br.readLine()) != null){
            String [] data = line.split(",",-1);
                Wineries wineries = new Wineries();
                wineries.setId(Long.parseLong(data[0]));
                wineries.setLat(Double.parseDouble(data[1]));
                wineries.setLon(Double.parseDouble(data[2]));
                wineries.setLanduse(data[3]);
                wineries.setWineryName(data[4]);
                wineries.setAddress(data[5]);
                wineriesList.add(wineries);
        }

        return wineriesList;

    }
        catch (IOException e){
        e.printStackTrace();
    }
        return null;
    }



}
