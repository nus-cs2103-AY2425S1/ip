package optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
//Used GPT to help with refining parts of the unit tests helping with more coverage
public class TaskListTest {

    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setUp() throws OptimusException {
        // Set up a TaskList with some tasks for testing
        taskList = new TaskList(Arrays.asList(
                new ToDo("Buy groceries"),
                new Deadline("Submit assignment", "2024-10-10 16:00"),
                new Event("Project meeting", "2024-09-20 16:00", "2024-09-21 17:00")
        ));
        ui = new Ui();
    }

    // Basic tests
    @Test
    public void testFindTasks_noMatch() {
        String result = taskList.findTasks("nonexistent", ui);
        assertEquals("No matching tasks found. Try again", result);
    }

    @Test
    public void testFindTasks_singleMatch() {
        String result = taskList.findTasks("Buy", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] Buy groceries\n";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_multipleMatches() {
        String result = taskList.findTasks("Submit", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [D][ ] Submit assignment (by: Oct 10 2024, 4:00pm)\n";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_caseInsensitive() {
        String result = taskList.findTasks("BUY", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] Buy groceries\n";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_multipleKeywords() {
        String result = taskList.findTasks("project meeting", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [E][ ] Project meeting (on: Sep 20 2024, 4:00pm - Sep 21 2024, 5:00pm)\n";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_emptyKeyword() {
        String result = taskList.findTasks("", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] Buy groceries\n"
                + "2. [D][ ] Submit assignment (by: Oct 10 2024, 4:00pm)\n"
                + "3. [E][ ] Project meeting (on: Sep 20 2024, 4:00pm - Sep 21 2024, 5:00pm)\n";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_noTasksInList() throws OptimusException {
        // Set up an empty TaskList
        taskList = new TaskList(Collections.emptyList());
        String result = taskList.findTasks("Buy", ui);
        assertEquals("No matching tasks found. Try again", result);
    }

    @Test
    public void testFindTasks_partialMatch() {
        String result = taskList.findTasks("gro", ui); // Partial match with "groceries"
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] Buy groceries\n";
        assertEquals(expected, result);
    }


    @Test
    public void testFindTasks_exactMatch() {
        String result = taskList.findTasks("Buy groceries", ui);
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] Buy groceries\n";
        assertEquals(expected, result);
    }

}
