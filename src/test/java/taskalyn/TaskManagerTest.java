package taskalyn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Verifies that the TaskManager properly handles task operations.
 */
public class TaskManagerTest {
    private static TaskManager taskManager;

    /**
     * Sets up ui, database, and taskmanager before each test.
     */
    @BeforeEach
    public void setUpBeforeTests() throws IOException {
        Ui ui = new Ui();
        MockDatabase database = new MockDatabase();
        database.clearDatabase();
        taskManager = new TaskManager(database, ui);
    }

    @Test
    public void addTask_newTask_taskAddedSuccessfully() {
        String actualOutput = taskManager.addTask(new TodoTask("go to gym", false));
        assertEquals(1, taskManager.getTaskSize(), "There should be just 1 task in the database.");
        String expectedOutput = """
                Got it, I've added this task to your list!
                      [T][ ] go to gym
                    Wah bro... 1 task already!""";
        assertEquals(expectedOutput, actualOutput, "This should be the UI message when a task is added.");
    }

    @Test
    public void deleteTask_existingTask_taskDeletedSuccessfully() {
        taskManager.addTask(new TodoTask("go to gym", false));
        String actualOutput = taskManager.deleteTask(1);
        assertEquals(0, taskManager.getTaskSize(), "There should be no tasks in the database.");
        String expectedOutput = """
                Awesome bro! One task gone :D
                      [T][ ] go to gym
                    Wah bro... 0 task already!""";
        assertEquals(expectedOutput, actualOutput, "This should be the UI message when a task is deleted.");
    }

    @Test
    public void listTasks_multipleTasks_listedCorrectly() {
        taskManager.addTask(new TodoTask("go to school", false));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));

        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualOutput = taskManager.listTasks();
        String expectedOutput = """
                Here are the tasks in your list:
                    1.[T][ ] go to school
                    2.[D][ ] hw (by: 11 09 2024, 1:00 PM)
                    3.[E][ ] party (from: 11 09 2024, 6:00 PM to: 11 09 2024, 8:00 PM)""";
        assertEquals(expectedOutput, actualOutput, "This should be the UI message when a task is listed.");
    }

    @Test
    public void markTaskAsComplete_markedCorrectly() {
        taskManager.addTask(new TodoTask("go to school", false));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));

        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualOutput = taskManager.markTaskAsComplete(1);
        String expectedOutput = """
                Nice, I've marked this task as complete:
                      [T][X] go to school""";
        assertEquals(expectedOutput, actualOutput, "This should be the UI message when a task is marked.");
    }

    @Test
    public void markTaskAsIncomplete_unmarkedCorrectly() {
        taskManager.addTask(new TodoTask("go to school", false));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));

        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        taskManager.markTaskAsComplete(1);
        String actualOutput = taskManager.markTaskAsIncomplete(1);
        String expectedOutput = """
                Ok, I've marked this task as incomplete:
                      [T][ ] go to school""";
        assertEquals(expectedOutput, actualOutput, "This should be the UI message when a task is unmarked.");
    }

    @Test
    public void findKeyword_searchTasksCorrectly() {
        taskManager.addTask(new TodoTask("go to school", false));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));

        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualOutput = taskManager.searchTasksByKeyword("school");
        String expectedOutput = """
                Here are the matching tasks in your list:
                    1.[T][ ] go to school""";
        assertEquals(expectedOutput, actualOutput,
                "This should be the UI message when a task is found by find command.");
    }
}
