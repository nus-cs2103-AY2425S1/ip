package johncena.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import johncena.art.Logo;
import johncena.commands.Command;
import johncena.exceptions.CenaException;
import johncena.exceptions.CenaUnknownCommandException;
import johncena.storage.Storage;
import johncena.tasks.Deadline;
import johncena.tasks.Event;
import johncena.tasks.Task;
import johncena.tasks.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputHandlerTest {

    private InputHandler inputHandler;
    private ArrayList<Task> tasks;
    private final String TEST_FILE_PATH = "./data/CenaTaskListTest.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Ensure the data directory exists
        Files.createDirectories(Paths.get("./data"));

        // Delete the test file if it exists and create a new empty one
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
        Files.createFile(Paths.get(TEST_FILE_PATH));

        // Set the Storage to use the test file
        Storage.setFilePath(TEST_FILE_PATH);

        tasks = Storage.loadTasks(); // Should load an empty list
        inputHandler = new InputHandler(tasks);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up the test file
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));

        // Reset Storage to default file path
        Storage.setFilePath("./data/CenaTaskList.txt");
    }

    @Test
    public void testTodoCommand() throws CenaException {
        Command command = inputHandler.handleInput("todo read book");
        String output = command.execute();
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertTrue(task instanceof Todo);
        assertEquals("read book", task.getDescription());
    }

    @Test
    public void testDeadlineCommand() throws CenaException {
        Command command = inputHandler.handleInput("deadline return book /by 2024-09-20 1800");
        String output = command.execute();
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertTrue(task instanceof Deadline);
        assertEquals("return book", task.getDescription());
        assertEquals("2024-09-20 1800", ((Deadline) task).getBy());
    }

    @Test
    public void testEventCommand() throws CenaException {
        Command command = inputHandler.handleInput("event project meeting /from 2024-09-20 1600 /to 2024-09-21 1800");
        String output = command.execute();
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertTrue(task instanceof Event);
        assertEquals("project meeting", task.getDescription());
        assertEquals("2024-09-20 1600", ((Event) task).getFrom());
        assertEquals("2024-09-21 1800", ((Event) task).getTo());
    }

    @Test
    public void testListCommand() throws CenaException {
        // Add tasks first
        inputHandler.handleInput("todo read book").execute();
        inputHandler.handleInput("deadline return book /by 2024-09-20 1800").execute();
        inputHandler.handleInput("event project meeting /from 2024-09-20 1600 /to 2024-09-21 1800").execute();

        // Test list command
        Command command = inputHandler.handleInput("list");
        String output = command.execute();
        String expectedOutput = " Alright, Champ! Here are the tasks in your list:\n"
                + " 1.[T][ ] read book\n"
                + " 2.[D][ ] return book (by: 2024-09-20 1800)\n"
                + " 3.[E][ ] project meeting (from: 2024-09-20 1600 to: 2024-09-21 1800)\n"
                + "Keep pushing forward and never give up!\n";
        assertEquals(expectedOutput.trim(), output.trim());
    }

    @Test
    public void testMarkCommand() throws CenaException {
        // Add a task first
        inputHandler.handleInput("todo read book").execute();

        // Mark the task
        Command command = inputHandler.handleInput("mark 1");
        String output = command.execute();
        Task task = tasks.get(0);
        assertTrue(task.isTaskDone());

        String expectedOutput = " You did it, Champ! I've marked this task as done:\n"
                + "   [T][X] read book\n";
        assertEquals(expectedOutput.trim(), output.trim());
    }

    @Test
    public void testUnmarkCommand() throws CenaException {
        // Add and mark a task first
        inputHandler.handleInput("todo read book").execute();
        inputHandler.handleInput("mark 1").execute();

        // Unmark the task
        Command command = inputHandler.handleInput("unmark 1");
        String output = command.execute();
        Task task = tasks.get(0);
        assertFalse(task.isTaskDone());

        String expectedOutput = " Alright , Champ! I've marked this task as not done yet:\n"
                + "   [T][ ] read book\n";
        assertEquals(expectedOutput.trim(), output.trim());
    }

    @Test
    public void testInvalidTaskIndex() throws CenaException {
        Command command = inputHandler.handleInput("mark 1"); // No tasks yet
        String actualOutput;
        try {
            actualOutput = command.execute();
        } catch (CenaException e) {
            actualOutput = "OOPS!!! " + e.getMessage();
        }
        String expectedOutput = "OOPS!!! The task index is invalid.";
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testUnknownCommandException() {
        Exception exception = assertThrows(CenaUnknownCommandException.class, () -> {
            inputHandler.handleInput("unknowncommand");
        });
        assertEquals("I'm sorry, but I don't know what that means :-(", exception.getMessage());
    }

    @Test
    public void testHelloCommand() throws CenaException {
        Command command = inputHandler.handleInput("hello");
        String actualOutput = command.execute();
        String expectedOutput = "Hello from\n" + Logo.getLogo() + "\n"
                + "You can't see me! But I'm here to help. What can I do for you today?\n";
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testHelpCommand() throws CenaException {
        Command command = inputHandler.handleInput("help");
        String actualOutput = command.execute();
        String expectedOutput = "Here are the commands you can use:\n"
                + "  bye - Exits the program\n"
                + "  list - Lists all tasks\n"
                + "  mark [task number] - Marks a task as done\n"
                + "  unmark [task number] - Marks a task as not done\n"
                + "  delete [task number] - Deletes a task\n"
                + "  todo [description] - Adds a todo task\n"
                + "  deadline [description] /by [due date] - Adds a deadline task\n"
                + "  event [description] /from [start date] /to [end date] - Adds an event task\n"
                + "  find [keyword] - Finds tasks with a specific keyword\n"
                + "  after [desription] /after [date] - Adds a task that you will do after a specific date\n"
                + "  on [date] - Lists all tasks on a specific date\n"
                + "  hello - Displays the welcome message\n"
                + "  help - Displays the list of commands\n";
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testOnCommand() throws CenaException {
        inputHandler.handleInput("deadline submit report /by 2024-09-20 1800").execute();
        inputHandler.handleInput("event conference /from 2024-09-20 1600 /to 2024-09-22 1800").execute();

        Command command = inputHandler.handleInput("on 2024-09-20");
        String actualOutput = command.execute();
        String expectedOutput = " Alright, Champ! Here are the tasks on Sep 20 2024:\n"
                + " 1.[D][ ] submit report (by: 2024-09-20 1800)\n"
                + " 2.[E][ ] conference (from: 2024-09-20 1600 to: 2024-09-22 1800)\n";
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testDeleteCommand() throws CenaException {
        inputHandler.handleInput("todo read book").execute();
        inputHandler.handleInput("deadline return book /by 2024-09-20 1800").execute();

        Command command = inputHandler.handleInput("delete 1");
        String actualOutput = command.execute();
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Deadline);

        String expectedOutput = " Noted. I've removed this task. Now, you can't see me and neither can you see that task:\n"
                + "   [T][ ] read book\n"
                + " Now you have 1 tasks in the list.\n";
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
