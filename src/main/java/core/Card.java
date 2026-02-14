/*
    NOTE:
    Add to this type any variables and/or methods required
    to represent one playing card.
    You MAY change this to a record/enum as you see fit.
 */
package core;

public record Card(Suit suit, Rank rank) {

    public enum Suit {

        Spades("♠ "), //you are defining enum values that call the constructor of the enum.
        Hearts("♥ "),
        Diamonds("♦ "),
        Clubs("♣ ");

        private final String stringsuit;

        private Suit(String givenStringSuit) {
            this.stringsuit = givenStringSuit;
        }

        String getStringSuit() {
            return (stringsuit);
        }
    }

    public enum Rank {

        One("1"),
        Two("2"),
        Three("3"),
        Four("4"),
        Five("5"),
        Six("6"),
        Seven("7"),
        Eight("8"),
        Nine("9"),
        Ten("10"),
        Jack("J"),
        Queen("Q"),
        King("K"),
        Ace("A");

        private final String stringrank;

        private Rank(String givenstring) {
            this.stringrank = givenstring;

        }
    }

    @Override
    public String toString() {
        return (rank.stringrank + suit.stringsuit);
    }

    /**determines rank value of card
     * based off of its position in
     * the rank enum which corresponds to its value.
     * @return the numeric value of the card as per high card rules
     */
    public int getCardValue() {
        return (this.rank.ordinal() + 1);
    }


    public Card copy(){ //never used this, maybe should have.
      return(new Card(this.suit, this.rank));
    }

    /**determines whether c2 is equal in value to the card this is called on.
     *
     * @param c2 another Card
     * @return boolean value of "is c2 equal in value to the card this is called on."
     */
    public boolean isEqualRank(Card c2) {
        return (this.getCardValue() == c2.getCardValue());
    }

    /**determines whether c2 is greater in value to the card this is called on.
     *
     * @param c2 another Card
     * @return boolean value of "is c2 greater in value to the card this is called on."
     */
    public boolean isGreaterRank(Card c2) {
        return (this.getCardValue() > c2.getCardValue());
    }


}




//gameplay condensed in a while loop.


