package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class GameEngineTest {

    private List<Player> players;
    private Player playerOne = new Player();
    private Player playerTwo = new Player();
    private Player playerThree = new Player();
    private Player playerFour = new Player();


    private GameEngine game;

    private List<Card> mockCards;
    private List<Card> mockDrawPile;

    private List<Integer> opponentIds;


    @Before
    public void setUp() {

        game = new GameEngine(playerOne, playerTwo, playerThree, playerFour);

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



        // {four of clubs, eight of spades, jack of hearts, ace of hearts, two of clubs}
        mockCards = new LinkedList<>();
        mockCards.add(new Card(Card.Suit.CLUBS, Card.Rank.FOUR));
        mockCards.add(new Card(Card.Suit.SPADES, Card.Rank.EIGHT));
        mockCards.add(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
        mockCards.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        mockCards.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));

        // {king of diamonds, four of hearts, queen of spades, eight of diamonds, three of hearts}
        mockDrawPile = new LinkedList<>();
        mockDrawPile.add(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
        mockDrawPile.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));
        mockDrawPile.add(new Card(Card.Suit.SPADES, Card.Rank.QUEEN));
        mockDrawPile.add(new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT));
        mockDrawPile.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));

    }


    @Test
    public void changePlayer() {
        game.setPlayerNumber(1);
        assertEquals(playerTwo, game.changePlayerHelper());

        game.setPlayerNumber(4);
        assertEquals(playerOne, game.changePlayerHelper());
    }


    @Test
    public void firstMove() {

        game.setDrawPile(mockDrawPile);
        game.firstMoveHelper();

        // discard pile should initially only have the top card from the draw pile (first move)
        assertEquals(new Card(Card.Suit.DIAMONDS, Card.Rank.KING), game.getDiscardPile().get(0));

    }


    @Test
    public void shuffle() {

        assertNotEquals(mockDrawPile ,game.shuffle(mockDrawPile));
    }

    @Test
    public void dealOutInitialCards() {

        game.dealOutInitialCards(playerOne);

        assertEquals(playerOne.getCards(), game.getInitialCards());

    }

    @Test
    public void getInitialDrawPile() {

        game.dealOutInitialCards(playerOne);

        assertEquals(game.getDeck(), game.getDrawPile());
    }



    @Test
    public void getTopCard() {

        assertEquals(new Card(Card.Suit.CLUBS, Card.Rank.FOUR), game.getTopCard(mockCards));
    }


    @Test
    public void testShuffled() {

        playerFour.setCards(game.shuffle(mockCards));

        assertNotEquals(mockCards, playerFour.getCards());
    }



    @Test
    public void gameEndedEmptyDrawPile() {
        game.setMoveCount(1);
        game.setDrawPile(new LinkedList<>());

        assertTrue(game.gameEnded());
    }



    @Test
    public void gameEndedEmptyHandOfCards() {
        // set up
        game.setMoveCount(5);
        game.setDrawPile(mockDrawPile);
        playerOne.setCards(new LinkedList<>());

        assertTrue(game.gameEndedHelper(playerOne));
    }



    @Test
    public void gameDidNotEnd() {
        // set up
        game.setMoveCount(0);
        game.setDrawPile(mockDrawPile);

        for (Player p : players) {
            p.receiveInitialCards(mockCards);
        }

        assertFalse(game.gameEnded());
    }



    @Test
    public void getWinner() {
        // setup
        game.setMoveCount(235);
        game.setDrawPile(mockDrawPile);
        playerThree.setCards(new LinkedList<>());

        game.gameEndedHelper(playerThree);
        assertEquals(playerThree, game.getWinningPlayer());
    }




    @Test
    public void finishTie() {
        // set up
        game.setMoveCount(4);
        game.setDrawPile(new LinkedList<>());

        // set player cards
        game.setPlayerCards(playerOne, mockDrawPile);
        game.setPlayerCards(playerTwo, new LinkedList<>());
        game.setPlayerCards(playerThree, mockCards);
        game.setPlayerCards(playerFour, new LinkedList<>());

        game.finish();

        // all players should get the other three players' card points
        assertEquals(144, playerOne.getPoints());
        assertEquals(144, playerTwo.getPoints());
    }



    @Test
    public void finishWinner() {

        List<Card> empty = new LinkedList<>();

        game.setGameWon(true);
        game.setWinningPlayer(playerFour);

        // set player cards
        game.setPlayerCards(playerOne, mockCards);
        game.setPlayerCards(playerTwo, mockCards);
        game.setPlayerCards(playerThree, mockCards);
        game.setPlayerCards(playerFour, empty);

        game.finish();


        // only the winnning player should get points
        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());
        assertEquals(201, playerFour.getPoints());
    }



    @Test
    public void getEndPoints() {

        List<Card> empty = new LinkedList<>();

        // set player cards
        game.setPlayerCards(playerOne, mockCards);
        game.setPlayerCards(playerTwo, empty);
        game.setPlayerCards(playerThree, empty);
        game.setPlayerCards(playerFour, mockCards);


        assertEquals(134, game.getEndPoints());
    }



    @Test
    public void returnCheaters() {

        assertEquals(new LinkedList<>(), game.returnCheaters());
    }





    // Testing main class

    @Test
    public void over200Points() {
        playerOne.setTotalPoints(224);
        Main main = new Main(playerOne, playerTwo, playerThree, playerFour);

        assertFalse(main.run());
    }

    @Test
    public void below200Points() {
        // set player points
        playerOne.setTotalPoints(30);
        playerTwo.setTotalPoints(34);
        playerThree.setTotalPoints(1);
        playerFour.setTotalPoints(199);
        Main main = new Main(playerOne, playerTwo, playerThree, playerFour);

        assertTrue(main.run());

    }

    @Test
    public void reportingCumulativeScore() {
        Main main = new Main(playerOne, playerTwo, playerThree, playerFour);

        // set game points
        main.cumulativeGameScores(playerFour, 230);
        main.cumulativeGameScores(playerOne, 20);

        assertEquals(20, main.getPlayer1GameScore());
        assertEquals(230, main.getPlayer4GameScore());

    }



}
