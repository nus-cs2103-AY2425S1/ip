package barney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import barney.action.commands.TodoCommand;
import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.TodoTask;
import barney.ui.Ui;

/**
 * This class contains unit tests for the TodoCommand class.
 */
public class TodoCommandTest {

    @Test
    public void execute_validArguments_addsTodoTask() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        HashMap<String, String> argumentMap = new HashMap<>();
        argumentMap.put("description", "borrow book");

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            TodoCommand command = new TodoCommand(argumentMap);
            command.execute(tasks, ui);

            // Verify the task was added
            assertEquals(1, tasks.size());
            assertTrue(tasks.get(0) instanceof TodoTask);
            // Verify the output
<<<<<<< Updated upstream
            String expectedOutput = "Got it. I've added this task:\r\n" + //
                    "[T][ ] borrow book\r\n" + //
                    "Now you have 1 tasks in the list.";
=======
            String expectedOutput = """
                    Got it. I've added this task:\r
                    [T][ ] borrow book\r
                    Now you have 1 tasks in the list.""";
>>>>>>> Stashed changes

            assertTrue(outContent.toString().contains(expectedOutput));
        } catch (InvalidArgumentException e) {
            fail("InvalidArgumentException should not be thrown" + e.getMessage());
        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }
<<<<<<< Updated upstream
}
=======

    /**
     * Tests that an InvalidArgumentException is thrown when the description is
     * missing.
     */
    @Test
    public void execute_missingDescription_throwsInvalidArgumentException() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        HashMap<String, String> argumentMap = new HashMap<>();

        try {
            TodoCommand command = new TodoCommand(argumentMap);
            command.execute(tasks, ui);
            fail("InvalidArgumentException should be thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Missing description for todo!", e.getMessage());
        }
    }

    /**
     * Tests that an InvalidArgumentException is thrown when there are extra
     */
    @Test
    public void execute_emptyDescription_throwsInvalidArgumentException() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        HashMap<String, String> argumentMap = new HashMap<>();
        argumentMap.put("description", "");

        try {
            TodoCommand command = new TodoCommand(argumentMap);
            command.execute(tasks, ui);
            fail("InvalidArgumentException should be thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("The description of a todo cannot be empty!", e.getMessage());
        }
    }

    /**
     * Tests that an InvalidArgumentException is thrown when there are extra
     * arguments.
     */
    @Test
    public void execute_extraArguments_throwsInvalidArgumentException() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        HashMap<String, String> argumentMap = new HashMap<>();
        argumentMap.put("description", "borrow book");
        argumentMap.put("extra", "extra");
        try {
            TodoCommand command = new TodoCommand(argumentMap);
            command.execute(tasks, ui);
            fail("InvalidArgumentException should be thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Extra arguments found for todo!", e.getMessage());
        }
    }
}
>>>>>>> Stashed changes
