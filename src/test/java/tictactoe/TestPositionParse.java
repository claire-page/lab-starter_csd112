package tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import tictactoe.game.Col;
import tictactoe.game.Position;
import tictactoe.game.Position;
import tictactoe.game.Row;

import java.text.ParseException;

import static java.lang.invoke.MethodHandles.throwException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPositionParse {

    @Test
    public void testNumbersWork(){
        try {
            Position pos = (Position.parse("12"));

            assertTrue(pos.row() == Row.Top);
            assertTrue(pos.col() == Col.Middle);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            Position pos2 = (Position.parse("2,3"));
            assertTrue(pos2.row()==Row.Middle);
            assertTrue(pos2.col()==Col.Right);
        }  catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
@Test
    public void testEquivalentInputsReturnEqualPositions(){
        //caps and lowercase
        try { Position pos = Position.parse("ml");
            Position pos2 = Position.parse("ML");
            assertTrue(pos.equals(pos2), ("The upper and lower-case positions should be equal but they're not. \nExpected: 'ml' = Position[row=Top, col=Left], 'ML'= Position[row=Middle, col=Left]\nActual: 'ml' = %s, 'ML' = %s").formatted(pos, pos2));
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //lowercase and numbers
        try { Position pos = Position.parse("tr");
            Position pos2 = Position.parse("1;3");
         assertTrue(pos.equals(pos2), ("The letter and number positions should be equal but they're not. \nExpected: tr = Position[row=Top, col=Right], 1;3 = Position[row=Middle, col=Left]\nActual: pos1 = %s, pos2 = %s").formatted(pos, pos2));
    }


    catch (ParseException e) {
        throw new RuntimeException(e);
    }
    }


    @Test
    public void assertExceptionsThrownByInvalidArgs() {

        ParseException exception = assertThrows(
                ParseException.class,
                () -> Position.parse("123"),
                "Expected big value to throw a ParseException, but it did not."
        );

        ParseException exception2 = assertThrows(
                ParseException.class,
                () -> Position.parse("middlemiddle"),
                "Expected big value to throw a ParseException, but it did not."
        );

        ParseException exception3 = assertThrows(
                ParseException.class,
                () -> Position.parse("loiwehflsdfh;klahejrf"),
                "Expected long string to throw a ParseException, but it did not."
        );


        ParseException exception4 = assertThrows(
                    ParseException.class,
                    () -> Position.parse("00"),
                    "Expected zeroes to throw a ParseException, but it did not."
            );



    ParseException exception5 = assertThrows(
            ParseException.class,
            () -> Position.parse("  "),
            "Expected empty string to throw a ParseException, but it did not."
    );

        NullPointerException exception6 = assertThrows(
                NullPointerException.class,
                () -> Position.parse(null),
                "Expected null value to throw a NullPointerException, but it did not."
        );
    }


    }


