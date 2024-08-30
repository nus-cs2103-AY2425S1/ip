package pixy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pixy.parser.Parser;
import pixy.tasks.Deadlines;
import pixy.tasks.Event;
import pixy.tasks.Task;
import pixy.tasks.TaskList;
import pixy.tasks.ToDos;
import pixy.ui.Ui;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Parser parser;
    private TaskList tasks;
    private Ui ui; // Note: You need to adapt these tests if you are not using mocks

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        tasks = new TaskList();
        ui = new Ui(); // Change to your actual Ui implementation if necessary
    }

    @Test
    public void testListCommandWithTasks() {
        tasks.add(new ToDos("Test todo"));
        // You will need to manually verify the results since Ui is not mocked
        parser.parseCommand("list", tasks, ui);
        assertFalse(tasks.getList().isEmpty()); // Check if task list is not empty
    }

    @Test
    public void testListCommandWithoutTasks() {
        parser.parseCommand("list", tasks, ui);
        assertTrue(tasks.getList().isEmpty()); // Check if task list is empty
    }

    @Test
    public void testMarkCommand() {
        Task todo = new ToDos("Test todo");
        tasks.add(todo);
        parser.parseCommand("mark 1", tasks, ui);
        assertTrue(todo.getStatus()); // Check if the task is marked as done
    }

    @Test
    public void testUnmarkCommand() {
        Task todo = new ToDos("Test todo");
        todo.markAsDone(true);
        tasks.add(todo);
        parser.parseCommand("unmark 1", tasks, ui);
        assertFalse(todo.getStatus()); // Check if the task is unmarked
    }

    @Test
    public void testDeleteCommand() {
        Task todo = new ToDos("Test todo");
        tasks.add(todo);
        parser.parseCommand("delete 1", tasks, ui);
        assertEquals(0, tasks.size()); // Check if the task list size is 0 after deletion
    }

    @Test
    public void testAddTodoCommand() {
        parser.parseCommand("todo Test todo", tasks, ui);
        assertEquals(1, tasks.size()); // Check if task list size is 1
        assertTrue(tasks.get(0) instanceof ToDos); // Check if the added task is a ToDos
    }

    @Test
    public void testAddDeadlineCommand() {
        parser.parseCommand("deadline Test deadline /by 31/08/2024 1200", tasks, ui);
        assertEquals(1, tasks.size()); // Check if task list size is 1
        assertTrue(tasks.get(0) instanceof Deadlines); // Check if the added task is a Deadlines
    }

    @Test
    public void testAddEventCommand() {
        parser.parseCommand("event Test event /from 31/08/2024 1200 /to 01/09/2024 1200", tasks, ui);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Event);
    }
}
