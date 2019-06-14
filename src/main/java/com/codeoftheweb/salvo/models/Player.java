package com.codeoftheweb.salvo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Entity

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    List<GamePlayer> gamePlayer;

    @OneToMany(mappedBy = "player",fetch = FetchType.EAGER)
    List<Score> score;

    public Player() { }

    public Player(String first, String last,String user,String password) {
        this.firstName = first;
        this.lastName = last;
        this.userName = user;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        this.gamePlayer.add(gamePlayer);
    }

    @JsonIgnore
    public List<Game> getGames() {
        return gamePlayer.stream().map(sub -> sub.getGame()).collect(Collectors.toList());
    }

    public List<Score> getScores() {
        return score;
    }

    public Score getScore(Game game) {
        return getScores().stream().filter(score->score.getGame()==game).findFirst().orElse(null);
    }

    @JsonIgnore
    public List<GamePlayer> getGamePlayer() {
        return gamePlayer;
    }

    public void setScore(Score score) {
        score.setPlayer(this);
        this.score.add(score);
    }

    public Map<String,Object> playersDTO(){
        Map<String,Object>Dto = new LinkedHashMap<>();
        Dto.put("id",this.getId());
        Dto.put("username",this.getUserName());
        return Dto;
    }
}
