package tictactoe.game;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void testNumbersWork() {
        try {
            Position pos = (Position.parse("12"));

            assertTrue(pos.row() == Row.Top);
            assertTrue(pos.col() == Col.Middle);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            Position pos2 = (Position.parse("2,3"));
            assertTrue(pos2.row() == Row.Middle);
            assertTrue(pos2.col() == Col.Right);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testEquivalentInputsReturnCorrectPosition() {
        Position middleLeft = new Position(Row.Middle, Col.Left);

        try {


            assertEquals(Position.parse("ml"), middleLeft);
            assertEquals(Position.parse("mL"), middleLeft);


            assertEquals(Position.parse("ML"), middleLeft);
            assertEquals(Position.parse("Middle Left"), middleLeft);
            assertEquals(Position.parse("mIDdlE LEft"), middleLeft);
            assertEquals(Position.parse("middle;left"), middleLeft);

            assertEquals(Position.parse("21"), middleLeft);
            assertEquals(Position.parse("2,1"), middleLeft);
            assertEquals(Position.parse("2;1"), middleLeft);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }


    @Test
    public void assertExceptionThrownByInvalidNumber() {

        ParseException exception = assertThrows(
                ParseException.class,
                () -> Position.parse("444"),
                "Expected big value to throw a ParseException, but it did not."
        );

        ParseException exception4 = assertThrows(
                ParseException.class,
                () -> Position.parse("00"),
                "Expected zeroes to throw a ParseException, but it did not."
        );
    }

    @Test
    public void assertExceptionThrownByLackofSpace() {

        ParseException exception2 = assertThrows(
                ParseException.class,
                () -> Position.parse("topmiddle"),
                "Expected unseparated position to throw a ParseException, but it did not."
        );
    }

    @Test
    public void assertExceptionThrownByInvalidStrings() {

        ParseException exception3 = assertThrows(
                ParseException.class,
                () -> Position.parse("cat;dog"),
                "Expected incorrect but separated string to throw a ParseException, but it did not."
        );


    }


    @Test
    public void assertExceptionThrownByEmptyString() {

        ParseException exception5 = assertThrows(
                ParseException.class,
                () -> Position.parse("  "),
                "Expected empty string to throw a ParseException, but it did not."
        );
    }


    @Test
    public void assertExceptionThrownByNull() {
        NullPointerException exception6 = assertThrows(
                NullPointerException.class,
                () -> Position.parse(null),
                "Expected null value to throw a NullPointerException, but it did not."
        );
    }

    @Test
    public void assertExceptionThrownByIncorrectSeparatorPlacement() {
        ParseException exception7 = assertThrows(
                ParseException.class,
                () -> Position.parse("12;"),
                "Expected incorrect argument string to throw a ParseException, but it did not."
        );
    }
@Test
    public void assertExceptionThrownByIncorrectSeparator(){
    ParseException exception7 = assertThrows(
            ParseException.class,
            () -> Position.parse("3:3"),
            "Expected incorrect separator in string to throw a ParseException, but it did not."
    );
}

}




