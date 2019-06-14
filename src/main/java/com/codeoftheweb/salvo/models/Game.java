package com.codeoftheweb.salvo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;




@Entity

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long ID;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    List<GamePlayer> gamePlayer;

    @OneToMany(mappedBy = "game",fetch = FetchType.EAGER)
    List<Score> score;

    public Game(){
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        this.creationDate = LocalDateTime.of(today,now);
    }

    public long getId() {
        return ID;
    }

    public void setId(long id) {
        this.ID = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        this.gamePlayer.add(gamePlayer);
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayer;
    }

    @JsonIgnore
    public List<Player> getPlayers() {
        return gamePlayer.stream().map(sub -> sub.getPlayer()).collect(Collectors.toList());
    }

    public List<Score> getScore() {
        return score;
    }

    public void setScore(Score score) {
        score.setGame(this);
        this.score.add(score);
    }

    public Map <String,Object> gamesDTO(){
        Map <String,Object>Dto = new LinkedHashMap<>();
        Dto.put("id",this.getId());
        Dto.put("created",this.getCreationDate());
        Dto.put("gamePlayers",this.getGamePlayers().stream().map(GamePlayer::gamePlayersDTO).collect(Collectors.toList()));
        return Dto;
    }
}
