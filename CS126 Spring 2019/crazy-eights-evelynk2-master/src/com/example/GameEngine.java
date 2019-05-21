package com.example;

// that manages the deck, card distribution, the player turn cycles, win conditions, turn legality, etc.
// While not necessary, it would also be helpful to have another kind of object managed by the engine
// that holds each player's state (their cards, score, which strategy class is being implemented) and interacts
// with the strategy using the interface methods to send information and receive the strategy's actions.

/**
 * You game engine should take in strategies for four players and play a game of crazy eights using them.
 * The game engine should keep playing until one player has a score of 200 points or more.
 * Once a player has reached a score of 200 points or more, that player has won a single match.
 * Players who have made invalid moves during a match should be “reported” and the match should end.
 */


import java.util.List;
import java.util.LinkedList;

public class GameEngine {

    private List<Player> cheaters;
    private List<Player> players;
    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;
    private Player playerFour;

    private Player winningPlayer;
    private Player currentPlayer;


    // max and min amount of cards in the deck
    private int max;
    private int min;


    private List<Card> deck;
    private List<Card> drawPile;
    private List<Card> discardPile;
    private List<Card> initialCards;


    private Card topCard;
    private Card.Suit pileSuit;

    private boolean gameWon;
    private int moveCount;
    private int playerNumber;

    private List<Integer> opponentIds;



    public GameEngine(Player one, Player two, Player three, Player four) {

        playerOne = one;
        playerTwo = two;
        playerThree = three;
        playerFour = four;

        players = new LinkedList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);

        opponentIds = new LinkedList<>();
        opponentIds.add(1);
        opponentIds.add(2);
        opponentIds.add(3);
        opponentIds.add(4);

        int id = 1;
        for (Player p : players) {
            opponentIds.remove(id - 1);
            p.init(id, opponentIds);
            opponentIds.add(id - 1, id);
            id++;
        }

        currentPlayer = playerOne;
        playerNumber = 1;

        deck = Card.getDeck();
        discardPile = new LinkedList<>();
        moveCount = 0;

        max = deck.size();
        min = 1;


        cheaters = new LinkedList<Player>();



        if (gameEnded()) {

            finish();

        } else {

            runGame();

        }

    }

    public void runGame() {


        if (gameEnded()) {

            finish();

        } else if (moveCount == 0) {

            firstMove();

        } else {

            move(currentPlayer);

        }


    }



    // helper method for runGame
    public void move(Player current) {

        if (gameEnded()) {

            finish();

        } else if (current.shouldDrawCard(topCard, pileSuit)) {

            receiveCard(current);

            drawPile.remove(topCard);


            // if player did not draw card when they should have
            if (!receiveCard(current)) {

                cheaters.add(current);

            }
            

        } else {

            playCard(current);


            // if player did not play their card when they should have
            if (!playCard(current)) {

                cheaters.add(current);

            }

        }

        // check to see if player has more than or less than 5 cards
        if (current.getCards().size() != 5) {

            cheaters.add(current);

        }

        changePlayer();




    }

    // helper method to check for cheating
    public boolean receiveCard(Player current) {

        current.receiveCard(getTopCard(drawPile));

        return true;
    }


    // helper method to check for cheating
    public boolean playCard(Player current) {


        Card played = current.playCard();
        discardPile.add(played);

        return true;
    }




    public void changePlayer() {

        changePlayerHelper();

        move(currentPlayer);

    }



    // helper method for changePlayer
    public Player changePlayerHelper() {


        if (playerNumber < 4) {


            playerNumber++;

            currentPlayer = players.get(playerNumber - 1);

        } else {


            playerNumber = 1;

            currentPlayer = players.get(playerNumber - 1);

        }

        return currentPlayer;

    }




    // helper function for when it is the first move of the game (setting up)
    public void firstMove() {

        for (Player p : players) {

            dealOutInitialCards(p);

        }

        while (getTopCard(drawPile).getRank() == Card.Rank.EIGHT) {

            // shuffles the deck
            drawPile = shuffle(drawPile);

        }

        firstMoveHelper();

    }


    // helper function that returns the Card it removed
    public void firstMoveHelper() {

        // first move: The top card is drawn from the draw pile and placed in the discard pile
        Card firstCard = getTopCard(drawPile);


        discardPile.add(0, firstCard);

        drawPile.remove(firstCard);

        moveCount++;

    }




    // helper function to shuffle deck of cards
    public List<Card> shuffle(List<Card> cards) {

        List<Card> shuffled = new LinkedList<>();

        while (cards.size() > 0) {

            int index = (int) (Math.random() * cards.size());

            shuffled.add(cards.remove(index));

        }

        return shuffled;
    }



    public List<Card> dealOutInitialCards(Player player) {

        List<Card> initialCards = new LinkedList<Card>();


        for (int k = 0; k < 5; k++) {

            int range = (max - min);
            int random = (int) (Math.random() * range);


            initialCards.add(deck.get(random));;

            deck.remove(deck.get(random));

            max--;

        }


        // remaining cards become the drawPile
        drawPile = deck;


        player.setCards(initialCards);
        this.initialCards = initialCards;
        return initialCards;
    }




    public Card getTopCard(List<Card> cards) {

        topCard = cards.get(0);


        if (topCard.getRank() == Card.Rank.EIGHT && cards == drawPile) {


            pileSuit = currentPlayer.declareSuit();


        }


        return topCard;
    }


    public boolean gameEnded() {

        boolean ended = false;

         if (moveCount != 0 && drawPile.isEmpty()) {


            ended = true;


        } else {


            for (Player p : players) {

                if (!ended) {

                    ended = gameEndedHelper(p);

                }
            }
        }


        return ended;
    }


    // helper function for gameEnded
    public boolean gameEndedHelper(Player player) {
        boolean ended = false;

        if (moveCount != 0 && player.getCards().isEmpty()) {

            gameWon = true;

            ended = true;

            winningPlayer = player;
        }

        return ended;

    }


    public void finish() {

        if (gameWon) {

            // SINGLE WINNER: The player who won will gain the summed value of cards that the other three players have in their hands.
            for (Player p : players) {


                if (p == winningPlayer) {


                    p.setPoints(getEndPoints());

                    break;

                }

            }


        } else {

            // TIE: all players should receive the summed value of the other three players’ hands.

            for (Player p : players) {

                p.setPoints(getEndPoints());

            }


        }


        for (Player p : players) {

            Main.cumulativeGameScores(p, p.getPoints());

            p.reset();

        }

    }


    // The player who won will gain the summed value of cards that the other three players have in their hands.
    // In the case of a tie, all players should receive the summed value of the other three players’ hands.
    public int getEndPoints() {

        int totalPoints = 0;

        for (Player p : players) {


            if (p.getCards() != null && !p.getCards().isEmpty()) {


                for (Card c : p.getCards()) {


                    totalPoints += c.getPointValue();

                }
            }

        }


        return totalPoints;
    }



    public List<Player> returnCheaters() {

        Main.reportCheaters(cheaters);
        return cheaters;

    }






    // methods added for testing purposes


    public void setPlayerCards(Player p, List<Card> cards) {
        p.setCards(cards);
    }



    public void setPlayerNumber(int number) {
        playerNumber = number;
    }



    public List<Card> getInitialCards() {
        return initialCards;
    }



    public List<Card> getDeck() {
        return deck;
    }



    public List<Card> getDrawPile() {
        return drawPile;
    }



    public List<Card> getDiscardPile() {
        return discardPile;
    }



    public void setDrawPile(List<Card> pile) {
        this.drawPile = pile;
    }



    public void setMoveCount(int count) {
        moveCount = count;
    }



    public Player getWinningPlayer() {
        return winningPlayer;
    }



    public void setWinningPlayer(Player winner) {
        winningPlayer = winner;
    }



    public void setGameWon(boolean won) {
        gameWon = won;
    }




}
