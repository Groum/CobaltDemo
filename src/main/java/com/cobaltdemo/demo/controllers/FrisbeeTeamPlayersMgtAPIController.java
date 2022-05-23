package com.cobaltdemo.demo.controllers;

import com.cobaltdemo.demo.model.Player;
import com.cobaltdemo.demo.services.FrisbeePlayersMgtServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class FrisbeeTeamPlayersMgtAPIController {

    @Autowired
    FrisbeePlayersMgtServices playerService;

    @RequestMapping("/")
    public String home(){
        return "Hello Welcome to Players registration to Cobalt Frisbee Team ";
    }

    //End point to search a player by id
    @RequestMapping("/getplayer/{id}")
    public @ResponseBody
    Player Players(@PathVariable(value ="id") int id) throws IOException {
        return playerService.getPlayerById(id);

    }
       //Endpoint to get all players list
    @RequestMapping("/getPlayersData")
    public  @ResponseBody
    List<Player> getPlayersDetail() throws IOException {
        return playerService.getAllPlayersdata();
    }
       //Endpoint to update player
    @PutMapping(path = "/updatePlayers" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Player updatePlayers(@RequestBody Player player) throws IOException {
        return playerService.updatePlayerINfo(player.getPlayerId(),player.getFirstName(),player.getLastName(),
                player.getPosition());

    }
           //Endpoint to register a player
    @PostMapping(path ="/registerPlayers" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Player> Registerplayer(@RequestBody Player player) throws IOException {
        return playerService.registerNewPlayer(player.getPlayerId(),player.getFirstName(),player.getLastName(),
                player.getPosition());
    }


}
