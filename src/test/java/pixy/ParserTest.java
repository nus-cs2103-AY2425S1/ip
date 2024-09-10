package pixy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pixy.parser.Parser;
import pixy.tasks.Deadlines;
import pixy.tasks.Event;
import pixy.tasks.Task;
import pixy.tasks.TaskList;
import pixy.tasks.ToDos;
import pixy.ui.Ui;

public class ParserTest {

    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        tasks = new TaskList();
        ui = new Ui();
    }

    @Test
    public void testListCommandWithTasks() {
        tasks.add(new ToDos("Test todo"));
        String response = parser.executeCommand("list", tasks, ui);
        assertTrue(response.contains("Test todo")); // Check if task list includes the added todo
    }

    @Test
    public void testListCommandWithoutTasks() {
        String response = parser.executeCommand("list", tasks, ui);
        assertEquals("Your task list is empty.", response); // Check if the task list is empty message is shown
    }

    @Test
    public void testMarkCommand() {
        Task todo = new ToDos("Test todo");
        tasks.add(todo);
        String response = parser.executeCommand("mark 1", tasks, ui);
        assertTrue(todo.getStatus()); // Check if the task is marked as done
        assertEquals("Task marked as done: Test todo", response); // Check the response message
    }

    @Test
    public void testUnmarkCommand() {
        Task todo = new ToDos("Test todo");
        todo.markAsDone(true);
        tasks.add(todo);
        String response = parser.executeCommand("unmark 1", tasks, ui);
        assertFalse(todo.getStatus()); // Check if the task is unmarked
        assertEquals("Task unmarked: Test todo", response); // Check the response message
    }

    @Test
    public void testDeleteCommand() {
        Task todo = new ToDos("Test todo");
        tasks.add(todo);
        String response = parser.executeCommand("delete 1", tasks, ui);
        assertEquals(0, tasks.size()); // Check if the task list size is 0 after deletion
        assertEquals("Task deleted: Test todo. You now have 0 task(s).", response); // Check the response message
    }

    @Test
    public void testAddTodoCommand() {
        String response = parser.executeCommand("todo Test todo", tasks, ui);
        assertEquals(1, tasks.size()); // Check if task list size is 1
        assertTrue(tasks.get(0) instanceof ToDos); // Check if the added task is a ToDos
        assertEquals("Added new todo: Test todo. You now have 1 task(s).", response); // Check the response message
    }

    @Test
    public void testAddDeadlineCommand() {
        String response = parser.executeCommand("deadline Test deadline /by 31/08/2024 1200", tasks, ui);
        assertEquals(1, tasks.size()); // Check if task list size is 1
        assertTrue(tasks.get(0) instanceof Deadlines); // Check if the added task is a Deadlines
        assertEquals("Added new deadline: Test deadline (by: 31/08/2024 1200). You now have 1 task(s).", response); // Check the response message
    }

    @Test
    public void testAddEventCommand() {
        String response = parser.executeCommand("event Test event /from 31/08/2024 1200 /to 01/09/2024 1200", tasks, ui);
        assertEquals(1, tasks.size()); // Check if task list size is 1
        assertTrue(tasks.get(0) instanceof Event); // Check if the added task is an Event
        assertEquals("Added new event: Test event (from: 31/08/2024 1200 to: 01/09/2024 1200). You now have 1 task(s).", response); // Check the response message
    }
}
