package PHamBot.parser;

import Johnson.command.ToDoCommand;
import Johnson.exceptions.MissingDateException;
import Johnson.exceptions.MissingDividerException;
import Johnson.exceptions.MissingTaskException;
import Johnson.exceptions.UnknownCommandException;
import Johnson.parser.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_returnsCorrectCommand() throws MissingDateException, MissingTaskException, UnknownCommandException, MissingDividerException {
        Parser parser = new Parser();
        assertEquals(parser.parseCommand("todo read book").getClass(), ToDoCommand.class);
    }
}
