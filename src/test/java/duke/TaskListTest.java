package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;


public class TaskListTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> toDoList = new ArrayList<>();
        toDoList.add(new Todo("Test Task"));
        TaskList.setToDoList(toDoList);
        Mockito.mockStatic(Storage.class);
        doNothing().when(Storage.class);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
        Mockito.clearAllCaches();
    }

    @Test
    public void testMark_success() {
        int taskIndex = 1;
        TaskList.mark(taskIndex);
        assertTrue(TaskList.getTask(taskIndex - 1).isDone());
        String expectedOutput = "Nice! I've marked this task as done:" + System.lineSeparator() +
                TaskList.getTask(taskIndex - 1).toString() + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testUnmark_success() {
        int taskIndex = 1;
        TaskList.unmark(taskIndex);
        assertFalse(TaskList.getTask(taskIndex - 1).isDone());
        String expectedOutput = "Ok! I've marked this task as not done yet:" + System.lineSeparator() +
                TaskList.getTask(taskIndex - 1).toString() + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

}

