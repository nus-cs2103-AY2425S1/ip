package papadom.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import papadom.exceptions.IncorrectTaskInputFormatException;
import papadom.storage.Storage;
import papadom.storage.TaskList;
import papadom.utils.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AddTodoCommandTest {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setup() {
        // Reinitialize TaskList, Storage, and Ui before each test to start fresh
        storage = new Storage("testStorage.txt");
        storage.clearTasks();
        taskList = new TaskList(storage);
        ui = new Ui();
    }
    @Test
    public void testExecuteWithValidInput() throws IncorrectTaskInputFormatException {
        // Arrange
        AddTodoCommand command = new AddTodoCommand("todo buy milk");

        // Capture the system output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        String output = command.execute(taskList, ui, storage);

        // Reset System.out to its original stream
        System.setOut(originalOut);

        // Assert
        String expectedOutput = " Got it. I've added this task:\n" +
                "  [T][ ] buy milk\n" +
                " Now you have 1 tasks in the list.";
        assertEquals(expectedOutput, output);
    }
    @Test
    public void testExecuteOnlyWithTypeThrowsException() {
        // Arrange
        AddTodoCommand command = new AddTodoCommand("todo");

        // Act & Assert
        IncorrectTaskInputFormatException thrown = assertThrows(
                IncorrectTaskInputFormatException.class, // Expected exception type
                () -> command.execute(taskList, ui, storage), // Code that should throw the exception
                "Expected command.execute() to throw IncorrectTaskInputFormatException, but it didn't"
        );

        // Optionally check the exception message
        assertEquals(" Please enter the correct format!\n" +
                        "  For todo tasks: todo [task]\n" +
                        "  For deadline tasks: deadline [task] /by yyyy-mm-dd OR yyyy-mm-dd hh-mm\n" +
                        "  For event tasks: event [task] /from [date & time] /to [date & time]\n" +
                        "  To find specific events with keyword: find [keyword]",
                thrown.getMessage());
    }
    @Test
    public void testExecuteWithoutTaskThrowsException() {
        // Arrange
        AddTodoCommand command = new AddTodoCommand("todo ");

        // Act & Assert
        IncorrectTaskInputFormatException thrown = assertThrows(
                IncorrectTaskInputFormatException.class, // Expected exception type
                () -> command.execute(taskList, ui, storage), // Code that should throw the exception
                "Expected command.execute() to throw NoTaskException, but it didn't"
        );

        // Optionally check the exception message
        assertEquals(" Please enter the correct format!\n"
                        + "  For todo tasks: todo [task]\n"
                        + "  For deadline tasks: deadline [task] /by yyyy-mm-dd OR yyyy-mm-dd hh-mm\n"
                        + "  For event tasks: event [task] /from [date & time] /to [date & time]\n"
                        + "  To find specific events with keyword: find [keyword]",
                thrown.getMessage());
    }
}

