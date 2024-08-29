package shrimp.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void testParseCommandBye() {
        String userInput = "bye";
        assertEquals(Parser.CommandType.BYE, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandList() {
        String userInput = "list";
        assertEquals(Parser.CommandType.LIST, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandMark() {
        String userInput = "mark 1";
        assertEquals(Parser.CommandType.MARK, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandUnmark() {
        String userInput = "unmark 1";
        assertEquals(Parser.CommandType.UNMARK, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandDeadline() {
        String userInput = "deadline return book /by 2023-12-02 1800";
        assertEquals(Parser.CommandType.DEADLINE, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandEvent() {
        String userInput = "event project meeting /from 2023-12-02 1400 /to 2023-12-02 1600";
        assertEquals(Parser.CommandType.EVENT, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandAdd() {
        String userInput = "todo read a book";
        assertEquals(Parser.CommandType.ADD, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandDelete() {
        String userInput = "delete 1";
        assertEquals(Parser.CommandType.DELETE, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandClear() {
        String userInput = "clear";
        assertEquals(Parser.CommandType.CLEAR, Parser.parseCommand(userInput));
    }

    @Test
    void testParseCommandInvalid() {
        String userInput = "invalid command";
        assertEquals(Parser.CommandType.ERROR, Parser.parseCommand(userInput));
    }
}
