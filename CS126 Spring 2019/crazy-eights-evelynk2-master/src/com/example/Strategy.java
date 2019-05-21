package com.example;


import java.util.LinkedList;
import java.util.List;

public class Strategy implements PlayerStrategy {


    private int playerID;
    private List<Integer> opponentIds;
    private List<Card> cards;
    private Card topCard;
    private Card.Suit pileSuit;
    List<PlayerTurn> opponentActions = new LinkedList<>();



    /**
     * Gives the player their assigned id, as well as a list of the opponents' assigned ids.
     *
     * @param playerId The id for this player
     * @param opponentIds A list of ids for this player's opponents
     */
    public void init(int playerId, List<Integer> opponentIds) {
        this.playerID = playerId;
        this.opponentIds = opponentIds;

    }


    /**
     * Called at the very beginning of the game to deal the player their initial cards.
     *
     * @param cards The initial list of cards dealt to this player
     */
    public void receiveInitialCards(List<Card> cards) {
        this.cards = cards;
    }



    /**
     * Called to check whether the player wants to draw this turn. Gives this player the top card of
     * the discard pile at the beginning of their turn, as well as an optional suit for the pile in
     * case a "8" was played, and the suit was changed.
     *
     * By having this return true, the game engine will then call receiveCard() for this player.
     * Otherwise, playCard() will be called.
     *
     * @param topPileCard The card currently at the top of the pile
     * @param pileSuit The suit that the pile was changed to as the result of an "8" being played.
     * Will be null if no "8" was played.
     * @return whether or not the player wants to draw
     */
    public boolean shouldDrawCard(Card topPileCard, Card.Suit pileSuit) {

        boolean shouldDraw = true;

        this.topCard = topPileCard;

        for (Card c: cards) {

            if (pileSuit != null) {

                if (c.getSuit() == pileSuit || c.getRank() == Card.Rank.EIGHT) {
                    shouldDraw = false;

                }

            } else {

                if (c.getRank() == topPileCard.getRank() || c.getSuit() == topPileCard.getSuit() || c.getRank() == Card.Rank.EIGHT) {
                    shouldDraw = false;
                }

            }
        }

        return shouldDraw;
    }



    /**
     * Called when this player has chosen to draw a card from the deck.
     *
     * @param drawnCard The card that this player has drawn
     */
    public void receiveCard(Card drawnCard) {
        cards.add(0, drawnCard);
    }




    /**
     * Called when this player is ready to play a card (will not be called if this player drew on
     * their turn).
     *
     * This will end this player's turn.
     *
     * @return The card this player wishes to put on top of the pile
     */
    public Card playCard() {

        // if last played card was an eight
        if (topCard.getRank() == Card.Rank.EIGHT) {

            // hand of cards does not have one of declared suit, so has to return an eight
            if (cardOfPileSuit(cards) == null) {

                topCard = getEightCard(cards);

                cards.remove(topCard);

                return topCard;

            } else {

                topCard = cardOfPileSuit(cards);

                cards.remove(topCard);

                return topCard;
            }


        } else {

            for (Card c : cards) {

                if (c.getRank() == topCard.getRank() || c.getSuit() == topCard.getSuit()) {


                    topCard = c;
                    cards.remove(c);

                    return topCard;

                }
            }

            if (hasAnEight(cards)) {

                topCard = getEightCard(cards);

                cards.remove(topCard);

                declareSuit();

                return topCard;
            }

        }

        return topCard;


    }



    // helper function to return a card (if it exists) of specific pile suit
    // declared after another player has played an eight
    public Card cardOfPileSuit(List<Card> cards) {

        for (Card c : cards) {

            if (c.getSuit() == pileSuit) {

                return c;

            }
        }

        return null;
    }



    // helper function to determine if the list of cards has an eight
    public boolean hasAnEight(List<Card> cards) {

        boolean result = false;

        if (cards == null) {

            return false;

        }

        for (Card c : cards) {

            if (c.getRank() == Card.Rank.EIGHT) {

                result = true;
            }
        }

        return result;
    }



    // helper function that is called only when hasAnEight returns true
    public Card getEightCard(List<Card> cards) {

        for (Card c : cards) {

            if (c.getRank() == Card.Rank.EIGHT) {

                return c;

            }
        }

        // should never be returned
        return null;
    }



    /**
     * Called if this player decided to play a "8" card. This player should then return the
     * Card.Suit enum that it wishes to set for the discard pile.
     */
    public Card.Suit declareSuit() {

        pileSuit = Card.Suit.DIAMONDS;
        return Card.Suit.DIAMONDS;
    }




    /**
     * Called at the very beginning of this player's turn to give it context of what its opponents
     * chose to do on each of their turns.
     *
     * @param opponentActions A list of what the opponents did on each of their turns
     */
    public void processOpponentActions(List<PlayerTurn> opponentActions) {
        this.opponentActions = opponentActions;
    }




    /**
     * Called when the game is being reset for the next round.
     */
    public void reset() {
        cards = new LinkedList<>();
    }

}




