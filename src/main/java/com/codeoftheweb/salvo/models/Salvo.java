package com.codeoftheweb.salvo.models;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long ID;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "gameplayer_id")
    private GamePlayer gamePlayer;

    private long turn;
    @ElementCollection
    private List<String> location = new ArrayList<String>();

    public Salvo(){}

    public Salvo(GamePlayer gamePlayer, long turn, List<String>location){
        this.turn = turn;
        this.gamePlayer = gamePlayer;
        this.location = location;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public long getTurn() {
        return turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public Map<String,Object> salvoesDTO(){
        Map <String,Object>Dto = new LinkedHashMap<>();
        Dto.put("turn",this.getTurn());
        Dto.put("player",this.getGamePlayer().getPlayer().getId());
        Dto.put("locations",this.getLocation());
        return Dto;
    }
}
