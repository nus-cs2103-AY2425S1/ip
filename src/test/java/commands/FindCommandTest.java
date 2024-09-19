package commands;
import Commands.FindCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;
import task.ToDoTask;
import ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the FindCommand class to ensure that it correctly finds tasks based on a search keyword.
 */

public class FindCommandTest {
    TaskList tasklist;
    @BeforeEach
    public void setUp() {
        // Initialize the TaskList before each test
        tasklist = new TaskList(new ArrayList<>()); // Initialize with an empty list
        Ui ui = new Ui(); // Need for method

        // Add sample tasks to TaskList
        tasklist.addTask(new ToDoTask("Buy milk"));
        tasklist.addTask(new ToDoTask("Read book"));
        tasklist.addTask(new ToDoTask("Buy groceries"));
    }

    /**
     * Tests the FindCommand for a positive case where the search keyword matches a task description.
     * It verifies that the command correctly finds and returns the tasks containing the keyword.
     */
    @Test
    public void testFindCommandPositive() {
        String cmd = "find milk";
        FindCommand fc = new FindCommand(cmd);

        String actualResult = fc.commandAction();

        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(new ToDoTask("Buy milk"));

        String expectedResult = Ui.diffListDisplay("Here are the matching tasks in your list:", expectedList);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests the FindCommand for a negative case where the search keyword is not found anywhere in the task description.
     * It returns an error message that there are no tasks available containing the keyword.
     */
    @Test
    public void testFindCommandNegative() {
        String cmd = "find cat";
        FindCommand fc = new FindCommand(cmd);

        String actualResult = fc.commandAction();

        String expectedResult = "You have no current tasks containing 'cat'.\n" +
                "Please try again with another word.";
        assertEquals(expectedResult, actualResult);
    }
}
