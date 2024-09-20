package yoda.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import yoda.TaskList;
import yoda.exceptions.YodaException;
import yoda.tasks.Todo;

public class UnmarkCommandTest {
    @Test
    public void run_validInput_success() throws YodaException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("sleep"));
        MarkCommand markCommand = new MarkCommand(taskList, "mark 1");
        markCommand.run();
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskList, "unmark 1");

        unmarkCommand.run();

        assertEquals(" ", taskList.get(0).getStatusIcon());
    }

    @Test
    public void run_invalidInput_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("sleep"));
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskList, "unmark");
        try {
            unmarkCommand.run();
        } catch (Exception e) {
            assertEquals("Unmark... which one?\n"
                    + "Command should be in format: unmark [number]", e.getMessage());
        }
    }

    @Test
    public void run_inputOutOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("sleep"));
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskList, "unmark 2");
        try {
            unmarkCommand.run();
        } catch (Exception e) {
            assertEquals("Unmark... which one?\n"
                    + "Command should be in format: unmark [number]", e.getMessage());
        }
    }

    @Test
    public void run_nonsenseInput_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("sleep"));
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskList, "sleep");
        try {
            unmarkCommand.run();
        } catch (Exception e) {
            assertEquals("Unmark... which one?\n"
                    + "Command should be in format: unmark [number]", e.getMessage());
        }
    }

    @Test
    public void checkForValidInt_validInput_returnTrue() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("sleep"));
        assertTrue(new UnmarkCommand(taskList, "unmark 1").hasValidFormat());
    }
    @Test
    public void checkValidToDo_invalidInput_returnFalse() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("sleep"));

        assertFalse(new UnmarkCommand(taskList, "unmark").hasValidFormat());
        assertFalse(new UnmarkCommand(taskList, "").hasValidFormat());
        assertFalse(new UnmarkCommand(taskList, "sleep").hasValidFormat());
    }
}
