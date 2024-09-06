package atreides.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import atreides.ui.AtreidesException;


public class TaskListTest {

    @Test
    public void markSuccess() throws AtreidesException {
        String[] task = {"T", "1", "read book"};
        ArrayList<String[]> tasks = new ArrayList<>();
        tasks.add(task);
        TaskList taskList = new TaskList(tasks);
        String printList = "T | 1 | read book";
        assertEquals(printList, taskList.writeList());
    }

    @Test
    public void markFail() throws AtreidesException {
        try {
            TaskList taskList = new TaskList();
            taskList.mark(4);
            fail();
        } catch (AtreidesException e) {
            assertEquals(e.getMessage(), "list does not have the index present");
        }
    }
}
