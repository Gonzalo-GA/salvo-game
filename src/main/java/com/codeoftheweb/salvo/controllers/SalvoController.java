package com.codeoftheweb.salvo.controllers;

import com.codeoftheweb.salvo.models.Game;
import com.codeoftheweb.salvo.models.GamePlayer;
import com.codeoftheweb.salvo.models.Player;
import com.codeoftheweb.salvo.models.Salvo;
import com.codeoftheweb.salvo.repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.repositories.GamesRepository;
import com.codeoftheweb.salvo.models.Ship;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class SalvoController{

    @Autowired
    private GamesRepository gameRepo;

    @RequestMapping("/games")
    public Map<String,Object> getGames(Authentication authentication){
        Map<String,Object> Dto = new LinkedHashMap<>();
        if(this.isGuest(authentication)){
            Dto.put("player","guest");
        }else{
            Dto.put("player",getLoggedPlayer(authentication));
        }
        Dto.put("games",gameRepo.findAll().stream().map(Game::gamesDTO).collect(Collectors.toList()));
        return Dto;
    }

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @RequestMapping("/game_view/{id}")
    public Map<String,Object> getGameView(@PathVariable ("id") long id){
        GamePlayer gamePlayer = gamePlayerRepo.findById(id).orElse(null);
        return gamePlayer.GamePlayerDTO();
    }

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Map<String,Object> getLoggedPlayer(Authentication authentication){
        return playerRepository.findByUserName(authentication.getName()).playersDTO();
    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    @PostMapping("/players")
    private ResponseEntity<Map<String,Object>> createNewPlayer(@RequestParam String name,@RequestParam String username,@RequestParam String password){

        String[] arr = subStrName(name);

        if(username.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>(makeMap("error","Name in use"),HttpStatus.BAD_REQUEST);
        }
        Player player = playerRepository.findByUserName(username);
        if(player != null){
            return new ResponseEntity<>(makeMap("error","Username already exists"),HttpStatus.CONFLICT);
        }
        Player newPlayer = playerRepository.save(new Player(arr[0],arr[1],username, passwordEncoder.encode(password)));
        return new ResponseEntity<>(makeMap("id",newPlayer.getId()),HttpStatus.CREATED);

    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    private String[] subStrName(String name){
        String[] arr = name.split(" ");
        return arr;
    }

}