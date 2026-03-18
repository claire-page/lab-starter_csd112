
package core;
import java.util.ArrayList;
import java.util.Collections;

public class CardStack {

    private ArrayList<Card> cards;



    private CardStack(ArrayList<Card> cards) {
        //constructs CardStack from arraylist of cards.
        this.cards = cards;
    }

    CardStack() {

        this.cards = new ArrayList<>();
    }

    public CardStack(Card c) {
        //calls constructor, used to make one-card decks but i never actually used it.
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
        for (Card c : this.cards) {
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

    /**
     * draws + removes cards fom the cardstack it's called on
     * and returns a new cardstack containing those cards.
     * note: this is intended for use with dealing from decks, hence the name.
     * @param cardsperhands how many cards will be in the hand
     * @return new cardstack containing the top cardsperhand cards of the deck it was
     * called on.
     */
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

    /**
     * adds a card to the stack it is called on.
     * @param c another Card
     */
    //adding a card to a deck. making this overloaded
    public void addToStack(Card c) {

        this.cards.add(c); //this works because the add function appends it to the end of the arraylist.
    }

    /** adding a given deck to the deck it is called on.
     * @param cStack another CardStack
     //empties cStack in the process.
     */

    public void addToStack(CardStack cStack) {
            this.cards.addAll(cStack.cards); //appends all.
            cStack.cards = null; //setting this cardstack to empty.
        }



    /**
     *
     * @return number of cards in a cardstack.
     */
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

        for (int i = 1; i < this.getNumberOfCards(); i++) {

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



