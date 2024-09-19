package xizi.chatbot;
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



public class TagCommandTest {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private final String testFilePath = "./data/tagCommandTest.txt"; // File to save test tasks
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a new file for testing storage
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

    @Test
    public void execute_validTask_tagsTask() throws IOException, XiziException {
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

    @Test
    public void execute_invalidTask_showsErrorMessage() throws IOException {
        // Create a TagCommand for a non-existing task (task 5)
        TagCommand command = new TagCommand("tag 5 #urgent");

        // Execute the command
        command.execute(tasks, storage, ui);

        // Get the output from the captured output stream
        String output = outputStream.toString();

        // Verify that an error message was printed to the UI
        assertTrue(output.contains("Invalid task number."));


    }
}
