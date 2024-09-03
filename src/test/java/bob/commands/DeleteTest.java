package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.data.TaskList;
import bob.tasks.TodoTask;
public class DeleteTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;
    private Delete deleteCommand;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize the task list and the delete command
        taskList = new TaskList();
        int index = 0;
        deleteCommand = new Delete(index);
    }

    @Test
    public void deleteTask_success() {
        taskList.add(new TodoTask("Task 1"));
        deleteCommand.execute(taskList, null, null);
        String expectedOutput = "Noted. I've removed this task:\n"
                + "[T][ ] Task 1"
                + "\nNow you have " + taskList.size() + (taskList.isEmpty() ? " task in the list."
                : " tasks in the list.");
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
