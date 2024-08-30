package puke.handlers;

import org.junit.jupiter.api.Test;
import puke.exceptions.*;
import puke.tasklist.TaskManager;
import puke.tasks.Deadline;
import puke.tasks.Event;
import puke.tasks.Task;
import puke.tasks.Todo;
import puke.ui.MessageBuilder;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the InputManager class.
 */
public class InputManagerTest {

    /**
     * Tests handling of empty input.
     */
    @Test
    public void testEmptyInput() {
        InputManager inputManager = new InputManager(new TaskManager(new ArrayList<>()), new MessageBuilder());
        Exception exception = assertThrows(EmptyInputException.class, () -> {
            inputManager.handleInput(" ");
        });
        assertTrue(exception.getMessage().contains("OOPS!!! You need to enter a command."));
    }

    /**
     * Tests successful addition of a Todo task.
     */
    @Test
    public void successfulTodoTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskManager taskManager = new TaskManager(tasks);
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());
        Task expectedTask = new Todo("Task #1", false);
        try {
            inputManager.handleInput("todo Task #1");
            Task addedTask = taskManager.getTask(0);
            assertEquals(expectedTask, addedTask);
        } catch (PukeException e) {
            fail("Exception should not have been thrown");
        }
    }

    /**
     * Tests successful addition of a Deadline task.
     */
    @Test
    public void successfulDeadlineTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskManager taskManager = new TaskManager(tasks);
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());
        Task expectedTask = new Deadline("Task #2", false, "01/01/2024 1800");
        try {
            inputManager.handleInput("deadline Task #2 /by 01/01/2024 1800");
            Task addedTask = taskManager.getTask(0);
            assertEquals(expectedTask, addedTask);
        } catch (PukeException e) {
            fail("Exception should not have been thrown");
        }
    }

    /**
     * Tests successful addition of an Event task.
     */
    @Test
    public void successfulEventTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskManager taskManager = new TaskManager(tasks);
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());
        Task expectedTask = new Event("Task #3", false, "01/01/2024 1800", "02/02/2024 1000");
        try {
            inputManager.handleInput("event Task #3 /from 01/01/2024 1800 /to 02/02/2024 1000");
            Task addedTask = taskManager.getTask(0);
            assertEquals(expectedTask, addedTask);
        } catch (PukeException e) {
            fail("Exception should not have been thrown");
        }
    }

    /**
     * Tests that a Deadline task without a specified time throws an exception.
     */
    @Test
    public void deadlineWithoutDateThrowsException() {
        TaskManager taskManager = new TaskManager(new ArrayList<>());
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());

        Exception exception = assertThrows(MissingTimeException.class, () -> {
            inputManager.handleInput("deadline Task #4");
        });
        assertEquals("OOPS!!! The deadline must have a specified time.", exception.getMessage());
    }

    /**
     * Tests that an Event task missing either start or end time throws an exception.
     */
    @Test
    public void eventWithoutStartOrEndTimeThrowsException() {
        TaskManager taskManager = new TaskManager(new ArrayList<>());
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());

        Exception exception1 = assertThrows(MissingEventTimeException.class, () -> {
            inputManager.handleInput("event Task #5 /from /to 02/02/2024 1200");  // Intentionally missing start time
        });
        assertEquals("OOPS!!! An event must have both start and end times specified.", exception1.getMessage());

        Exception exception2 = assertThrows(MissingEventTimeException.class, () -> {
            inputManager.handleInput("event Task #5 /from 01/01/2024 1800 /to");  // Intentionally missing end time
        });
        assertEquals("OOPS!!! An event must have both start and end times specified.", exception2.getMessage());
    }

    /**
     * Tests successful marking of a task.
     */
    @Test
    public void markTaskSuccessfully() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskManager taskManager = new TaskManager(tasks);
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());
        taskManager.addTask("todo","Task #6" );

        try {
            inputManager.handleInput("mark 1");
            Task markedTask = taskManager.getTask(0);
            assertTrue(markedTask.isDone());
        } catch (PukeException e) {
            fail("Exception should not have been thrown");
        }
    }

    /**
     * Tests successful unmarking of a task.
     */
    @Test
    public void unMarkTaskSuccessfully() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskManager taskManager = new TaskManager(tasks);
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());
        taskManager.addTask("todo","Task #7" );

        try {
            inputManager.handleInput("mark 1");
            inputManager.handleInput("unmark 1");
            Task markedTask = taskManager.getTask(0);
            assertTrue(!markedTask.isDone());
        } catch (PukeException e) {
            fail("Exception should not have been thrown");
        }
    }

    /**
     * Tests that marking a task with an out-of-bounds index throws an exception.
     */
    @Test
    public void markTaskIndexOutOfBounds() {
        TaskManager taskManager = new TaskManager(new ArrayList<>());
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());

        Exception exception = assertThrows(TaskNumberOutOfBoundsException.class, () -> {
            inputManager.handleInput("mark 2");
        });
        assertEquals("OOPS!!! The task number " + 2 + " is out of bounds.", exception.getMessage());
    }

    /**
     * Tests that a Deadline task with an incorrect date format throws an exception.
     */
    @Test
    public void deadlineWithIncorrectDateFormatThrowsException() {
        TaskManager taskManager = new TaskManager(new ArrayList<>());
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());

        Exception exception = assertThrows(DateTimeParseException.class, () -> {
            inputManager.handleInput("deadline Task #8 /by 2024-01-01 1800");
        });
        assertTrue(exception.getMessage().contains("Text '2024-01-01 1800' could not be parsed"));
    }

    /**
     * Tests that an Event task with incorrect date formats for start or end times throws an exception.
     */
    @Test
    public void eventWithIncorrectDateFormatThrowsException() {
        TaskManager taskManager = new TaskManager(new ArrayList<>());
        InputManager inputManager = new InputManager(taskManager, new MessageBuilder());

        Exception exception1 = assertThrows(DateTimeParseException.class, () -> {
            inputManager.handleInput("event Task #9 /from 01-01-2024 1800 /to 02/02/2024 1000");
        });
        assertTrue(exception1.getMessage().contains("Text '01-01-2024 1800' could not be parsed"));

        Exception exception2 = assertThrows(DateTimeParseException.class, () -> {
            inputManager.handleInput("event Task #9 /from 01/01/2024 1800 /to 02-02-2024 1000");
        });
        assertTrue(exception2.getMessage().contains("Text '02-02-2024 1000' could not be parsed"));
    }
}
