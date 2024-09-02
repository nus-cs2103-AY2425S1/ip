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
        ui.welcomeMessage();
        String expectedOutput = "____________________________________________________________\n"
                + " Hello! I'm Samson.Samson\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGoodbyeMessage() {
        ui.goodbyeMessage();
        String expectedOutput = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testShowTaskAdded() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Write unit tests");
        taskList.addTask(task);

        ui.showTaskAdded(task, taskList);

        String expectedOutput = "____________________________________________________________\n"
                + " Got it. I've added this task:\n"
                + "   [T][ ] Write unit tests\n"
                + " Now you have 1 tasks in the list.\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testShowTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new ToDo("Task 2"));

        ui.showTaskList(taskList);

        String expectedOutput = "____________________________________________________________\n"
                + "Here are the tasks in your list: \n"
                + " 1. [T][ ] Task 1\n"
                + " 2. [T][ ] Task 2\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testShowTaskMarked() {
        Task task = new ToDo("Task to mark");
        task.complete();
        ui.showTaskMarked(task);

        String expectedOutput = "____________________________________________________________\n"
                + " Nice! I've marked this task as done:\n"
                + "   [T][X] Task to mark\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testShowTaskUnmarked() {
        Task task = new ToDo("Task to unmark");
        task.complete();  // Initially marked as done
        task.notComplete();  // Now unmarked
        ui.showTaskUnmarked(task);

        String expectedOutput = "____________________________________________________________\n"
                + " OK, I've marked this task as not done yet:\n"
                + "   [T][ ] Task to unmark\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testShowTaskNumInvalid() {
        ui.showTaskNumInvalid();

        String expectedOutput = "____________________________________________________________\n"
                + " Task number out of range.\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testShowError() {
        String errorMessage = "An error occurred!";
        ui.showError(errorMessage);

        String expectedOutput = "____________________________________________________________\n"
                + " OOPS!!! An error occurred!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReadCommand() {
        String input = "This is a command";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        String command = ui.readCommand();
        assertEquals(input, command);
    }
}

