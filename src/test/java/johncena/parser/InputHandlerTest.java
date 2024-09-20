package johncena.parser;

import johncena.art.Logo;

import johncena.exceptions.CenaException;
import johncena.exceptions.CenaUnknownCommandException;

import johncena.tasks.Task;
import johncena.tasks.Deadline;
import johncena.tasks.Event;
import johncena.tasks.Todo;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


import johncena.storage.Storage;

import java.io.IOException;

/**
 * This class tests the InputHandler class.
 */
public class InputHandlerTest {

    private InputHandler inputHandler;
    private ArrayList<Task> tasks;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() throws IOException {
        System.setOut(originalOut);
        outContent.reset();

        // Clean up the test file
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));

        // Reset Storage to default file path
        Storage.setFilePath("./data/CenaTaskList.txt");
    }

    @Test
    public void testTodoCommand() throws CenaException {
        inputHandler.handleInput("todo read book");
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertTrue(task instanceof Todo);
        assertEquals("read book", task.getDescription());
    }

    @Test
    public void testDeadlineCommand() throws CenaException {
        inputHandler.handleInput("deadline return book /by 2024-09-20 1800");
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertTrue(task instanceof Deadline);
        assertEquals("return book", task.getDescription());
        assertEquals("2024-09-20 1800", ((Deadline) task).getBy());
    }

    @Test
    public void testEventCommand() throws CenaException {
        inputHandler.handleInput("event project meeting /from 2024-09-20 1600 /to 2024-09-21 1800");
        Task task = tasks.get(0);
        assertTrue(task instanceof Event);
        assertEquals("project meeting", task.getDescription());
        assertEquals("2024-09-20 1600", ((Event) task).getFrom());
        assertEquals("2024-09-21 1800", ((Event) task).getTo());
    }

    @Test
    public void testListCommand() throws CenaException {
        inputHandler.handleInput("todo read book");
        inputHandler.handleInput("deadline return book /by 2024-09-20 1800");
        inputHandler.handleInput("event project meeting /from 2024-09-20 1600 /to 2024-09-21 1800");
        outContent.reset();
        inputHandler.handleInput("list");
        String expectedOutput = "____________________________________________________________\n"
                + " Here are the tasks in your list:\n"
                + " 1.[T][ ] read book\n"
                + " 2.[D][ ] return book (by: 2024-09-20 1800)\n"
                + " 3.[E][ ] project meeting (from: 2024-09-20 1600 to: 2024-09-21 1800)\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testMarkCommand() throws CenaException {
        inputHandler.handleInput("todo read book");
        inputHandler.handleInput("mark 1");
        Task task = tasks.get(0);
        assertTrue(task.isTaskDone());
    }

    @Test
    public void testUnmarkCommand() throws CenaException {
        inputHandler.handleInput("todo read book");
        inputHandler.handleInput("mark 1");
        inputHandler.handleInput("unmark 1");
        Task task = tasks.get(0);
        assertFalse(task.isTaskDone());
    }


    @Test
    public void testInvalidTaskIndex() throws CenaException {
        outContent.reset();
        inputHandler.handleInput("mark 1"); // No tasks yet
        String expectedOutput = "____________________________________________________________\n"
                + " OOPS!!! The task index is invalid.\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testInvalidDeadlineFormat() throws CenaException {
        outContent.reset();
        inputHandler.handleInput("deadline return book");
        String expectedOutput = "____________________________________________________________\n"
                + " OOPS!!! Incorrect description for deadline. It should contain only /by.\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testInvalidEventFormat() throws CenaException {
        outContent.reset();
        inputHandler.handleInput("event project meeting /from 2024-09-20");
        String expectedOutput = "____________________________________________________________\n"
                + " OOPS!!! Incorrect description for event. It should contain /from and /to.\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
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
        outContent.reset();
        inputHandler.handleInput("hello");
        String expectedOutput = "____________________________________________________________\n"
                + "Hello from\n" + Logo.getLogo() + "\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testHelpCommand() throws CenaException {
        outContent.reset();
        inputHandler.handleInput("help");
        String expectedOutput = "____________________________________________________________\n"
                + "Here are the commands you can use:\n"
                + "  bye - Exits the program\n"
                + "  list - Lists all tasks\n"
                + "  mark [task number] - Marks a task as done\n"
                + "  unmark [task number] - Marks a task as not done\n"
                + "  delete [task number] - Deletes a task\n"
                + "  todo [description] - Adds a todo task\n"
                + "  deadline [description] /by [due date] - Adds a deadline task\n"
                + "  event [description] /from [start date] /to [end date] - Adds an event task\n"
                + "  on [date] - Lists all tasks on a specific date\n"
                + "  hello - Displays the welcome message\n"
                + "  help - Displays the list of commands\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testOnCommand() throws CenaException {
        inputHandler.handleInput("deadline submit report /by 2024-09-20 1800");
        inputHandler.handleInput("event conference /from 2024-09-20 1600 /to 2024-09-22 1800");
        outContent.reset();
        inputHandler.handleInput("on 2024-09-20");
        String expectedOutput = "____________________________________________________________\n"
                + " Here are the tasks on Sep 20 2024:\n"
                + " 1.[D][ ] submit report (by: 2024-09-20 1800)\n"
                + " 2.[E][ ] conference (from: 2024-09-20 1600 to: 2024-09-22 1800)\n"
                + "____________________________________________________________";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testDeleteCommand() throws CenaException {
        inputHandler.handleInput("todo read book");
        inputHandler.handleInput("deadline return book /by 2024-09-20 1800");
        inputHandler.handleInput("delete 1");
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Deadline);
    }
}