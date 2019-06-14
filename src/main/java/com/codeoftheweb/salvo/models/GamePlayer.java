package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class GamePlayer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long ID;
    private Date joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Ship> ship = new HashSet<>();

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Salvo> salvo = new HashSet<>();

    //Game game = new Game();
    //Player player = new Player();
    //private long gameID = game.getId();
    //private long playerID = player.getId();

    public GamePlayer(){}

    public GamePlayer(Player player, Game game){
        this.joinDate = new Date();
        this.player = player;
        this.game = game;
    }

    public long getID() {
        return ID;
    }

    public Player getPlayer() {
        return player;
    }

    //public Map<String,Object> getPlayerDTO() {
    //    return ;
    //}

    public void setPlayer(Player playerID) {
        this.player = playerID;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game gameID) {
        this.game = gameID;
    }

    public void setShip(Ship ship){
        ship.setGamePlayer(this);
        this.ship.add(ship);
    }

    public Set<Ship> getShip() {
        return ship;
    }

    public Set<Salvo> getSalvo() {
        return salvo;
    }

    public void setSalvo(Salvo salvo) {
        salvo.setGamePlayer(this);
        this.salvo.add(salvo);
    }

    public Score getScore(){
        return getPlayer().getScore(this.getGame());
    }

    public Map<String,Object> GamePlayerDTO(){ //gameplayers se que muestra en api/game_view
        Map<String,Object>Dto=new LinkedHashMap<>();
        Dto.put("id",this.getGame().getId());
        Dto.put("created",this.getGame().getCreationDate());
        Dto.put("gamePlayer" , this.game.getGamePlayers().stream().map(GamePlayer::gamePlayersDTO));
        Dto.put("ships",this.getShip().stream().map(Ship::shipsDTO).collect(Collectors.toList()));
        Dto.put("salvoes",this.getGame().getGamePlayers().stream().flatMap(gamePlayer -> gamePlayer.getSalvo().stream().map(Salvo::salvoesDTO)));
        return Dto;
    }

    public Map<String,Object> gamePlayersDTO(){  //gameplayers que se muestra en api/games
        Map<String,Object>Dto=new LinkedHashMap<>();
        Dto.put("id",this.getID());
        Dto.put("player",this.player.playersDTO());
        if(this.getScore()==null){
            Dto.put("score",null);
        }else {
            Dto.put("score", this.getScore().getScore());
        }
        return Dto;
    }
}
