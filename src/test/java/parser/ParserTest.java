package parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chatterboxexceptions.ChatterboxExceptions;


public class ParserTest {

    private final Parser testParser = new Parser();


    @Test
    public void parseCommand_byeCommand() {
        assertEquals(Parser.ValidCommand.BYE, testParser.parseCommand("bye bye sas"));

    }

    @Test
    public void parseCommand_invalidCommand() {
        assertEquals(Parser.ValidCommand.INVALID, testParser.parseCommand("baye bye"));

    }

    @Test
    public void extractNum_normalText() {
        assertEquals(43, testParser.extractNum("unmark 43"));

    }

    @Test
    public void extractNum_connectedText() {
        assertEquals(43, testParser.extractNum("unmark sadf43"));

    }

    @Test
    public void extractNum_notNum_exceptionThrown() {
        try {
            assertEquals("asd", testParser.extractNum("mark asd"));

        } catch (NumberFormatException e) {
            assertEquals("For input string: \"\"", e.getMessage());
        }
    }

    @Test
    public void parseTodo_todoText() {

        assertEquals("go home", testParser.parseTodo("todo go home"));
    }

    @Test void parseTodo_byeText() {

        assertEquals("bye bye", testParser.parseTodo("todo bye bye"));
    }
    @Test
    public void parseDeadline_deadlineText() {
        String[] expected = {"homework", "tmr"};
        try {
            assertArrayEquals(expected, testParser.parseDeadline("deadline homework /by tmr"));

        } catch (ChatterboxExceptions.ChatterBoxMissingParameter e) {
            System.out.println("Fail");
        }
    }

    @Test
    public void parseDeadline_deadlineSlash() {
        try {
            assertArrayEquals(new String[]{"deadline text", "/by next year"},
                    testParser.parseDeadline("deadline deadline text /by/by next year"));
        } catch (ChatterboxExceptions.ChatterBoxMissingParameter e) {
            System.out.println("error");
        }
    }

    @Test
    public void parseEvent_normalText() {
        try {
            assertArrayEquals(new String[] {"event start", "4pm", "9pm"},
                    testParser.parseEvent("event event start /from 4pm /to 9pm"));
        } catch (ChatterboxExceptions.ChatterBoxMissingParameter e) {
            System.out.println("error" + e.getMessage());
        }
    }

    @Test
    public void parseEvent_plainText() {
        try {
            assertArrayEquals(new String[] {"start", "4pm", "9pm"},
                    testParser.parseEvent("event start /from 4pm /to 9pm"));
        } catch (ChatterboxExceptions.ChatterBoxMissingParameter e) {
            System.out.println("error" + e.getMessage());
        }
    }

    @Test
    public void parseEvent_eventSlash() {
        try {
            assertArrayEquals(new String[] {"first text", "/from abc", "/to end"},
                    testParser.parseEvent("event first text /from/from abc /to /to end"));
        } catch (ChatterboxExceptions.ChatterBoxMissingParameter e) {
            System.out.println("error" + e.getMessage());
        }
    }

    @Test
    public void parseEvent_repeatCommands() {
        try {
            assertArrayEquals(new String[] {"event text", "/from abc", "/from/to/to asdf"},
                    testParser.parseEvent("event event text /from/from abc /to/from/to/to asdf"));
        } catch (ChatterboxExceptions.ChatterBoxMissingParameter e) {
            System.out.println("error" + e.getMessage());
        }
    }
}
