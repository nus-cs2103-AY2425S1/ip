package echoa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static Parser parser = new Parser();

    @Test
    public void testParseCommand_List() {
        String input = "list";
        String actual = parser.parseCommand(input);
        String expected = "list";
        assertEquals(expected, actual, "The toText() method should return " + expected);
    }

    @Test
    public void testParseCommand_Mark() {
        String input = "mark 1";
        String actual = parser.parseCommand(input);
        String expected = "mark";
        assertEquals(expected, actual, "The toText() method should return " + expected);
    }

    @Test
    public void testParseCommand_Unmark() {
        String input = "unmark hello";
        String actual = parser.parseCommand(input);
        String expected = "unmark";
        assertEquals(expected, actual, "The toText() method should return " + expected);
    }

    @Test
    public void testParseCommand_Delete() {
        String input = "delete this";
        String actual = parser.parseCommand(input);
        String expected = "delete";
        assertEquals(expected, actual, "The toText() method should return " + expected);
    }

    @Test
    public void testParseCommand_ToDo() {
        String input = "todo reading";
        String actual = parser.parseCommand(input);
        String expected = "todo";
        assertEquals(expected, actual);
    }

    @Test
    public void testParseCommand_Deadline() {
        String input = "deadline ip /2024-08-30 16:00 ";
        String actual = parser.parseCommand(input);
        String expected = "deadline";
        assertEquals(expected, actual);
    }

    @Test
    public void testParseCommand_Event() {
        String input = "event work";
        String actual = parser.parseCommand(input);
        String expected = "event";
        assertEquals(expected, actual);
    }

    @Test
    public void testParseCommand_Bye1() {
        String input = "bye";
        String actual = parser.parseCommand(input);
        String expected = "bye";
        assertEquals(expected, actual);
    }

    @Test
    public void testParseCommand_Bye2() {
        String input = " bye echoa";
        String actual = parser.parseCommand(input);
        String expected = "bye";
        assertEquals(expected, actual);
    }

    @Test
    public void testParseCommand_Default() {
        String input = "blahblahblah";
        String actual = parser.parseCommand(input);
        String expected = "blahblahblah";
        assertEquals(expected, actual);
    }

    @Test
    public void testParseCommand_Blank() {
        String input = "      ";
        String actual = parser.parseCommand(input);
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void testParseCommand_Whitespace() {
        String input = "   todo submit work    ";
        String actual = parser.parseCommand(input);
        String expected = "todo";
        assertEquals(expected, actual);
    }
}
