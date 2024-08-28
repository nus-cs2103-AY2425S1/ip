package command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDo;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testGreetUser() {
        ui.greetUser();
        String expectedOutput = "\t\t" + "_".repeat(50) + "\n" +
                "\t\tHey there! I'm ChatterBox\n" +
                "\t\tWhat's on your plate today?\n" +
                "\t\t" + "_".repeat(50) + "\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowTaskList() {
        Task task = new ToDo("Sample Task");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);

        ui.showTaskList(taskList);
        String expectedOutput = "\t\t" + "_".repeat(50) + "\n" +
                "\t\tHere's what you've got on your to-do list so far:\n" +
                "\t\t\t1. " + task + "\n" +
                "\t\t" + "_".repeat(50) + "\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowTaskAdded() {
        Task task = new ToDo("Sample Task");
        int taskCount = 1;

        ui.showTaskAdded(task, taskCount);
        String expectedOutput = "\t\t" + "_".repeat(50) + "\n" +
                "\t\tSample Task is added to your list\n" +
                "\t\t" + task + "\n" +
                "\t\tNow you have 1 tasks in your list.\n" +
                "\t\t" + "_".repeat(50) + "\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
