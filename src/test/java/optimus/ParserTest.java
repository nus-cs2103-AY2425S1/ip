package optimus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import optimus.commands.AddTaskCommand;
import optimus.commands.MarkCommand;
import optimus.exceptions.OptimusExceptions;
import optimus.tasks.DeadlineTask;


public class ParserTest {

    @Test
    public void markTest() {
        try {
            MarkCommand mc = (MarkCommand) Parser.parse("mark 4");
            assertEquals(3, mc.getTaskIndex());
        } catch (OptimusExceptions e) {
            fail(e.getMessage());
        } catch (ClassCastException e) {
            fail("Wrong command produced");
        }
    }

    @Test
    public void incompleteMarkTest() {
        try {
            MarkCommand mc = (MarkCommand) Parser.parse("mark ");
            fail("Accepted mark command without task number");
        } catch (OptimusExceptions e) {
            assertEquals("The task number is not specified", e.getMessage());
        } catch (ClassCastException e) {
            fail("Wrong command produced");
        }
    }

    @Test
    public void successfulDeadlineTest() {
        try {
            AddTaskCommand addTaskCommand = (AddTaskCommand) Parser.parse("deadline test /by 2024-08-28");
            DeadlineTask testCommand = new DeadlineTask("test", "2024-08-28");
            assertEquals(addTaskCommand.getTask(), testCommand);
        } catch (OptimusExceptions e) {
            fail(e.getMessage());
        } catch (ClassCastException e) {
            fail("Wrong command produced");
        }
    }

    @Test
    public void incompleteDeadlineTest() {
        try {
            AddTaskCommand addTaskCommand = (AddTaskCommand) Parser.parse("deadline test /by ");
            fail("Accepted deadline command without deadline");
        } catch (OptimusExceptions e) {
            assertEquals("Deadline Tasks must have a deadline specified", e.getMessage());
        } catch (ClassCastException e) {
            fail("Wrong command produced");
        }
    }

    @Test
    public void incorrectDateFormatTest() {
        try {
            AddTaskCommand addTaskCommand = (AddTaskCommand) Parser.parse("deadline test /by today");
            fail("Accepted deadline command with incorrect deadline format");
        } catch (OptimusExceptions e) {
            assertEquals("Dates for Deadline tasks must be in the YYYY-MM-DD format", e.getMessage());
        } catch (ClassCastException e) {
            fail("Wrong command produced");
        }
    }

}
