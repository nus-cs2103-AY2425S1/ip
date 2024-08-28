package monique.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import monique.command.AddCommand;
import monique.command.ByeCommand;
import monique.command.Command;
import monique.command.ListCommand;
import monique.command.UnknownCommand;
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
    void testParseAddDeadlineCommand() {
        Command command = Parser.parse("deadline return book /by 8/28/2024");
        assertTrue(command instanceof AddCommand);
        assertTrue(((AddCommand) command).getTask() instanceof Deadline);
        assertEquals(((AddCommand) command).getTask().getDescription(), "return book");
        assertEquals(((Deadline) ((AddCommand) command).getTask()).getBy(),
                LocalDate.parse("8/28/2024", DateTimeFormatter.ofPattern("M/d/yyyy")));
    }

    @Test
    void testParseAddEventCommand() {
        Command command = Parser.parse("event project meeting /from 8/28/2024 /to 8/29/2024");
        assertTrue(command instanceof AddCommand);
        assertTrue(((AddCommand) command).getTask() instanceof Event);
        assertEquals(((AddCommand) command).getTask().getDescription(), "project meeting");
        assertEquals(((Event) ((AddCommand) command).getTask()).getFrom(), LocalDate.parse("8/28/2024", DateTimeFormatter.ofPattern("M/d/yyyy")));
        assertEquals(((Event) ((AddCommand) command).getTask()).getTo(), LocalDate.parse("8/29/2024", DateTimeFormatter.ofPattern("M/d/yyyy")));
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

