package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link Parser} class.
 * This class contains unit tests for the methods in the {@link Parser} class,
 * specifically for parsing commands and reading tasks from a file.
 */
public class ParserTest {

    /**
     * Tests the {@link Parser#handleInput(String, TaskList, Ui)} method
     * with a valid "todo" command.
     * Verifies that the task is correctly added to the task list and its
     * description matches the command input.
     */
    @Test
    public void testParseValidNewCommand() {
        String command = "todo test command";
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        try {
            Parser.handleInput(command, taskList, ui);
            assertEquals(1, taskList.size());
            assertEquals("test command", taskList.getTask(0).getDescription());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Tests the {@link Parser#parseTaskFromFile(String)} method.
     * Verifies that a task is correctly parsed from a file string representation
     * and added to the task list. Checks that the task's description and status
     * are correctly set according to the file string.
     */
    @Test
    public void testParseReadFromFile() {
        String fileString = "T | 1 | read from file test";
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        try {
            tasks.add(Parser.parseTaskFromFile(fileString));
            taskList.setTasks(tasks);
            assertEquals(1, taskList.size());
            assertEquals("read from file test", taskList.getTask(0).getDescription());
            assertTrue(taskList.getTask(0).isDone());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }
    }
}
