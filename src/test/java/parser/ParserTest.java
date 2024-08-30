package parser;

import org.junit.jupiter.api.Test;

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


}