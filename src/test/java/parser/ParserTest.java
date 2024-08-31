package parser;

import chatterboxexceptions.ChatterboxExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Parser testParser = new Parser();
    
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }

    @Test
    public void parseCommand_byeCommand() {
        assertEquals(Parser.VALID_COMMAND.BYE, testParser.parseCommand("bye bye sas"));

    }

    @Test
    public void parseCommand_invalCommand() {
        assertEquals(Parser.VALID_COMMAND.INVALID, testParser.parseCommand("baye bye"));

    }

    @Test
    public void extractNum_43(){
        assertEquals(43, testParser.extractNum("unmark 43"));

    }

    @Test
    public void extractNum_21(){
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

        assertEquals("go home", testParser.parseTODO("todo go home"));
    }

    @Test void parseTodo_byeText() {

        assertEquals("bye bye", testParser.parseTODO("todo bye bye"));
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
}