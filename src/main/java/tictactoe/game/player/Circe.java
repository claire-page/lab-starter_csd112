package tictactoe.game.player;

import tictactoe.game.*;

import java.text.ParseException;

public class Circe extends Player{

    public Circe(Token token) {
        super("Circe", token);    }

    @Override
    public Position getNextMove(Board board) {
        //Circe just iterates thru these.
        //side note - was thinking of implementing this where you would pop (??) the first value in the list and if it was occupied
        //you'd just carry on without using it which would save time maybe, circe doesn't need to know a position unless it's useful to her/it.

            String[] circeMoves = {"2 2 ", "1 2", "1 3", "2 3", "3 3", "3 2", "3 1 ", "2 1", " 1 1"};

            for (String s : circeMoves) {
               try { Position p = Position.parse(s); //needed a try here ig
                    if (board.isEmptyAt(p)) {
                        return(p);
                    }

            } catch (ParseException e) {
                   throw new RuntimeException(e);
               }

        }

        return null;
    }}
