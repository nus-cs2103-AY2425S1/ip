package papadom.commands;

import org.junit.jupiter.api.Test;
import papadom.Storage.Storage;
import papadom.Ui;
import papadom.Storage.TaskList;
import papadom.Exceptions.NoTaskException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddTodoCommandTest {
    @Test
    public void testExecute() throws NoTaskException {
        // Arrange
        Storage storage = new Storage("testStorage.txt");
        TaskList taskList = new TaskList(storage);
        Ui ui = new Ui();
        AddTodoCommand command = new AddTodoCommand("todo buy milk");

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
                "  [T][ ] buy milk\n" +
                " Now you have 1 tasks in the list.\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}

