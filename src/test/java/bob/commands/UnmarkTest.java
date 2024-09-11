package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.data.TaskList;
import bob.tasks.TodoTask;

public class UnmarkTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;
    private Unmark unmarkCommand;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize the task list and the List command
        taskList = new TaskList();
        int index = 0;
        unmarkCommand = new Unmark(index);
    }

    @Test
    public void unmarkTask_success() {
        taskList.add(new TodoTask("Task 1"));
        taskList.get(0).markAsDone();
        unmarkCommand.execute(taskList, null, null);
        String expectedOutput = "OK, I've marked this task as not done yet:\n"
                + "[T][ ] Task 1";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
