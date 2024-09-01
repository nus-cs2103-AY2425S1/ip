package barney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import barney.action.commands.DeadlineCommand;
import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.DeadlineTask;
import barney.ui.Ui;

/**
 * This class contains unit tests for the {@link DeadlineCommand} class.
 */
public class DeadlineCommandTest {

<<<<<<< Updated upstream
=======
    /**
     * Tests that a deadline task is added when the arguments are valid.
     */
>>>>>>> Stashed changes
    @Test
    public void execute_validArguments_addsDeadlineTask() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        HashMap<String, String> argumentMap = new HashMap<>();
        argumentMap.put("description", "return book");
        argumentMap.put("by", "June 6th");

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            DeadlineCommand command = new DeadlineCommand(argumentMap);
            command.execute(tasks, ui);

            // Verify the task was added
            assertEquals(1, tasks.size());
            assertTrue(tasks.get(0) instanceof DeadlineTask);
            // Verify the output
<<<<<<< Updated upstream
            String expectedOutput = "Got it. I've added this task:\r\n" + //
                    "[D][ ] return book (by: June 6th)\r\n" + //
                    "Now you have 1 tasks in the list.";
=======
            String expectedOutput = """
                    Got it. I've added this task:\r
                    [D][ ] return book (by: June 6th)\r
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
        argumentMap.put("by", "June 6th");

        try {
            DeadlineCommand command = new DeadlineCommand(argumentMap);
            command.execute(tasks, ui);
            fail("InvalidArgumentException should be thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Missing description for deadline!", e.getMessage());
        }
    }

    /**
     * Tests that an InvalidArgumentException is thrown when the by is missing.
     */
    @Test
    public void execute_missingBy_throwsInvalidArgumentException() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        HashMap<String, String> argumentMap = new HashMap<>();
        argumentMap.put("description", "return book");

        try {
            DeadlineCommand command = new DeadlineCommand(argumentMap);
            command.execute(tasks, ui);
            fail("InvalidArgumentException should be thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Missing by for deadline!", e.getMessage());
        }
    }

    /**
     * Tests that an InvalidArgumentException is thrown when the description is
     * empty.
     */
    @Test
    public void execute_emptyDescription_throwsInvalidArgumentException() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        HashMap<String, String> argumentMap = new HashMap<>();
        argumentMap.put("description", "");
        argumentMap.put("by", "June 6th");

        try {
            DeadlineCommand command = new DeadlineCommand(argumentMap);
            command.execute(tasks, ui);
            fail("InvalidArgumentException should be thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("The description of a deadline cannot be empty!", e.getMessage());
        }
    }

    /**
     * Tests that an InvalidArgumentException is thrown when the by is empty.
     */
    @Test
    public void execute_emptyBy_throwsInvalidArgumentException() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        HashMap<String, String> argumentMap = new HashMap<>();
        argumentMap.put("description", "return book");
        argumentMap.put("by", "");

        try {
            DeadlineCommand command = new DeadlineCommand(argumentMap);
            command.execute(tasks, ui);
            fail("InvalidArgumentException should be thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("The by of a deadline cannot be empty!", e.getMessage());
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
        argumentMap.put("description", "return book");
        argumentMap.put("by", "June 6th");
        argumentMap.put("extra", "extra");

        try {
            DeadlineCommand command = new DeadlineCommand(argumentMap);
            command.execute(tasks, ui);
            fail("InvalidArgumentException should be thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Extra arguments found for deadline!", e.getMessage());
        }
    }
}
>>>>>>> Stashed changes
