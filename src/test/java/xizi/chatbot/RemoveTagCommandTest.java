package xizi.chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xizi.chatbot.command.RemoveTagCommand;
import xizi.chatbot.task.Deadline;
import xizi.chatbot.task.Event;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.task.Todo;


/**
 * Tests for the {@link RemoveTagCommand} class.
 */
public class RemoveTagCommandTest {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    /**
     * Sets up the testing environment before each test.
     * This method initializes the {@code TaskList}, {@code Storage}, and {@code Ui} objects,
     * and populates the task list with test tasks.
     *
     * @throws IOException if an I/O error occurs while setting up the environment.
     */
    @BeforeEach
    public void setUp() throws IOException {
        String testFilePath = "./data/removeTagCommandTest.txt";
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }

        storage = new Storage(testFilePath);
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        ui = new Ui(System.in, printStream);
        tasks = new TaskList();

        tasks.addTask(new Todo("read book"));
        tasks.addTask(new Deadline("submit report", LocalDateTime.of(2024, 12, 1, 23, 59)));
        tasks.addTask(new Event("team meeting",
                LocalDateTime.of(2024, 12, 5, 14, 0), LocalDateTime.of(2024, 12, 5, 16, 0)));

        tasks.getItems().get(0).addTag("urgent"); // Tagging task 1 with #urgent
        storage.saveTasks(tasks.getItems());
    }

    /**
     * Tests the {@code execute()} method of the {@link RemoveTagCommand} class when a tag is successfully removed.
     *
     * @throws IOException    if an I/O error occurs during task manipulation.
     */
    @Test
    public void testValidTaskRemoveTag() throws IOException, XiziException {
        // Create a RemoveTagCommand for task 1 with tag #urgent
        RemoveTagCommand command = new RemoveTagCommand("remove tag 1 #urgent");

        // Execute the command
        command.execute(tasks, storage, ui);

        // Verify that the tag was removed from the task
        Task task = tasks.getItems().get(0);
        assertFalse(task.getTags().contains("urgent"));

        // Verify that the UI shows the correct message
        String output = outputStream.toString();
        assertTrue(output.contains("Removed tag"));
    }

    /**
     * Tests the {@code execute()} method of the {@link RemoveTagCommand} class
     * when trying to remove a tag from a non-existing task.
     *
     * @throws IOException if an I/O error occurs during task manipulation.
     */
    @Test
    public void testInvalidTaskNumber() throws IOException, XiziException {
        // Create a RemoveTagCommand for a non-existing task (task 5)
        RemoveTagCommand command = new RemoveTagCommand("remove tag 5 #urgent");

        // Execute the command
        command.execute(tasks, storage, ui);

        // Get the output from the captured output stream
        String output = outputStream.toString();

        // Verify that an error message was printed to the UI
        assertTrue(output.contains("Invalid task number."));
    }

    /**
     * Tests the {@code execute()} method of the {@link RemoveTagCommand} class
     * when the task number provided is non-numeric.
     *
     */
    @Test
    public void testNonNumericTaskNumber() {
        // Invalid input where task number is non-numeric
        String userInput = "remove tag one #urgent";

        Exception exception = assertThrows(XiziException.class, () -> {
            new RemoveTagCommand(userInput).execute(tasks, storage, ui);
        });

        assertEquals("[remove, tag, one, #urgent]", exception.getMessage());
    }

    /**
     * Tests the {@code execute()} method of the {@link RemoveTagCommand} class when the input format is incorrect.
     *
     */
    @Test
    public void testInvalidInputFormat() {
        // Invalid input format with missing tag
        String userInput = "remove tag 1";

        Exception exception = assertThrows(XiziException.class, () -> {
            new RemoveTagCommand(userInput).execute(tasks, storage, ui);
        });

        assertEquals("Invalid format. Usage: remove tag <task number> <tag>", exception.getMessage());
    }

    /**
     * Tests the {@code execute()} method of the {@link RemoveTagCommand} class
     * when attempting to remove a tag from a task that has no tags.
     *
     * @throws IOException if an I/O error occurs during task manipulation.
     */
    @Test
    public void testTaskWithoutTag() throws IOException, XiziException {
        // Remove the tag from the first task to make it untagged
        tasks.getItems().get(0).removeTag("urgent");
        storage.saveTasks(tasks.getItems());

        // Create a RemoveTagCommand for task 1 with a tag that has already been removed
        RemoveTagCommand command = new RemoveTagCommand("remove tag 1 #urgent");

        // Execute the command
        command.execute(tasks, storage, ui);

        // Get the output from the captured output stream
        String output = outputStream.toString();

        // Verify that an error message was printed to the UI
        assertTrue(output.contains("Tag not found on task."));
    }
}
