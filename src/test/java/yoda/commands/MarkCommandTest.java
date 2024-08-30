package yoda.commands;

import org.junit.jupiter.api.Test;
import yoda.TaskList;
import yoda.tasks.ToDo;
import yoda.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {
    @Test
    public void run_validInput_success() throws InvalidInputException {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("sleep"));
        MarkCommand command = new MarkCommand(taskList, "mark 1");

        command.run();

        assertEquals("X", taskList.get(0).getStatusIcon());
    }

    @Test
    public void run_invalidInput_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("sleep"));
        MarkCommand command = new MarkCommand(taskList, "mark");
        try {
            command.run();
            fail();
        } catch (Exception e) {
            assertEquals("Mark... which one?", e.getMessage());
        }
    }

    @Test
    public void run_inputOutOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("sleep"));
        MarkCommand command = new MarkCommand(taskList, "mark 3");
        try {
            command.run();
            fail();
        } catch (Exception e) {
            assertEquals("Mark... which one?", e.getMessage());
        }
    }

    @Test
    public void run_nonsenseInput_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("sleep"));
        MarkCommand command = new MarkCommand(taskList, "sleep");
        try {
            command.run();
            fail();
        } catch (Exception e) {
            assertEquals("Mark... which one?", e.getMessage());
        }
    }

    @Test
    public void checkForValidInt_validInput_returnTrue() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("sleep"));
        assertTrue(new MarkCommand(taskList, "mark 1").hasValidFormat());
    }
    @Test
    public void checkValidToDo_invalidInput_returnFalse() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("sleep"));

        assertFalse(new MarkCommand(taskList, "mark").hasValidFormat());
        assertFalse(new MarkCommand(taskList, "").hasValidFormat());
        assertFalse(new MarkCommand(taskList, "sleep").hasValidFormat());
    }
}
