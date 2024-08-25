package diego;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseCommand_addTodoCommand() throws DiegoException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("todo read book");
        assertInstanceOf(AddTodoCommand.class, command);
    }

    @Test
    void testParseCommand_addDeadlineCommand() throws DiegoException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("deadline return book /by 2023-10-01");
        assertInstanceOf(AddDeadlineCommand.class, command);
    }

    @Test
    void testParseCommand_addEventCommand() throws DiegoException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("event project meeting /from 2023-10-01 1400 /to 1600");
        assertInstanceOf(AddEventCommand.class, command);
    }

    @Test
    void testParseCommand_unknownCommand() {
        Parser parser = new Parser();
        assertThrows(UnknownCommandException.class, () -> parser.parseCommand("unknown command"));
    }
}
