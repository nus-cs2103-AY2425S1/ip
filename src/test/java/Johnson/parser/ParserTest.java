package Johnson.parser;

import Johnson.command.Command;
import Johnson.command.DeadlineCommand;
import Johnson.command.ToDoCommand;
import Johnson.exceptions.MissingDateException;
import Johnson.exceptions.MissingDividerException;
import Johnson.exceptions.MissingTaskException;
import Johnson.exceptions.UnknownCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseCommand_returnsCorrectCommand() throws MissingDateException, MissingTaskException, UnknownCommandException, MissingDividerException {
        Parser parser = new Parser();
        assertEquals(parser.parseCommand("todo read book").getClass(), ToDoCommand.class);
    }

    @Test
    void testFullInput() throws MissingTaskException, MissingDateException, MissingDividerException, UnknownCommandException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("deadline submit report /by 2024-12-12 12:00 /#work #urgent");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    void testMissingTask() {
        Parser parser = new Parser();
        assertThrows(MissingTaskException.class, () -> {
            parser.parseCommand("deadline /by 2024-12-12 12:00 /#work #urgent");
        });
    }

    @Test
    void testNoSpaceBetweenSlash() throws MissingDateException, MissingTaskException, UnknownCommandException, MissingDividerException {
        Parser parser = new Parser();
        assertTrue(parser.parseCommand("deadline submit report/by 2024-12-12 12:00/#work #urgent") instanceof DeadlineCommand);
    }

    @Test
    void testNoByFromOn() throws MissingDateException, MissingTaskException, UnknownCommandException, MissingDividerException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("deadline submit report / 2024-12-12 12:00 /#work #urgent");
        assertTrue(command instanceof DeadlineCommand);

    }

    @Test
    void testNoHashtagForTags() {
        Parser parser = new Parser();
        assertThrows(UnknownCommandException.class, () -> {
            parser.parseCommand("deadline submit report /by 2024-12-12 12:00 /work urgent");
        });
    }

    @Test
    void testNoDate() {
        Parser parser = new Parser();
        assertThrows(MissingDateException.class, () -> {
            parser.parseCommand("deadline submit report /by /#work #urgent");
        });
    }

    @Test
    void testNoTags() throws MissingTaskException, MissingDateException, MissingDividerException, UnknownCommandException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("deadline submit report /by 2024-12-12 12:00");
        assertTrue(command instanceof DeadlineCommand);
    }
}
