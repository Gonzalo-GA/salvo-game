package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.models.*;
import com.codeoftheweb.salvo.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;


@SpringBootApplication
public class SalvoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository pRepository, GamesRepository gRepository, GamePlayerRepository gpRepository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("Jack","Bauer","j.bauer@ctu.gov",passwordEncoder().encode("24"));
			Player player2 = new Player("Chloe", "O'Brian","c.obrian@ctu.gov",passwordEncoder().encode("42"));
			Player player3 = new Player("Kim", "Bauer","kim_bauer@gmail.com",passwordEncoder().encode("kb"));
			Player player4 = new Player("Tony", "Almeida","t.almeida@ctu.gov",passwordEncoder().encode("mole"));
			pRepository.save(player1);
			pRepository.save(player2);
			pRepository.save(player3);
			pRepository.save(player4);


			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();
			Game game4 = new Game();
			Game game5 = new Game();
			Game game6 = new Game();
			Game game7 = new Game();
			Game game8 = new Game();
			gRepository.save(game1);
			gRepository.save(game2);
			gRepository.save(game3);
			gRepository.save(game4);
			gRepository.save(game5);
			gRepository.save(game6);
			gRepository.save(game7);
			gRepository.save(game8);


			GamePlayer gp1 = new GamePlayer(player1,game1);
			gpRepository.save(gp1);
			GamePlayer gp2 = new GamePlayer(player2,game1);
			gpRepository.save(gp2);
			GamePlayer gp3 = new GamePlayer(player1,game2);
			gpRepository.save(gp3);
			GamePlayer gp4 = new GamePlayer(player2,game2);
			gpRepository.save(gp4);
			GamePlayer gp5 = new GamePlayer(player2,game3);
			gpRepository.save(gp5);
			GamePlayer gp6 = new GamePlayer(player4,game3);
			gpRepository.save(gp6);
			GamePlayer gp7 = new GamePlayer(player2,game4);
			gpRepository.save(gp7);
			GamePlayer gp8 = new GamePlayer(player1,game4);
			gpRepository.save(gp8);
			GamePlayer gp9 = new GamePlayer(player4,game5);
			gpRepository.save(gp9);
			GamePlayer gp10 = new GamePlayer(player1,game5);
			gpRepository.save(gp10);
			GamePlayer gp11 = new GamePlayer(player3,game6);
			gpRepository.save(gp11);
			GamePlayer gp12 = new GamePlayer(player4,game7);
			gpRepository.save(gp12);
			GamePlayer gp13 = new GamePlayer(player3,game8);
			gpRepository.save(gp13);
			GamePlayer gp14 = new GamePlayer(player4,game8);
			gpRepository.save(gp14);


			//Game 1 Ships
			List<String> celdas1 = new ArrayList<>(Arrays.asList("H2", "H3", "H4"));
			List<String> celdas2 = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			List<String> celdas3 = new ArrayList<>(Arrays.asList("E1", "F1", "G1"));
			List<String> celdas4 = new ArrayList<>(Arrays.asList("B4", "B5"));
			List<String> celdas5 = new ArrayList<>(Arrays.asList("F1", "F2"));

			Ship ship1 = new Ship(gp1,"Destroyer",celdas1);
			Ship ship4 = new Ship(gp2,"Destroyer",celdas2);
			Ship ship2 = new Ship(gp1,"Submarine",celdas3);
			Ship ship3 = new Ship(gp1,"Patrol_Boat",celdas4);
			Ship ship5 = new Ship(gp2,"Patrol_Boat",celdas5);
			shipRepository.save(ship1);
			shipRepository.save(ship4);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship5);


			//Game 2 Ships
			List<String> celdas6 = new ArrayList<>(Arrays.asList("B5", "C5", "D5"));
			List<String> celdas7 = new ArrayList<>(Arrays.asList("C6", "C7"));
			List<String> celdas8 = new ArrayList<>(Arrays.asList("A2", "A3", "A4"));
			List<String> celdas9 = new ArrayList<>(Arrays.asList("G6", "H6"));

			Ship ship6 = new Ship(gp3,"Destroyer",celdas6);
			Ship ship7 = new Ship(gp3,"Patrol_Boat",celdas7);
			Ship ship8 = new Ship(gp4,"Submarine",celdas8);
			Ship ship9 = new Ship(gp4,"Patrol_Boat",celdas9);

			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
			shipRepository.save(ship9);


			//Game 3 Ships

			Ship ship10 = new Ship(gp5,"Destroyer",celdas6);
			Ship ship11 = new Ship(gp5,"Patrol_Boat",celdas7);
			Ship ship12 = new Ship(gp6,"Submarine",celdas8);
			Ship ship13 = new Ship(gp6,"Patrol_Boat",celdas9);

			shipRepository.save(ship10);
			shipRepository.save(ship11);
			shipRepository.save(ship12);
			shipRepository.save(ship13);



			//Game 4 Ships
			Ship ship14 = new Ship(gp7,"Destroyer",celdas6);
			Ship ship15 = new Ship(gp7,"Patrol_Boat",celdas7);
			Ship ship16 = new Ship(gp8,"Submarine",celdas8);
			Ship ship17 = new Ship(gp8,"Patrol_Boat",celdas9);

			shipRepository.save(ship14);
			shipRepository.save(ship15);
			shipRepository.save(ship16);
			shipRepository.save(ship17);



			//Game 5 Ships
			Ship ship18 = new Ship(gp9,"Destroyer",celdas6);
			Ship ship19 = new Ship(gp9,"Patrol_Boat",celdas7);
			Ship ship20 = new Ship(gp10,"Submarine",celdas8);
			Ship ship21 = new Ship(gp10,"Patrol_Boat",celdas9);

			shipRepository.save(ship18);
			shipRepository.save(ship19);
			shipRepository.save(ship20);
			shipRepository.save(ship21);



			//Game 6 Ships
			Ship ship22 = new Ship(gp11,"Destroyer",celdas6);
			Ship ship23 = new Ship(gp11,"Patrol_Boat",celdas7);

			shipRepository.save(ship22);
			shipRepository.save(ship23);


			//Game 7 no Ships yet

			//Game 8 Ships
			Ship ship24 = new Ship(gp13,"Destroyer",celdas6);
			Ship ship25 = new Ship(gp13,"Patrol_Boat",celdas7);
			Ship ship26 = new Ship(gp14,"Submarine",celdas8);
			Ship ship27 = new Ship(gp14,"Patrol_Boat",celdas9);

			shipRepository.save(ship24);
			shipRepository.save(ship25);
			shipRepository.save(ship26);
			shipRepository.save(ship27);
			shipRepository.save(ship27);


			//Game 1 Salvoes
			List<String> locaciones1 = new ArrayList<>(Arrays.asList("B5","C5","F1"));
			List<String> locaciones2 = new ArrayList<>(Arrays.asList("F2","D5"));
			List<String> locaciones3 = new ArrayList<>(Arrays.asList("B4","B5","B6"));
			List<String> locaciones4 = new ArrayList<>(Arrays.asList("E1","H3","A2"));

			Salvo salvo1 = new Salvo(gp1,1,locaciones1);
			Salvo salvo2 = new Salvo(gp1,2,locaciones2);
			Salvo salvo3 = new Salvo(gp2,1,locaciones3);
			Salvo salvo4 = new Salvo(gp3,2,locaciones4);
			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);



			//Game 2 Salvoes
			List<String> locaciones5 = new ArrayList<>(Arrays.asList("A2","A4","G6"));
			List<String> locaciones6 = new ArrayList<>(Arrays.asList("A3","H6"));
			List<String> locaciones7 = new ArrayList<>(Arrays.asList("B5","D5","C7"));
			List<String> locaciones8 = new ArrayList<>(Arrays.asList("C5","C6"));

			Salvo salvo5 = new Salvo(gp3,1,locaciones5);
			Salvo salvo6 = new Salvo(gp3,2,locaciones6);
			Salvo salvo7 = new Salvo(gp4,1,locaciones7);
			Salvo salvo8 = new Salvo(gp4,2,locaciones8);

			salvoRepository.save(salvo5);
			salvoRepository.save(salvo6);
			salvoRepository.save(salvo7);
			salvoRepository.save(salvo8);


			//Game 3 Salvoes
			List<String> locaciones9 = new ArrayList<>(Arrays.asList("G6","H6","A4"));
			List<String> locaciones10 = new ArrayList<>(Arrays.asList("A2","A3","D8"));
			List<String> locaciones11 = new ArrayList<>(Arrays.asList("H1","H2","H3"));
			List<String> locaciones12 = new ArrayList<>(Arrays.asList("E1","F2","G3"));

			Salvo salvo9 = new Salvo(gp5,1,locaciones9);
			Salvo salvo10 = new Salvo(gp5,2,locaciones10);
			Salvo salvo11 = new Salvo(gp6,1,locaciones11);
			Salvo salvo12 = new Salvo(gp6,2,locaciones12);

			salvoRepository.save(salvo9);
			salvoRepository.save(salvo10);
			salvoRepository.save(salvo11);
			salvoRepository.save(salvo12);



			//Game 4 Salvoes
			List<String> locaciones13 = new ArrayList<>(Arrays.asList("A3","A4","F7"));
			List<String> locaciones14 = new ArrayList<>(Arrays.asList("A2","G6","H6"));
			List<String> locaciones15 = new ArrayList<>(Arrays.asList("B5","C6","H1"));
			List<String> locaciones16 = new ArrayList<>(Arrays.asList("C5","C7","D5"));

			Salvo salvo13 = new Salvo(gp7,1,locaciones13);
			Salvo salvo14 = new Salvo(gp7,2,locaciones14);
			Salvo salvo15 = new Salvo(gp8,1,locaciones15);
			Salvo salvo16 = new Salvo(gp8,2,locaciones16);

			salvoRepository.save(salvo13);
			salvoRepository.save(salvo14);
			salvoRepository.save(salvo15);
			salvoRepository.save(salvo16);



			//Game 5 salvoes
			List<String> locaciones17 = new ArrayList<>(Arrays.asList("A1","A2","A3"));
			List<String> locaciones18 = new ArrayList<>(Arrays.asList("G6","G7","G8"));
			List<String> locaciones19 = new ArrayList<>(Arrays.asList("B5","B6","C7"));
			List<String> locaciones20 = new ArrayList<>(Arrays.asList("C6","D6","E6"));
			List<String> locaciones21 = new ArrayList<>(Arrays.asList("H1","H8"));

			Salvo salvo17 = new Salvo(gp9,1,locaciones17);
			Salvo salvo18 = new Salvo(gp9,2,locaciones18);
			Salvo salvo19 = new Salvo(gp10,1,locaciones19);
			Salvo salvo20 = new Salvo(gp10,2,locaciones20);
			Salvo salvo21 = new Salvo(gp10,3,locaciones21);

			salvoRepository.save(salvo17);
			salvoRepository.save(salvo18);
			salvoRepository.save(salvo19);
			salvoRepository.save(salvo20);
			salvoRepository.save(salvo21);




			Score score1 = new Score(1,player1,game1);
			Score score2 = new Score(0,player2,game1);
			Score score3 = new Score(0.5,player1,game2);
			Score score4 = new Score(0.5,player2,game2);
			Score score5 = new Score(1,player2,game3);
			Score score6 = new Score(0,player4,game3);
			Score score7 = new Score(0.5,player2,game4);
			Score score8 = new Score(0.5,player1,game4);
			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			scoreRepository.save(score4);
			scoreRepository.save(score5);
			scoreRepository.save(score6);
			scoreRepository.save(score7);
			scoreRepository.save(score8);

		};
	}

}
