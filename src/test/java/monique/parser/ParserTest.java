package monique.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import monique.command.AddCommand;
import monique.command.ByeCommand;
import monique.command.Command;
import monique.command.ListCommand;
import monique.command.UnknownCommand;
import monique.exception.IllegalDateFormatException;
import monique.task.Deadline;
import monique.task.Event;
import monique.task.ToDo;




public class ParserTest {

    @Test
    public void parse_withEmptyString_unknownCommand() {
        assertEquals(new UnknownCommand(), Parser.parse(""));
    }

    @Test
    void testParseByeCommand() {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    void testParseListCommand() {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void testParseAddTodoCommand() {
        Command command = Parser.parse("todo read a book");
        assertTrue(command instanceof AddCommand);
        assertTrue(((AddCommand) command).getTask() instanceof ToDo); // Assuming getTask() is defined
        assertEquals(((AddCommand) command).getTask().getDescription(), "read a book");
    }

    @Test
    void testParseAddDeadlineCommand() throws IllegalDateFormatException {
        Command command = Parser.parse("deadline return book /by mon");
        assertTrue(command instanceof AddCommand);
        assertTrue(((AddCommand) command).getTask() instanceof Deadline);
        assertEquals(((AddCommand) command).getTask().getDescription(), "return book");
        assertEquals(((Deadline) ((AddCommand) command).getTask()).getBy(),
                DateParser.getDateTimeString("mon"));
    }

    @Test
    void testParseAddEventCommand() throws IllegalDateFormatException {
        Command command = Parser.parse("event project meeting /from tomorrow 5pm /to tomorrow 6pm");
        assertTrue(command instanceof AddCommand);
        assertTrue(((AddCommand) command).getTask() instanceof Event);
        assertEquals(((AddCommand) command).getTask().getDescription(), "project meeting");
        assertEquals(((Event) ((AddCommand) command).getTask()).getFrom(),
                DateParser.getDateTimeString("tomorrow 5pm"));
        assertEquals(((Event) ((AddCommand) command).getTask()).getTo(),
                DateParser.getDateTimeString("tomorrow 6pm"));
    }

    @Test
    void testParseInvalidMarkCommand() {
        Command command = Parser.parse("mark");
        assertTrue(command instanceof UnknownCommand); // Assuming error handling falls back to UnknownCommand
    }

    @Test
    void testParseInvalidDeleteCommand() {
        Command command = Parser.parse("delete abc");
        assertTrue(command instanceof UnknownCommand); // Assuming error handling falls back to UnknownCommand
    }

    @Test
    void testParseInvalidEventCommand() {
        Command command = Parser.parse("event project meeting /from Monday");
        assertTrue(command instanceof UnknownCommand); // Assuming error handling falls back to UnknownCommand
    }
}

