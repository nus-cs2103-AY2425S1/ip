package UI;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Task.TaskList;
import Task.ToDo;

public class ParserTest {

    @BeforeEach
    public void setUp() {
        //Reset the TaskList
        while (TaskList.mainTaskList.getNumTasks() > 0) {
            TaskList.mainTaskList.deleteTask(0);
        }
        // Set up the TaskList with some tasks
        new ToDo("Task 1");
        new ToDo("Task 2");
        new ToDo("Task 3");
    }

    @Test
    public void testHandleDeleteValidTask() {
        try {
            TaskList.mainTaskList.printList();
            Parser.handleDelete("2"); // Should delete the second task
            TaskList.mainTaskList.printList();
            assertEquals(2, TaskList.mainTaskList.getNumTasks());
        } catch (BotException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testHandleDeleteEmptyArgument() {
        Exception exception = assertThrows(BotException.class, () -> {
            Parser.handleDelete("");
        });
        assertEquals("Please provide a task number.", exception.getMessage());
    }

    @Test
    public void testHandleDeleteInvalidTaskNumber() {
        Exception exception = assertThrows(BotException.class, () -> {
            Parser.handleDelete("10"); // Out of bounds
        });
        assertEquals("That task does not exist!", exception.getMessage());
    }
}

