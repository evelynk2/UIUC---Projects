package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.LinkedList;

import static org.junit.Assert.*;


public class StrategyTest {

    private GameEngine game;

    private List<Card> cardsWithEight;
    private List<Card> cardsWithoutEight;


    private Player playerWithEight = new Player();
    private Player playerWithoutEight = new Player();
    private Player randomPlayer = new Player();
    private Player randomPlayerTwo = new Player();



    @Before
    public void setUp() {
        game = new GameEngine(playerWithEight, playerWithoutEight, randomPlayer, randomPlayerTwo);

        game.dealOutInitialCards(randomPlayer);


        // setting up player with cards that include an eight
        // cards = {four of clubs, eight of spades, jack of hearts, ace of hearts, two of clubs}
        cardsWithEight = new LinkedList<>();
        cardsWithEight.add(new Card(Card.Suit.CLUBS, Card.Rank.FOUR));
        cardsWithEight.add(new Card(Card.Suit.SPADES, Card.Rank.EIGHT));
        cardsWithEight.add(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
        cardsWithEight.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        cardsWithEight.add(new Card(Card.Suit.CLUBS, Card.Rank.TWO));

        playerWithEight.receiveInitialCards(cardsWithEight);


        // setting up player with cards that DON'T include an eight
        // cards = {six of diamonds, king of diamonds, four of hearts, queen of spades, three of hearts}
        cardsWithoutEight = new LinkedList<>();
        cardsWithoutEight.add(new Card(Card.Suit.DIAMONDS, Card.Rank.SIX));
        cardsWithoutEight.add(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
        cardsWithoutEight.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));
        cardsWithoutEight.add(new Card(Card.Suit.SPADES, Card.Rank.QUEEN));
        cardsWithoutEight.add(new Card(Card.Suit.HEARTS, Card.Rank.THREE));

        playerWithoutEight.receiveInitialCards(cardsWithoutEight);
    }




    @Test
    public void getInitialCards() {
        assertEquals(game.dealOutInitialCards(randomPlayer), randomPlayer.getCards());
    }



    @Test
    public void shouldDrawCardFalse() {
        assertFalse(playerWithEight.shouldDrawCard(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE), null));
    }



    @Test
    public void shouldDrawCardTrue() {
        assertTrue(playerWithoutEight.shouldDrawCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE), null));
    }



    @Test
    public void shouldDrawCardAfterEight() {
        assertTrue(playerWithoutEight.shouldDrawCard(new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT), Card.Suit.CLUBS));
    }



    @Test
    public void shouldNotDrawCardAfterEight() {
        // has a card with the declared suit
        assertFalse(playerWithoutEight.shouldDrawCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT), Card.Suit.DIAMONDS));

        // has an eight to play
        assertFalse(playerWithEight.shouldDrawCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT), Card.Suit.DIAMONDS));
    }



    @Test
    public void receiveCard() {
        Card drawnCard = new Card(Card.Suit.CLUBS, Card.Rank.SEVEN);
        randomPlayer.receiveCard(drawnCard);


        assertEquals(drawnCard, randomPlayer.getCards().get(0));
    }



    @Test
    public void playCard() {
        playerWithoutEight.setTopCard(new Card(Card.Suit.DIAMONDS, Card.Rank.SIX));


        assertEquals(cardsWithoutEight.get(0), playerWithoutEight.playCard());
    }



    @Test
    public void playEightCard() {
        playerWithEight.setTopCard(new Card(Card.Suit.DIAMONDS, Card.Rank.SIX));


        assertEquals(cardsWithEight.get(1), playerWithEight.playCard());
    }



    @Test
    public void hasAnEight() {
        assertTrue(playerWithEight.hasAnEight(cardsWithEight));
    }



    @Test
    public void hasNoEight() {
        assertFalse(playerWithoutEight.hasAnEight(playerWithoutEight.getCards()));
    }



    @Test
    public void getEightCard() {
        assertEquals(cardsWithEight.get(1), playerWithEight.getEightCard(cardsWithEight));
    }



    @Test
    public void getEigthCardNull() {
        assertNull(playerWithoutEight.getEightCard(cardsWithoutEight));
    }


    @Test
    public void declareSuit() {
        assertEquals(Card.Suit.DIAMONDS, randomPlayer.declareSuit());
    }



    @Test
    public void reset() {
        playerWithEight.reset();
        assertEquals(new LinkedList<>(), playerWithEight.getCards());
    }

}
