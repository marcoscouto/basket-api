package com.marcoscouto.basketapi.controller;

import com.marcoscouto.basketapi.client.TeamClient;
import com.marcoscouto.basketapi.controller.exception.ControllerException;
import com.marcoscouto.basketapi.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class TeamController {

    @Autowired
    TeamClient teamClient;

    @GetMapping(value = "/teams")
    public ResponseEntity getAll(){
        String url = "https://free-nba.p.rapidapi.com/teams";
        try {
            return ResponseEntity.ok().body(teamClient.getAllTeams(url));
        } catch (ControllerException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(500).body(new HashMap<>().put("error", "Please, contact support"));
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity getById(@PathVariable String id){
        String url = "https://free-nba.p.rapidapi.com/teams/" + id;
        try {
            Team team = teamClient.getTeamById(url);
            if(team == null){
                HashMap hm = new HashMap();
                hm.put("code", 404);
                hm.put("message", "Not Founded");
                return ResponseEntity.status(404).body(hm);
            }
            return ResponseEntity.ok().body(team);
        } catch (ControllerException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(500).body(new HashMap<>().put("error", "Please, contact support"));
    }

    @GetMapping(value = "/teams/city/{city}")
    public ResponseEntity getByCity(@PathVariable String city){
        String url = "https://free-nba.p.rapidapi.com/teams/";
        List<Team> teams = new ArrayList<>();
        try {
            teamClient.getAllTeams(url).forEach(x -> {
                    if(x.getCity().equalsIgnoreCase(city)) teams.add(x);
            });
            if(teams.isEmpty()) {
                HashMap hm = new HashMap();
                hm.put("code", 404);
                hm.put("message", "Not Founded");
                return ResponseEntity.status(404).body(hm);
            }
            return ResponseEntity.ok().body(teams);
        } catch (ControllerException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(500).body(new HashMap<>().put("error", "Please, contact support"));
    }

    @GetMapping(value = "/teams/name/{name}")
    public ResponseEntity getByName(@PathVariable String name){
        String url = "https://free-nba.p.rapidapi.com/teams/";
        List<Team> teams = new ArrayList<>();
        try {
            teamClient.getAllTeams(url).forEach(x -> {
                    if(x.getName().contains(name)) teams.add(x);
            });
            if(teams.isEmpty()) {
                HashMap hm = new HashMap();
                hm.put("code", 404);
                hm.put("message", "Not Founded");
                return ResponseEntity.status(404).body(hm);
            }
            return ResponseEntity.ok().body(teams);
        } catch (ControllerException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(500).body(new HashMap<>().put("error", "Please, contact support"));
    }

}
