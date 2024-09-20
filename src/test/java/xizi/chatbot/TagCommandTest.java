package xizi.chatbot;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xizi.chatbot.command.TagCommand;
import xizi.chatbot.task.Deadline;
import xizi.chatbot.task.Event;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.task.Todo;


/**
 * Tests for the {@link TagCommand} class.
 * This test suite checks the functionality of tagging tasks and handling errors in the {@code TagCommand}.
 */
public class TagCommandTest {

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
        // File to save test tasks
        String testFilePath = "./data/tagCommandTest.txt";
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }

        // Set up real Storage and Ui
        storage = new Storage(testFilePath);
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Pass the captured output to the Ui
        ui = new Ui(System.in, printStream);
        tasks = new TaskList();

        // Adding a Todo task
        tasks.addTask(new Todo("read book"));

        // Adding a Deadline task
        tasks.addTask(new Deadline("submit report", LocalDateTime.of(2024, 12, 1, 23, 59)));

        // Adding an Event task
        tasks.addTask(new Event("team meeting",
                LocalDateTime.of(2024, 12, 5, 14, 0), LocalDateTime.of(2024, 12, 5, 16, 0)));

        // Save the initial tasks to storage
        storage.saveTasks(tasks.getItems());
    }

    /**
     * Tests the {@code execute()} method of the {@link TagCommand} class.
     * This test checks that a valid task can be tagged correctly and that the tag persists
     * when tasks are reloaded from storage.
     *
     * @throws IOException    if an I/O error occurs during task manipulation.
     * @throws XiziException  if an error occurs within the command execution.
     */
    @Test
    public void testValidTaskTag() throws IOException, XiziException {
        // Create a TagCommand for task 1 with tag #urgent
        TagCommand command = new TagCommand("tag 1 #urgent");

        // Execute the command
        command.execute(tasks, storage, ui);

        // Verify that the task was tagged correctly
        Task taggedTask = tasks.getItems().get(0);
        assertTrue(taggedTask.getTags().contains("urgent"));

        // Reload the tasks from storage and check if the tag persists
        TaskList reloadedTasks = new TaskList(storage.loadTasks());
        Task reloadedTask = reloadedTasks.getItems().get(0);
        assertTrue(reloadedTask.getTags().contains("urgent"));
    }

    /**
     * Tests the {@code execute()} method of the {@link TagCommand} class when trying to tag a non-existing task.
     * This test ensures that an error message is printed to the UI when an invalid task number is provided.
     *
     * @throws IOException if an I/O error occurs during task manipulation.
     */
    @Test
    public void testInvalidTaskNumber() throws IOException {
        // Create a TagCommand for a non-existing task (task 5)
        TagCommand command = new TagCommand("tag 5 #urgent");

        // Execute the command
        command.execute(tasks, storage, ui);

        // Get the output from the captured output stream
        String output = outputStream.toString();

        // Verify that an error message was printed to the UI
        assertTrue(output.contains("Invalid task number."));
    }

    /**
     * Tests tagging multiple tasks consecutively to ensure consistency across tasks.
     *
     * @throws IOException    if an I/O error occurs during task manipulation.
     * @throws XiziException  if an error occurs within the command execution.
     */
    @Test
    public void testMultipleTaskTagging() throws IOException, XiziException {
        TagCommand command1 = new TagCommand("tag 1 #urgent");
        TagCommand command2 = new TagCommand("tag 2 #important");
        TagCommand command3 = new TagCommand("tag 3 #meeting");

        command1.execute(tasks, storage, ui);
        command2.execute(tasks, storage, ui);
        command3.execute(tasks, storage, ui);

        TaskList reloadedTasks = new TaskList(storage.loadTasks());

        Task task1 = reloadedTasks.getItems().get(0);
        Task task2 = reloadedTasks.getItems().get(1);
        Task task3 = reloadedTasks.getItems().get(2);

        assertTrue(task1.getTags().contains("urgent"));
        assertTrue(task2.getTags().contains("important"));
        assertTrue(task3.getTags().contains("meeting"));
    }
    /**
     * Tests the scenario where the user provides a non-numeric task number in the "tag" command.
     * This should trigger an IllegalArgumentException with the appropriate error message.
     *
     */
    @Test
    public void testNonNumericTaskNumber() {

        // Invalid input where task number is non-numeric
        String userInput = "tag one #important";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TagCommand(userInput).execute(tasks, storage, ui);
        });

        assertEquals("Task number must be a valid integer.", exception.getMessage());
    }
}
