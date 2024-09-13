package rizzler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TaskListTest {
    private static final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    @Test
    public void addTaskToList_success() throws Exception {
        new TaskList(new ArrayList<>()).add(new Todo("test 1"));
        assertEquals(testOut.toString(),
                "_______________________________________________________\n"
                        + "Gotcha! I've added the new task for you:\n"
                        + "[T][ ] test 1\n"
                        + "Now you have 1 tasks in the list.\n"
                        + "_______________________________________________________\n"
                        + "\r\n");
        testOut.reset();
    }

    @Test
    public void deleteTaskFromList_success() throws Exception {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("test 2"));
        new TaskList(testList).delete(0);
        assertEquals(testOut.toString(),
                "_______________________________________________________\n"
                        + "I have removed this task for you:\n"
                        + "[T][ ] test 2\n"
                        + "Now you have 0 tasks in the list.\n"
                        + "_______________________________________________________\n"
                        + "\r\n");
        testOut.reset();
    }

    @Test
    public void deleteTaskFromList_noItemsInList() throws Exception {
        try {
            new TaskList(new ArrayList<>()).delete(0);
            fail(); // this should never go through
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(), "No tasks to delete");
        }
    }

    @Test
    public void deleteTaskFromList_indexOutOfBounds() throws Exception {
        try {
            ArrayList<Task> testList = new ArrayList<>();
            testList.add(new Todo("test 3"));
            new TaskList(testList).delete(1);
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Please put task number that is actually in the list");
        }
    }
}
