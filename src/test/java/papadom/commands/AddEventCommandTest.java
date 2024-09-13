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

public class AddEventCommandTest {
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
        Storage storage = new Storage("testStorage.txt");
        TaskList taskList = new TaskList(storage);
        Ui ui = new Ui();
        AddEventCommand command = new AddEventCommand("event project meeting /from Mon 2pm /to 4pm");

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
                "  [E][ ] project meeting (from: Mon 2pm to: 4pm)\n" +
                " Now you have 1 tasks in the list.";
        assertEquals(expectedOutput, output);
    }
    @Test
    public void testExecuteWithInvalidInput() {
        // Arrange
        Storage storage = new Storage("testStorage.txt");
        TaskList taskList = new TaskList(storage);
        Ui ui = new Ui();
        AddEventCommand command = new AddEventCommand("event concert");

        // Act & Assert
        IncorrectTaskInputFormatException thrown = assertThrows(
                IncorrectTaskInputFormatException.class,
                () -> command.execute(taskList, ui, storage),
                "Expected command.execute() to throw IncorrectTaskInputFormatException, but it didn't"
        );

        // Optionally check the exception message
        String expectedMessage = " Please enter the correct format!\n" +
                "  For todo tasks: todo [task]\n" +
                "  For deadline tasks: deadline [task] /by yyyy-mm-dd OR yyyy-mm-dd hh-mm\n" +
                "  For event tasks: event [task] /from [date & time] /to [date & time]\n" +
                "  To find specific events with keyword: find [keyword]";
        assertEquals(expectedMessage, thrown.getMessage());
    }
    @Test
    public void testExecuteWithOnlyFrom() {
        // Arrange
        Storage storage = new Storage("testStorage.txt");
        TaskList taskList = new TaskList(storage);
        Ui ui = new Ui();
        AddEventCommand command = new AddEventCommand("event concert /from 21 Aug 2023");

        // Act & Assert
        IncorrectTaskInputFormatException thrown = assertThrows(
                IncorrectTaskInputFormatException.class,
                () -> command.execute(taskList, ui, storage),
                "Expected command.execute() to throw IncorrectTaskInputFormatException, but it didn't"
        );

        // Optionally check the exception message
        String expectedMessage = " Please enter the correct format!\n" +
                "  For todo tasks: todo [task]\n" +
                "  For deadline tasks: deadline [task] /by yyyy-mm-dd OR yyyy-mm-dd hh-mm\n" +
                "  For event tasks: event [task] /from [date & time] /to [date & time]\n" +
                "  To find specific events with keyword: find [keyword]";
        assertEquals(expectedMessage, thrown.getMessage());
    }

}
