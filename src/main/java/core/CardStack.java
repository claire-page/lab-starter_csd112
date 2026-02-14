/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate a stack (deck/hand) of playing cards.

    You MAY change this to a record/enum as you see fit.
 */
package core;
import java.util.ArrayList;
import java.util.Collections;

public class CardStack {

    private ArrayList<Card> cards;

    private int numberOfCards; //thinking it does not make sense to have this as a var


    private CardStack(ArrayList<Card> cards) {
        //constructs CardStack from arraylist of cards.
        this.cards = cards;
        this.numberOfCards = cards.size();
    }

    CardStack() {

        this.cards = new ArrayList<>();
    }

    public CardStack(Card c) {

        CardStack stack = new CardStack();
        stack.addToStack(c);
    }

    public static CardStack newShuffledDeck() {

        var deck = new ArrayList<Card>(1);

        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                deck.add(new Card(s, r));
            }
        }
        Collections.shuffle(deck);
        return (new CardStack(deck));
    }

    @Override
    public String toString() {

        StringBuilder ret_string = new StringBuilder();
        for (Card c : this.cards) { //calling to string on s
            ret_string.append(c.toString());
        }
        return (ret_string).toString();
    }


    public Card drawOneCard() {

        return (cards.removeFirst());
    }

    public Card takeCardAt(int index) {

        return (cards.remove(index));
    }

    public CardStack dealHand(int cardsperhands) {

        CardStack hand = new CardStack();
        for (int i = 0; i < cardsperhands; i++) {
            hand.cards.add(i, this.drawOneCard());
        }
        return hand;
    }

    public Card getCardAt(int index) {
        return cards.get(index);
    }

    //for adding a card to a deck. making this overloaded
    public void addToStack(Card c) {

        this.cards.add(c); //this works because the add function appends it to the end of the arraylist.
        this.numberOfCards += 1;
    }

    //for adding a deck to another deck.
    public void addToStack(CardStack cStack) {

        while (cStack.getNumberOfCards() > 0) {
            this.addToStack(cStack.cards.removeFirst());
            this.numberOfCards += 1;
        }
    }
    //note: this removes.

    public int getNumberOfCards() {

        return (this.cards.size());
    }


    public void replaceStack(Card c) {
        this.cards = new ArrayList<Card>();
        this.addToStack(c);

    }
    public void replaceStack(CardStack cStack) {
        this.cards = new ArrayList<Card>();
        this.addToStack(cStack);
    }

    /**
     *
     * @return new CardStack containing highest cards in stack it was
     * called on. if multiple of same card-rank are found and they end up
     * being the highest, those are added.
     */

    public CardStack highestCardsinStack() {
        CardStack highest = new CardStack();
        highest.addToStack(this.getCardAt(0)); //adding the first card of current deck to start.

        for (int i = 1; i < this.numberOfCards; i++) {

            Card c = this.getCardAt(i);
            Card currenthighest = highest.getCardAt(0);

            if (c.getCardValue() == currenthighest.getCardValue()) {
                highest.addToStack(c);
            } else if (c.getCardValue() > currenthighest.getCardValue()) {
                highest.replaceStack(c);
            }
        }
        return (highest);
    }

    /**
     *
      * @param c a Card
     * @return the index of a card in the CardStack it was called on
     */
    public int indexOf(Card c) {
        return (this.cards.indexOf(c));
    }

    public ArrayList<Card> getCards() {
        return (this.cards);
    }

}

