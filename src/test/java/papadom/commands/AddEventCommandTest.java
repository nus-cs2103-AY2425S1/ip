package papadom.commands;

import org.junit.jupiter.api.Test;
import papadom.Exceptions.IncorrectTaskInputFormatException;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddEventCommandTest {
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
        command.execute(taskList, ui, storage);
        // Reset System.out to its original stream
        System.setOut(originalOut);

        // Assert
        String expectedOutput = "____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                "  [E][ ] project meeting (from: Mon 2pm to: 4pm)\n" +
                " Now you have 1 tasks in the list.\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
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
                "  For event tasks: event [task] /from [date & time] /to [date & time]";
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
                "  For event tasks: event [task] /from [date & time] /to [date & time]";
        assertEquals(expectedMessage, thrown.getMessage());
    }

}
