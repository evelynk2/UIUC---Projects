package com.example;

import java.util.LinkedList;
import java.util.List;



public class Player extends Strategy {



    private int points;

    private int totalPoints;

    private List<Card> cards;



    public void setCards(List<Card> cards) {
        receiveInitialCards(cards);
        this.cards = cards;
    }



    public List<Card> getCards() {
        return cards;
    }



    public void setPoints(int points) {
        this.points = points;
        setTotalPoints(points);
    }



    public int getPoints() {
        return points;
    }



    // testing purposes
    public void setTopCard(Card c) {
        shouldDrawCard(c, null);
    }



    public void setTotalPoints(int points) {
        totalPoints += points;
    }



    public int getTotalPoints() {
        return totalPoints;
    }



    public void reset() {
        cards = new LinkedList<>();
    }


}
