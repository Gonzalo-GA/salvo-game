package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String type;
    @ElementCollection
    private List<String> cells = new ArrayList<String>();

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "gameplayer_id")
    private GamePlayer gamePlayer;

    public Ship(){}

    public Ship(GamePlayer gamePlayer, String type, List<String>cells){
        this.gamePlayer=gamePlayer;
        this.type=type;
        this.cells=cells;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCells() {
        return cells;
    }

    public void setCells(List<String> cells) {
        this.cells = cells;
    }

    public Map<String,Object> shipsDTO(){
        Map<String,Object>Dto = new LinkedHashMap<>();
        Dto.put("type", this.getType());
        Dto.put("locations",this.getCells());
        return Dto;
    }
}
