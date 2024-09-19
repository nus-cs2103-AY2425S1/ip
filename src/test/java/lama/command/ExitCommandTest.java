package lama.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;
import lama.task.Todo;



/**
 * Test class for ExitCommand
 * Contains unit test case for ExitCommand class
 */
public class ExitCommandTest {

    private static final String BAR = "____________________________________________________________";
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Test the run method.
     * Verifies that the loop is exited and output as expected.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void runTest() throws LamaException {

        System.setOut(new PrintStream(outputStream));

        Task todo = new Todo("Read Book");
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./testAdd.txt", "./testAliasList.txt");

        taskList.add(todo);
        Command exitCommand = new ExitCommand();

        exitCommand.run(taskList, storage, ui);

        String output = BAR + "\r\nBye. Hope to see you again soon!\r\n" + BAR + "\r\n";
        assertEquals(output, outputStream.toString());
    }

    /**
     * Test the isExit() method.
     * Verifies that calling isExit() will return true.
     */
    @Test
    public void isExitTest() {
        Command exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }

}
