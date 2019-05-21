package com.example;

import java.util.List;

// You must write a main function that uses your game engine to play a match of crazy eights
// reporting the cumulative scores for each game until the match is completed or a cheater is found.

public class Main {


    private static Player player1 = new Player();
    private static Player player2 = new Player();
    private static Player player3 = new Player();
    private static Player player4 = new Player();

    private static int player1GameScore;
    private static int player2GameScore;
    private static int player3GameScore;
    private static int player4GameScore;




    private final int WINNING_POINTS = 200;

    private GameEngine game;


    public Main(Player one, Player two, Player three, Player four) {
        player1 = one;
        player2 = two;
        player3 = three;
        player4 = four;

        if (run()) {
            game = new GameEngine(player1, player2, player3, player4);
        }

    }

    public boolean run() {


        if (player1.getTotalPoints() < WINNING_POINTS && player2.getTotalPoints() < WINNING_POINTS
                && player3.getTotalPoints() < WINNING_POINTS && player4.getTotalPoints() < WINNING_POINTS) {
            return true;
        }

        return false;
    }


    public static void cumulativeGameScores(Player player, int points) {
        if (player == player1) {

            player1GameScore = points;

        } else if (player == player2) {

            player2GameScore = points;

        } else if (player == player3) {

            player3GameScore = points;

        } else if (player == player4) {

            player4GameScore = points;

        }

    }

    public static void reportCheaters(List<Player> cheaters) {

        System.out.print("The cheaters are: " + cheaters);

    }


    public int getPlayer1GameScore() {
        return player1GameScore;
    }

    public int getPlayer2GameScore() {
        return player2GameScore;
    }

    public int getPlayer3GameScore() {
        return player3GameScore;
    }

    public int getPlayer4GameScore() {
        return player4GameScore;
    }



}
