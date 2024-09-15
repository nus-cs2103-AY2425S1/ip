package muke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muke.task.Task;
import muke.task.ToDo;
import muke.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private Ui ui;
    private ByteArrayOutputStream outputStream;
    private List<Task> tasks;

    @BeforeEach
    public void setUp() {
        // Initialize the Ui instance and set up the output stream to capture prints
        ui = new Ui();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Reset the "database" (tasks list) before each test
        tasks = new ArrayList<>();
    }

    @Test
    public void testShowWelcome() {
        ui.showWelcome();
        String expectedOutput = "____________________________________________________________\n" +
                " Hello! I'm Muke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowGoodbye() {
        ui.showGoodbye();
        String expectedOutput = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowTaskAdded() {
        Task task = new ToDo("Test task");
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        String expectedOutput = "____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                "   " + task + "\n" +
                " Now you have 1 tasks in the list.\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowTaskRemoved() {
        Task task = new ToDo("Test task");
        tasks.add(task);
        tasks.remove(task);
        ui.showTaskRemoved(task, tasks.size());
        String expectedOutput = "____________________________________________________________\n" +
                " Noted. I've removed this task:\n" +
                "   " + task + "\n" +
                " Now you have 0 tasks in the list.\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}