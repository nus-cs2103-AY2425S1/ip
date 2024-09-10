package joe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import joe.command.Command;
import joe.command.DeadlineCommand;
import joe.command.DeleteCommand;
import joe.command.EventCommand;
import joe.command.ExitCommand;
import joe.command.FindCommand;
import joe.command.ListCommand;
import joe.command.MarkCommand;
import joe.command.TodoCommand;
import joe.command.UnmarkCommand;

class ParserTest {

    @Test
    void testEmptyInput() {
        assertThrows(JoeException.class, () -> Parser.parse(""),
                "\tOOPS! You did not enter anything");
    }

    @Test
    void testUnknownCommand() {
        assertThrows(JoeException.class, () -> Parser.parse("gibberish"), "Unknown command");
    }

    @Test
    void testByeCommand() {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void testListCommand() {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void testTodoCommandValidInput() {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TodoCommand.class, command);
        assertEquals(new TodoCommand("read book"), command);
    }

    @Test
    void testTodoCommandEmptyDescription() {
        assertThrows(JoeException.class, () -> Parser.parse("todo"),
                "OOPS!!! The description of a todo cannot be empty.");
    }

    @Test
    void testDeadlineCommandValidInput() {
        Command command = Parser.parse("deadline submit report /by 2024-09-10");
        assertInstanceOf(DeadlineCommand.class, command);
        assertEquals(new DeadlineCommand("submit report", "2024-09-10"), command);
    }

    @Test
    void testDeadlineCommandMissingBy() {
        assertThrows(JoeException.class, () -> Parser.parse("deadline submit report"),
                "Oops! Try adding it like this: deadline {task description} /by {duedate}");
    }

    @Test
    void testDeadlineCommandInvalidDate() {
        assertThrows(JoeException.class, () -> Parser.parse("deadline submit report /by 2024/09/10"),
                "Please enter a date with the format yyyy-mm-dd");
    }

    @Test
    void testEventCommandValidInput() {
        Command command = Parser.parse("event project meeting /from 2024-09-01 /to 2024-09-02");
        assertInstanceOf(EventCommand.class, command);
        assertEquals(new EventCommand("project meeting", "2024-09-01", "2024-09-02"),
                command);
    }

    @Test
    void testEventCommandMissingFrom() {
        assertThrows(JoeException.class, () -> Parser.parse("event project meeting"),
                "Oops! Let's try again with this format: event {task description} /from {start date} /to {end date}");
    }

    @Test
    void testDeleteCommand() {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void testMarkCommand() {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void testUnmarkCommand() {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    void testFindCommandValidInput() {
        Command command = Parser.parse("find book");
        assertInstanceOf(FindCommand.class, command);
    }

}
