package Echoa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParserTest {

    private static Parser parser = new Parser();

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    @Test
    public void parseCommandTest1() {
        String input = "list";
        String actual = parser.parseCommand(input);
        String expected = "list";
        assertEquals(expected, actual, "The toText() method should return " + expected);
    }

    @Test
    public void parseCommandTest2() {
        String input = "mark 1";
        String actual = parser.parseCommand(input);
        String expected = "mark";
        assertEquals(expected, actual, "The toText() method should return " + expected);
    }

    @Test
    public void parseCommandTest3() {
        String input = "unmark hello";
        String actual = parser.parseCommand(input);
        String expected = "unmark";
        assertEquals(expected, actual, "The toText() method should return " + expected);
    }

    @Test
    public void parseCommandTest4() {
        String input = "      delete this   ";
        String actual = parser.parseCommand(input);
        String expected = "delete";
        assertEquals(expected, actual, "The toText() method should return " + expected);
    }

    @Test
    public void parseCommandTest5() {
        String input = "todo reading";
        String actual = parser.parseCommand(input);
        String expected = "todo";
        assertEquals(expected, actual);
    }

    @Test
    public void parseCommandTest6() {
        String input = "deadline ip /2024-08-30 16:00 ";
        String actual = parser.parseCommand(input);
        String expected = "deadline";
        assertEquals(expected, actual);
    }

    @Test
    public void parseCommandTest7() {
        String input = " event work";
        String actual = parser.parseCommand(input);
        String expected = "event";
        assertEquals(expected, actual);
    }

    @Test
    public void parseCommandTest8() {
        String input = "bye";
        String actual = parser.parseCommand(input);
        String expected = "bye";
        assertEquals(expected, actual);
    }

    @Test
    public void parseCommandTest9() {
        String input = " bye echoa";
        String actual = parser.parseCommand(input);
        String expected = "bye";
        assertEquals(expected, actual);
    }

    @Test
    public void parseCommandTest10() {
        String input = "blahblahblah";
        String actual = parser.parseCommand(input);
        String expected = "blahblahblah";
        assertEquals(expected, actual);
    }

    @Test
    public void parseCommandTest11() {
        String input = "      ";
        String actual = parser.parseCommand(input);
        String expected = "";
        assertEquals(expected, actual);
    }
}
