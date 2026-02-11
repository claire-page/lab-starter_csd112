package core;


import java.util.ArrayList;

public record Draw(Player player, Card drawCard) {

    public Draw(Player player, Card drawCard) {
        this.player = player;
        this.drawCard = drawCard;

    }
    public String toString() {
        return (player.toString() + "'s draw: " + drawCard.toString());
    }

    public Player getPlayer(){
        return this.player;
    }

    public Card getCard (){
        return this.drawCard;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
