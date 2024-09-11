package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.data.TaskList;
import bob.tasks.TodoTask;

public class ListTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;
    private List listCommand;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize the task list and the List command
        taskList = new TaskList();
        listCommand = new List();
    }

    @Test
    public void listTasks_emptyList_success() {
        listCommand.execute(taskList, null, null);
        assertEquals("There are no tasks in your list.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void listTasks_withTasks_success() {
        taskList.add(new TodoTask("Task 1"));
        taskList.add(new TodoTask("Task 2"));

        listCommand.execute(taskList, null, null);

        String expectedOutput = "Here are the tasks in your list:\n"
                + "1. [T][ ] Task 1\n"
                + "2. [T][ ] Task 2";

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}

