package utilities;

import tasks.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests TaskList class for non-trivial functions.
 * Assumes data files starts with values from input.txt
 * and initial length is of length 4.
 */
public class TaskListTest {
    private static final String FILE_PATH = "./tasks/data.txt";
    private static final String CHECK_STRING = "1.[T][X] run errands";

    private Storage store = new Storage(FILE_PATH);

    @Test
    public void manipulateListTest() {
        TaskList tl = new TaskList(this.store);
        // Check for addition
        tl.addToTaskList(new ToDos("run errands"), "run errands");
        assertEquals(5, tl.getSize());

        // Check for removal
        tl.removeFromTaskList(1);
        assertEquals(4, tl.getSize());

        // Check for updating of status and finding of tasks
        tl.updateTaskListStatus(3, true);
        String result = tl.findTasks("run errands");
        assertEquals(true, result.contains(CHECK_STRING));
        
        // Check for updating of tags
        tl.addTag(0, "fun");
        assertEquals(true, tl.toString().contains("#fun"));
    }

    @Test
    public void findTaskTest() {
        TaskList tl = new TaskList(this.store);

        // Check for search by tags
        tl.addToTaskList(new ToDos("run errands"), "run errands");
        tl.updateTaskListStatus(4, true);
        tl.addTag(4, "funny");

        String result = tl.findTasks("funny");
        assertEquals(true, result.contains(CHECK_STRING));

        // Check for false positives
        String result2 = tl.findTasks("important");
        assertEquals(false, result2.contains(CHECK_STRING));
    }
}
