package xizi.chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xizi.chatbot.command.DeleteCommand;
import xizi.chatbot.task.Deadline;
import xizi.chatbot.task.Event;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.task.Todo;


/**
 * Unit tests for the {@link DeleteCommand} class with a mixture of task types.
 * This class tests the functionality related to deleting tasks from the {@link TaskList}.
 */
public class DeleteCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the necessary objects before each test.
     * Initializes an empty {@link TaskList}, a {@link Ui}, and a {@link Storage} object.
     * Adds different types of tasks (Todo, Deadline, Event) to the task list for testing purposes.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/deleteCommandTest.txt");

        // Adding a Todo task
        tasks.addTask(new Todo("read book"));

        // Adding a Deadline task
        tasks.addTask(new Deadline("submit report", LocalDateTime.of(2024, 12, 1, 23, 59)));

        // Adding an Event task
        tasks.addTask(new Event("team meeting",
                LocalDateTime.of(2024, 12, 5, 14, 0), LocalDateTime.of(2024, 12, 5, 16, 0)));
    }

    /**
     * Tests the functionality of the {@link DeleteCommand} to delete a task.
     * Verifies that the correct task is removed and that the remaining tasks are as expected.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void testDeleteCommand() throws Exception {
        // Deleting the first task (Todo)
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        deleteCommand.execute(tasks, storage, ui);

        // Verifying the Todo task is deleted and the remaining tasks are correct
        assertEquals(2, tasks.getSize());
        assertEquals("submit report", tasks.getItems().get(0).getName()); // Deadline should be first now
        assertEquals("team meeting", tasks.getItems().get(1).getName()); // Event should be second
    }

    /**
     * Tests deleting an Event task.
     * Verifies that the Event is correctly removed.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void testDeleteEvent() throws Exception {
        // Deleting the Event task (third task in the list)
        DeleteCommand deleteCommand = new DeleteCommand("delete 3");
        deleteCommand.execute(tasks, storage, ui);

        // Verifying that the Event is deleted and the remaining tasks are correct
        assertEquals(2, tasks.getSize());
        assertEquals("read book", tasks.getItems().get(0).getName());
        assertEquals("submit report", tasks.getItems().get(1).getName());
    }

    /**
     * Tests the behavior when attempting to delete a task with an invalid index.
     * Verifies that an exception is thrown when the task index does not exist.
     */
    @Test
    public void testDeleteInvalidTask() {
        Exception exception = assertThrows(Exception.class, () -> {
            DeleteCommand deleteCommand = new DeleteCommand("delete 4"); // Out of range
            deleteCommand.execute(tasks, storage, ui);
        });

        assertTrue(exception.getMessage().contains("The task number does not exist."));
    }

    /**
     * Tests deleting the last task (Event) from the task list.
     * Verifies the list is empty after deleting all tasks.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void testDeleteLastTask() throws Exception {
        // Deleting all tasks one by one
        DeleteCommand deleteCommand1 = new DeleteCommand("delete 1"); // Delete Todo
        deleteCommand1.execute(tasks, storage, ui);

        DeleteCommand deleteCommand2 = new DeleteCommand("delete 1"); // Delete Deadline
        deleteCommand2.execute(tasks, storage, ui);

        DeleteCommand deleteCommand3 = new DeleteCommand("delete 1"); // Delete Event
        deleteCommand3.execute(tasks, storage, ui);

        // Verifying the task list is now empty
        assertEquals(0, tasks.getSize());
    }

    /**
     * Tests the behavior when deleting from an empty task list.
     * Verifies that an exception is thrown when trying to delete from an empty list.
     */
    @Test
    public void testDeleteFromEmptyList() {
        tasks = new TaskList(); // Reinitialize as empty
        Exception exception = assertThrows(Exception.class, () -> {
            DeleteCommand deleteCommand = new DeleteCommand("delete 1");
            deleteCommand.execute(tasks, storage, ui);
        });

        assertTrue(exception.getMessage().contains("The task number does not exist."));
    }
}



