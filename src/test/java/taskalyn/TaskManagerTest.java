package taskalyn;

import java.io.*;
import java.util.List;

import jdk.jfr.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskManagerTest {
    private static TaskManager taskManager;
    private List<Task> tasks;
    private Ui ui;
    private static MockDatabase database;

    @BeforeEach
    public void setUpBeforeTests() throws IOException {
        this.ui = new Ui();
        database = new MockDatabase();
        taskManager = new TaskManager(database, ui);
        for (int i = 0; i < database.getDatabaseSize(); i++) {
            taskManager.deleteTask(i + 1);
        }
    }

    @Test
    public void completeTask_negativeIndex_exceptionThrown() {
        TodoTask task = new TodoTask("do homework", false);
        assertThrows(IndexOutOfBoundsException.class, () -> taskManager.markTaskAsComplete(-4));
    }

    @Test
    public void completeTask_validIndex_successfullyCompleted() {
        TodoTask task = new TodoTask("do homework", false);
        taskManager.addTask(task);
        assertDoesNotThrow(() -> taskManager.markTaskAsComplete(1));
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskManager.markTaskAsComplete(-100));
    }

    @Test
    public void deleteTask_taskDeleted_updatesTaskSize() {
        TodoTask t1 = new TodoTask("eat chicken", false);
        DeadlineTask t2 = new DeadlineTask("finish homework", "2024-08-05 2359", false);
        EventTask t3 = new EventTask("party", "tmr 2pm", "tmr 4pm", false);
        taskManager.addTask(t1);
        taskManager.addTask(t2);
        taskManager.addTask(t3);
        taskManager.deleteTask(2);
        assertEquals(2, taskManager.getTaskSize());
    }

    @Test
    public void listTasks_tasksListed_correctOutput() {
        TodoTask t1 = new TodoTask("eat chicken", false);
        DeadlineTask t2 = new DeadlineTask("finish homework", "2024-08-05 2359", false);
        EventTask t3 = new EventTask("party", "tmr 2pm", "tmr 4pm", false);
        taskManager.addTask(t1);
        taskManager.addTask(t2);
        taskManager.addTask(t3);
        taskManager.markTaskAsComplete(2);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        PrintStream old = System.out;
        System.setOut(ps);
        taskManager.listTasks();
        System.out.flush();
        System.setOut(old);
        String expectedOutput =
                ("    ____________________________________________________________\r\n" +
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

    @AfterAll
    public static void cleanUp() throws IOException {
        for (int i = 0; i < database.getDatabaseSize(); i++) {
            taskManager.deleteTask(i + 1);
        }
    }
}
