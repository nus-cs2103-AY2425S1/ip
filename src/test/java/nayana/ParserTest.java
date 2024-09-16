package nayana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nayana.command.AddCommand;
import nayana.command.Command;
import nayana.command.DeleteCommand;
import nayana.command.ExitCommand;
import nayana.command.FindCommand;
import nayana.command.ListCommand;
import nayana.command.MarkCommand;
import nayana.command.UnmarkCommand;
import nayana.task.Deadline;
import nayana.task.Event;
import nayana.task.ToDos;

class ParserTest {

    @Test
    void testParseValidCommands() throws NayanaException {
        // Test valid commands
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand, "Command should be an instance of ExitCommand.");

        command = Parser.parse("list");
        assertTrue(command instanceof ListCommand, "Command should be an instance of ListCommand.");

        command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand, "Command should be an instance of MarkCommand.");

        command = Parser.parse("unmark 2");
        assertTrue(command instanceof UnmarkCommand, "Command should be an instance of UnmarkCommand.");

        command = Parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand, "Command should be an instance of DeleteCommand.");

        command = Parser.parse("deadline Homework /by 2024-09-01");
        assertTrue(command instanceof AddCommand, "Command should be an instance of AddCommand.");
        assertTrue(((AddCommand) command).getTask() instanceof Deadline, "Task should be an instance of Deadlines.");

        command = Parser.parse("event Meeting /from 2024-09-01 /to 2024-09-02");
        assertTrue(command instanceof AddCommand, "Command should be an instance of AddCommand.");
        assertTrue(((AddCommand) command).getTask() instanceof Event, "Task should be an instance of Event.");

        command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof AddCommand, "Command should be an instance of AddCommand.");
        assertTrue(((AddCommand) command).getTask() instanceof ToDos, "Task should be an instance of ToDos.");

        command = Parser.parse("find keyword");
        assertTrue(command instanceof FindCommand, "The command should be of type FindCommand.");
        assertEquals("keyword", ((FindCommand) command).getFindValue(), "The find value should be 'keyword'.");
    }

    @Test
    void testParseInvalidCommands() {
        // Test invalid commands
        assertThrows(NayanaException.class, () -> Parser.parse("unknown"),
              "Should throw NayanaException for unknown command.");

        assertThrows(NayanaException.class, () -> Parser.parse("mark"),
              "Should throw NayanaException for invalid mark command format.");
        assertThrows(NayanaException.class, () -> Parser.parse("unmark"),
              "Should throw NayanaException for invalid unmark command format.");
        assertThrows(NayanaException.class, () -> Parser.parse("delete"),
              "Should throw NayanaException for invalid delete command format.");

        assertThrows(NayanaException.class, () -> Parser.parse("deadline Homework"),
              "Should throw NayanaException for missing /by in deadline command.");
        assertThrows(NayanaException.class, () -> Parser.parse("event Meeting /from 2024-09-01"),
              "Should throw NayanaException for missing /to in event command.");
        assertThrows(NayanaException.class, () -> Parser.parse("todo"),
              "Should throw NayanaException for empty description in todo command.");

        // Test invalid date formats
        assertThrows(NayanaException.class, () -> Parser.parse("deadline Homework /by 01-09-2024"),
              "Should throw NayanaException for invalid date format.");
        assertThrows(NayanaException.class, () ->
                    Parser.parse("event Meeting /from 01-09-2024 /to 2024-09-02"),
              "Should throw NayanaException for invalid start date format.");
        assertThrows(NayanaException.class, () -> Parser.parse("find "),
              "Parsing an invalid find command should throw NayanaException.");
    }
}

