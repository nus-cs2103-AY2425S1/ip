package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.data.TaskList;
import bob.tasks.TodoTask;

public class MarkTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;
    private Mark markCommand;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize the task list and the List command
        taskList = new TaskList();
        int index = 0;
        markCommand = new Mark(index);
    }

    @Test
    public void markTask_success() {
        taskList.add(new TodoTask("Task 1"));
        markCommand.execute(taskList, null, null);
        String expectedOutput = "Nice! I've marked this task as done:\n"
                + "[T][X] Task 1";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
