package com.cobaltdemo.demo.services;

import com.cobaltdemo.demo.model.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application.properties")
@Service
public class FrisbeePlayersMgtServices {
    Player playerRecord;

    List<Player> playersList = new ArrayList<Player>();
    ObjectMapper objectMapper = new ObjectMapper();

     @Value("${jsonFilePath}")
     private String jsonPath;



    //Method to read All players in the json file
    public List<Player> getAllPlayersdata() throws IOException {
        playersList = readFromJsonfile(jsonPath);
        return playersList;
    }

    //Method to search a players by id in the json file
    public Player getPlayerById(int id) throws IOException {

        Player playerRec = readFromJsonfile(jsonPath).stream()
                .filter(aPlayer -> id == (aPlayer.getPlayerId()))
                .findAny()
                .orElse(null);

        return playerRec;
    }

    //  a method to update an existing player
    public Player updatePlayerINfo(int id, String firstName, String lastName, String position) throws IOException {

        Player updatedPlayerRecord = readFromJsonfile(jsonPath).stream()
                .filter(aPlayer -> id == (aPlayer.getPlayerId()))
                .findAny()
                .orElse(null);

        updatedPlayerRecord.setPosition(position);
        updatedPlayerRecord.setFirstName(firstName);
        updatedPlayerRecord.setLastName(lastName);
        updatedPlayerRecord.setPlayerId(id);

        return updatedPlayerRecord;

    }

    // a method to register a new player
    public List<Player> registerNewPlayer(int id, String firstName, String lastName, String position) throws IOException {
        playersList.forEach(newRecord -> {
                    newRecord.setFirstName(firstName);
                    newRecord.setLastName(lastName);
                    newRecord.setPosition(position);
                }
        );

        return playersList;

    }


    // A method to read Json file from resources

    public List<Player> readFromJsonfile(String jsonPath) throws IOException {

        InputStream inputStream = new FileInputStream(new File(jsonPath));
        TypeReference<List<Player>> typeReference = new TypeReference<List<Player>>() {
        };

        return objectMapper.readValue(inputStream, typeReference);

    }



}