package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.data.TaskList;

public class TodoTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;
    private Todo todoCommand;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize the task list and the todo command
        taskList = new TaskList();
        todoCommand = new Todo("Task 1");
    }

    @Test
    public void execute_success() {
        todoCommand.execute(taskList, null, null);
        String expectedOutput = "Got it. I've added this task:\n"
                + "[T][ ] Task 1\n"
                + "Now you have 1 task in the list.";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
