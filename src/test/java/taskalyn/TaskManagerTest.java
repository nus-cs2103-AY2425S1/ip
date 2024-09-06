package taskalyn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.List;

import jdk.jfr.Event;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies that the TaskManager properly handles task operations.
 */
public class TaskManagerTest {
    private static TaskManager taskManager;
    private List<Task> tasks;
    private Ui ui;
    private static MockDatabase database;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    /**
     * Sets up ui, database, and taskmanager before each test.
     *
     * @throws IOException If an I/O error occurs during reading.
     */
    @BeforeEach
    public void setUpBeforeTests() throws IOException {
        System.setIn(new ByteArrayInputStream("".getBytes()));
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        this.ui = new Ui();
        database = new MockDatabase();
        taskManager = new TaskManager(database, ui);
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
    }

    @AfterEach
    public void tearDownAfterTests() throws IOException {
        System.setOut(originalOut);
    }

    /**
     * Verifies that a negative task number returns an exception.
     */
    @Test
    public void completeTask_negativeIndex_exceptionThrown() {
        TodoTask task = new TodoTask("do homework", false);
        assertThrows(IndexOutOfBoundsException.class, () -> taskManager.markTaskAsComplete(-4));
    }

    /**
     * Verifies that an existent task can be completed.
     */
    @Test
    public void completeTask_validIndex_successfullyCompleted() {
        TodoTask task = new TodoTask("do homework", false);
        taskManager.addTask(task);
        assertDoesNotThrow(() -> taskManager.markTaskAsComplete(1));
    }

    /**
     * Verifies that an invalid task number returns an exception.
     */
    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskManager.markTaskAsComplete(-100));
    }

    /**
     * Verifies that the task size is correctly shown.
     */
    @Test
    public void deleteTask_taskDeleted_updatesTaskSize() {
        TodoTask t1 = new TodoTask("eat chicken", false);
        DeadlineTask t2 = new DeadlineTask("finish homework", "2024-08-05 2359", false);
        EventTask t3 = new EventTask("party", "tmr 2pm", "tmr 4pm", false);
        taskManager.addTask(t1);
        taskManager.addTask(t2);
        taskManager.addTask(t3);
        assertEquals(3, taskManager.getTaskSize());
        taskManager.deleteTask(2);
        assertEquals(2, taskManager.getTaskSize());
    }

    /**
     * Verifies that various adding and listing task operations are followed as expected.
     */
    @Test
    public void addAndListTasks_producesExpectedOutput() {
        TodoTask t1 = new TodoTask("eat chicken", false);
        DeadlineTask t2 = new DeadlineTask("finish homework", "2024-08-05 2359", false);
        EventTask t3 = new EventTask("party", "tmr 2pm", "tmr 4pm", false);
        taskManager.addTask(t1);
        taskManager.addTask(t2);
        taskManager.addTask(t3);
        taskManager.markTaskAsComplete(2);
        taskManager.listTasks();
        String expectedOutput =
                ("    ____________________________________________________________\r\n" +
                "    Got it, I've added this task to your list!\r\n" +
                "      [T][ ] eat chicken\r\n" +
                "    Wah bro... 1 task already!\r\n" +
                "    ____________________________________________________________\r\n\r\n" +
                "    ____________________________________________________________\r\n" +
                "    Got it, I've added this task to your list!\r\n" +
                "      [D][ ] finish homework (by: 05 08 2024, 11:59 PM)\r\n" +
                "    Wah bro... 2 tasks already!\r\n" +
                "    ____________________________________________________________\r\n\r\n" +
                "    ____________________________________________________________\r\n" +
                "    Got it, I've added this task to your list!\r\n" +
                "      [E][ ] party (from: tmr 2pm to: tmr 4pm)\r\n" +
                "    Wah bro... 3 tasks already!\r\n" +
                "    ____________________________________________________________\r\n\r\n" +
                "    ____________________________________________________________\r\n" +
                "    Nice, I've marked this task as complete:\r\n" +
                "       [D][X] finish homework (by: 05 08 2024, 11:59 PM)\r\n" +
                "    ____________________________________________________________\r\n\r\n" +
                "    ____________________________________________________________\r\n" +
                "    Here are the tasks in your list:\r\n" +
                "    1.[T][ ] eat chicken\r\n" +
                "    2.[D][X] finish homework (by: 05 08 2024, 11:59 PM)\r\n" +
                "    3.[E][ ] party (from: tmr 2pm to: tmr 4pm)\r\n" +
                "    ____________________________________________________________\r\n\r\n"
                ).replaceAll("\\n|\\r\\n", System.lineSeparator());

        String actualOutput = outputStream.toString().trim().replaceAll("\\s+$", "")
                .replaceAll("\\r\\n", "\n");
        String normalizedExpectedOutput = expectedOutput.trim().replaceAll("\\s+$", "")
                .replaceAll("\\r\\n", "\n");
        assertEquals(normalizedExpectedOutput, actualOutput);
    }
}
