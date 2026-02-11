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

    //these are public because i would like to access them in the package at least.
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

    public int getCardValue() {
        return (rank.ordinal() + 1);
    }

    public Card copy(){
      return(new Card(this.suit, this.rank));
    }

    //should these be doing the thing where they check the object?

    public boolean isEqualRank(Card c2) {
        return (this.getCardValue() == c2.getCardValue());
    }


    public boolean isGreaterRank(Card c2) {
        return (this.getCardValue() > c2.getCardValue());
    }














}




//gameplay condensed in a while loop.


