package optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

public class TaskListTest {

    private TaskList taskList;
    private Ui ui;
    //ChatGPT helped with coming up with some of the tests. But it could not give correct inputs for some so
    //had to tweak the code a bit
    @BeforeEach
    public void setUp() throws OptimusException {
        // Set up a TaskList with some tasks for testing
        taskList = new TaskList(Arrays.asList(
                new ToDo("Buy groceries"),
                new Deadline("Submit assignment", "2024-10-10 16:00"),
                new Event("Project meeting", "2024-09-20 16:00", "2024-09-21 17:00")
        ));
        ui = new Ui(); // Assuming Ui class exists
    }

    @Test
    public void testFindTasks_NoMatch() {
        String result = taskList.findTasks("nonexistent", ui);
        assertEquals("No matching tasks found. Try again", result);
    }

    @Test
    public void testFindTasks_SingleMatch() {
        String result = taskList.findTasks("Buy", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] Buy groceries\n";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_MultipleMatches() {
        String result = taskList.findTasks("Submit", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [D][ ] Submit assignment (by: Oct 10 2024, 4:00pm)\n";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_CaseInsensitive() {
        String result = taskList.findTasks("BUY", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] Buy groceries\n";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_MultipleKeywords() {
        String result = taskList.findTasks("project meeting", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [E][ ] Project meeting (on: Sep 20 2024, 4:00pm - Sep 21 2024, 5:00pm)\n";
        assertEquals(expected, result);
    }
}


