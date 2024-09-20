package samson;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import samson.task.Task;
import samson.task.TaskList;
import samson.task.ToDo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private Ui ui;
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testWelcomeMessage() {
        String expectedOutput = " Hello! I'm Samson\n" + " What can I do for you?\n";
        assertEquals(expectedOutput, Ui.welcomeMessage());
    }

    @Test
    public void testGoodbyeMessage() {
        String expectedOutput = " Bye. Hope to see you again soon!\n";
        assertEquals(expectedOutput, ui.goodbyeMessage());
    }

    @Test
    public void testShowTaskAdded() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Write unit tests");
        taskList.addTask(task);

        String expectedOutput = " Got it. I've added this task:\n"
                + "   [T][ ] Write unit tests\n"
                + " Now you have 1 tasks in the list.\n";
        assertEquals(expectedOutput, ui.showTaskAdded(task, taskList));
    }

    @Test
    public void testShowTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new ToDo("Task 2"));

        String expectedOutput = "____________________________________________________________\n"
                + "Here are the tasks in your list:\n"
                + " 1. [T][ ] Task 1\n"
                + " 2. [T][ ] Task 2\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, ui.showTaskList(taskList));
    }

    @Test
    public void testShowTaskMarked() {
        Task task = new ToDo("Task to mark");
        task.complete();

        String expectedOutput = " Nice! I've marked this task as done:\n"
                + "   [T][X] Task to mark\n";
        assertEquals(expectedOutput, ui.showTaskMarked(task));
    }

    @Test
    public void testShowTaskUnmarked() {
        Task task = new ToDo("Task to unmark");
        task.complete();  // Initially marked as done
        task.notComplete();  // Now unmarked

        String expectedOutput = " OK, I've marked this task as not done yet:\n"
                + "   [T][ ] Task to unmark\n";
        assertEquals(expectedOutput, ui.showTaskUnmarked(task));
    }

    @Test
    public void testShowTaskNumInvalid() {
        String expectedOutput = " Task number out of range.\n";
        assertEquals(expectedOutput, ui.showTaskNumInvalid());
    }

    @Test
    public void testShowError() {
        String errorMessage = "An error occurred!";
        String expectedOutput = " OOPS!!! An error occurred!\n";
        assertEquals(expectedOutput, ui.showError(errorMessage));
    }

}
