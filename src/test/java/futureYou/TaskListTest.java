package futureyou;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import futureyou.task.Deadline;
import futureyou.task.Task;

/**
 * Tests for the {@link TaskList} class.
 */
public class TaskListTest {
    /**
     * Date time format for printing LocalDateTime objects
     */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String TEST_TASK_NAME = "Test Task";
    private static final String TEST_DATE = "2024-09-30 22:33";

    private TaskList taskList;

    @BeforeEach
    public void initializeVariables() throws IOException {
        String testFile = "./test.txt";
        File file = new File(testFile);
        file.delete();

        // Clear task list before each test
        TaskList.clearTaskList();

        this.taskList = new TaskList(testFile);
    }

    /**
     * Helper method to create a test Deadline task
     */
    private Deadline createTestDeadline() {
        LocalDateTime testDate = LocalDateTime.parse(TEST_DATE, DATE_FORMAT);
        return new Deadline(TEST_TASK_NAME, testDate);
    }

    /**
     * Helper method to add a test Deadline task to the task list
     */
    private void addTestDeadlineToList() {
        LocalDateTime testDate = LocalDateTime.parse(TEST_DATE, DATE_FORMAT);
        TaskList.addTask(TEST_TASK_NAME, testDate);
    }

    @SuppressWarnings("checkstyle:LineLength")
    @Test
    void addTask_normalInput_writtenCorrectly() {
        // @@author {Nicholas-Cheng-De-Fei}-reused
        // Reused from
        // https://github.com/Nicholas-Cheng-De-Fei/ip/blob/master/src/test/java/quack/util/TaskListTest.java
        Deadline testDeadline = createTestDeadline();
        addTestDeadlineToList();
        ArrayList<Task> list = TaskList.getTaskList();

        // Check if the size of the list is correct
        assertEquals(1, list.size(), "The task list did not add the task into the list");

        // Fetch the task that was just added
        Task actualDeadline = list.get(0);

        // Ensure the task matches what was expected
        assertEquals(testDeadline.getTaskName(), actualDeadline.getTaskName(),
                "The wrong task is added into the list");
        assertEquals(testDeadline.getDeadline(), ((Deadline) actualDeadline).getDeadline(),
                "The deadline date is incorrect");
        // @@author
    }

    @SuppressWarnings("checkstyle:LineLength")
    @Test
    void deleteTask_normalInput_writtenCorrectly() {
        // @@author {Nicholas-Cheng-De-Fei}-reused
        // Reused from
        // https://github.com/Nicholas-Cheng-De-Fei/ip/blob/master/src/test/java/quack/util/TaskListTest.java
        Deadline testDeadline = createTestDeadline();
        addTestDeadlineToList();

        String expectedMessage = "Task Deleted: \n" + testDeadline.print() + System.lineSeparator()
                + "0 tasks left in the list";
        String actualMessage = TaskList.deleteTask(0);

        assertEquals(expectedMessage, actualMessage, "The task list did not delete the task");
        // @@author
    }

    @SuppressWarnings("checkstyle:LineLength")
    @Test
    void markTask_normalInput_writtenCorrectly() {
        // @@author {Nicholas-Cheng-De-Fei}-reused
        // Reused from
        // https://github.com/Nicholas-Cheng-De-Fei/ip/blob/master/src/test/java/quack/util/TaskListTest.java
        Deadline testDeadline = createTestDeadline();
        testDeadline.markTask();
        addTestDeadlineToList();

        String expectedMessage = "Task marked as completed:" + System.lineSeparator() + testDeadline.print();
        String actualMessage = TaskList.markTask(0);

        assertEquals(expectedMessage, actualMessage, "The task list did not mark the task");
        // @@author
    }
}
