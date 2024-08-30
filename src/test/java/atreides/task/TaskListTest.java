package atreides.task;

import atreides.ui.AtreidesException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {

    @Test
    public void mark_success() throws AtreidesException {
            String[] task = {"T", "1", "read book"};
            ArrayList<String[]> tasks = new ArrayList<>();
            tasks.add(task);
            TaskList taskList = new TaskList(tasks);
            String printList = "T | 1 | read book";
            assertEquals(printList, taskList.writeList());
    }

    @Test
    public void mark_fail() throws AtreidesException {
        try {
            TaskList taskList = new TaskList();
            taskList.mark(4);
            fail();
        } catch (AtreidesException e) {
            assertEquals(e.getMessage(), "list does not have the index present");
        }
    }
}
