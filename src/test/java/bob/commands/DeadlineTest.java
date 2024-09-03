package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.data.TaskList;
public class DeadlineTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;
    private Deadline deadlineCommand;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize the task list and the deadline command
        taskList = new TaskList();
        LocalDateTime by = LocalDateTime.of(2024, 9, 15, 16, 30);
        deadlineCommand = new Deadline("Task 1", by);
    }

    @Test
    public void execute_success() {
        deadlineCommand.execute(taskList, null, null);
        String expectedOutput = "Got it. I've added this task:\n"
                + "[D][ ] Task 1 (by: 15 September 2024 4:30pm)\n"
                + "Now you have 1 task in the list.";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
