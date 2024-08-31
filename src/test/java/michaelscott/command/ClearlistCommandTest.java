package michaelscott.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import michaelscott.task.TaskList;
import michaelscott.task.Todo;

class ClearlistCommandTest {
    private TaskList tasks;
    private ClearlistCommand clearlistCommand;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        clearlistCommand = new ClearlistCommand();

        // Adding some tasks to the list for testing
        tasks.addTask(new Todo("Task 1"));
        tasks.addTask(new Todo("Task 2"));
        tasks.addTask(new Todo("Task 3"));
    }

    @Test
    void testExecute_clearList() {
        String result = clearlistCommand.execute(tasks);
        assertEquals(0, tasks.size(), "Task list should be empty after clearing.");
        assertEquals("Okay, the list has been cleared", result, "Message after clearing list is incorrect.");
    }

    @Test
    void testExecute_emptyList() {
        tasks.clearList();
        String result = clearlistCommand.execute(tasks);
        assertEquals(0, tasks.size(), "Task list should be empty.");
        assertEquals("Okay, the list has been cleared", result, "Message after clearing an empty list is incorrect.");
    }
}
