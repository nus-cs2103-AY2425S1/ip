package lawrence.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import lawrence.command.AddTaskCommand;
import lawrence.command.Command;
import lawrence.command.CompleteTaskCommand;
import lawrence.command.DeleteTaskCommand;
import lawrence.command.DisplayTasksCommand;
import lawrence.command.ExitSessionCommand;
import lawrence.command.UncompleteTaskCommand;

public class CommandParserTest {
    @Test
    public void createCommand_exitCommand_returnsExitSessionCommand() {
        Command command = CommandParser.createCommand("bye");
        assertInstanceOf(ExitSessionCommand.class, command);
    }

    @Test
    public void createCommand_displayCommand_returnsDisplayTasksCommand() {
        Command command = CommandParser.createCommand("list");
        assertInstanceOf(DisplayTasksCommand.class, command);
    }

    @Test
    public void createCommand_markCompleteCommand_returnsCompleteTaskCommand() {
        Command command = CommandParser.createCommand("mark 1");
        assertInstanceOf(CompleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_markCompleteCommandWithMissingValue_returnsCompleteTaskCommand() {
        Command command = CommandParser.createCommand("mark");
        assertInstanceOf(CompleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_markCompleteCommandWithInvalidValue_returnsCompleteTaskCommand() {
        Command command = CommandParser.createCommand("mark ;");
        assertInstanceOf(CompleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_markIncompleteCommand_returnsUncompleteTaskCommand() {
        Command command = CommandParser.createCommand("unmark 1");
        assertInstanceOf(UncompleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_markIncompleteCommandWithMissingValue_returnsUncompleteTaskCommand() {
        Command command = CommandParser.createCommand("unmark");
        assertInstanceOf(UncompleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_markIncompleteCommandWithInvalidValue_returnsUncompleteTaskCommand() {
        Command command = CommandParser.createCommand("unmark -1");
        assertInstanceOf(UncompleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_deleteCommand_returnsDeleteTaskCommand() {
        Command command = CommandParser.createCommand("delete 1");
        assertInstanceOf(DeleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_deleteCommandWithNoValue_returnsDeleteTaskCommand() {
        Command command = CommandParser.createCommand("delete");
        assertInstanceOf(DeleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_deleteCommandWithInvalidValue_returnsDeleteTaskCommand() {
        Command command = CommandParser.createCommand("delete a");
        assertInstanceOf(DeleteTaskCommand.class, command);
    }

    @Test
    public void createCommand_addTodoCommand_returnsAddTaskCommand() {
        Command command = CommandParser.createCommand("todo task description");
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void createCommand_addTodoCommandWithMissingDescription_returnsAddTaskCommand() {
        Command command = CommandParser.createCommand("todo");
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void createCommand_addDeadlineCommand_returnsAddTaskCommand() {
        Command command = CommandParser.createCommand("deadline task description /by 2024-09-30 10:00");
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void createCommand_addDeadlineCommandWithMissingTime_returnsAddTaskCommand() {
        Command command = CommandParser.createCommand("deadline task description");
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void createCommand_addEventCommand_returnsAddTaskCommand() {
        Command command = CommandParser.createCommand(
                "event task description /from 2024-09-01 10:00 /to 2024-09-30 12:00");
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void createCommand_addEventCommandWithMissingDescriptionAndFromTime_returnsAddTaskCommand() {
        Command command = CommandParser.createCommand(
                "event task /to 2024-09-30 12:00");
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void createCommand_invalidCommand_throwsIllegalArgumentException() {
        try {
            CommandParser.createCommand("blah blah blah");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("No command type found for: blah.", e.getMessage());
        }
    }

    @Test
    public void createCommand_noCommandProvided_throwsIllegalArgumentException() {
        try {
            CommandParser.createCommand("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Command input cannot be empty!", e.getMessage());
        }
    }
}
