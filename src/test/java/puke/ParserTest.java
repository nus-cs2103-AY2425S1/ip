package puke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import puke.exceptions.PukeException;
import puke.message.MessageBuilder;
import puke.task.Deadline;
import puke.task.Event;
import puke.task.Task;
import puke.task.Todo;

/**
 * Unit tests for the Parser class.
 */
public class ParserTest {

    /**
     * Tests handling of empty input.
     */
    @Test
    public void testEmptyInput() {
        Parser parser = new Parser(new TaskList(new ArrayList<>()), new MessageBuilder());
        String result = parser.handleInput(" ");
        assertTrue(result.contains("OOPS!!! You need to enter a command."));
    }

    /**
     * Tests successful addition of a Todo task.
     */
    @Test
    public void successfulTodoTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Parser parser = new Parser(taskList, new MessageBuilder());
        Task expectedTask = new Todo("Task #1", false);
        try {
            parser.handleInput("todo Task #1");
            Task addedTask = taskList.getTask(0);
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
        TaskList taskList = new TaskList(tasks);
        Parser parser = new Parser(taskList, new MessageBuilder());
        Task expectedTask = new Deadline("Task #2", false, "01/01/2024 1800");
        try {
            parser.handleInput("deadline Task #2 /by 01/01/2024 1800");
            Task addedTask = taskList.getTask(0);
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
        TaskList taskList = new TaskList(tasks);
        Parser parser = new Parser(taskList, new MessageBuilder());
        Task expectedTask = new Event("Task #3", false, "01/01/2024 1800", "02/02/2024 1000");
        try {
            parser.handleInput("event Task #3 /from 01/01/2024 1800 /to 02/02/2024 1000");
            Task addedTask = taskList.getTask(0);
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
        TaskList taskList = new TaskList(new ArrayList<>());
        Parser parser = new Parser(taskList, new MessageBuilder());
        String resultWithoutTime = parser.handleInput("deadline Task #4");
        assertTrue(resultWithoutTime.contains("OOPS!!! The deadline must have a specified time."));
    }

    /**
     * Tests that an Event task missing either start or end time throws an exception.
     */
    @Test
    public void eventWithoutStartOrEndTimeThrowsException() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Parser parser = new Parser(taskList, new MessageBuilder());
        String resultWithoutFrom = parser.handleInput("event Task #5 /from /to 02/02/2024 1200");
        assertTrue(resultWithoutFrom.contains("OOPS!!! An event must have both start and end times specified."));
        String resultWithoutTo = parser.handleInput("event Task #5 /from 01/01/2024 1800 /to");
        assertTrue(resultWithoutTo.contains("OOPS!!! An event must have both start and end times specified."));
    }

    /**
     * Tests successful marking of a task.
     */
    @Test
    public void markTaskSuccessfully() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Parser parser = new Parser(taskList, new MessageBuilder());
        taskList.addTask("todo", "Task #6");

        try {
            parser.handleInput("mark 1");
            Task markedTask = taskList.getTask(0);
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
        TaskList taskList = new TaskList(tasks);
        Parser parser = new Parser(taskList, new MessageBuilder());
        taskList.addTask("todo", "Task #7");

        try {
            parser.handleInput("mark 1");
            parser.handleInput("unmark 1");
            Task markedTask = taskList.getTask(0);
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
        TaskList taskList = new TaskList(new ArrayList<>());
        Parser parser = new Parser(taskList, new MessageBuilder());
        String result = parser.handleInput("mark 2");
        assertTrue(result.contains("OOPS!!! The task number " + 2 + " is out of bounds."));
    }
}
