package lama.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;
import lama.task.Todo;



/**
 * Test class for UnmarkCommand
 * Contains unit test case for UnmarkCommand class
 */
public class UnmarkCommandTest {
    private static final String BAR = "____________________________________________________________";
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Set up the testing environment after each test.
     * Deletes any existing file to ensure a clean state.
     */
    @BeforeEach
    public void setup() {
        try {
            Files.deleteIfExists(new File("./testAdd.txt").toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test the run method.
     * Verifies that the task is correctly unmarked, output
     * as expected, save properly to the storage.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void runTest() throws LamaException {

        System.setOut(new PrintStream(outputStream));

        Task todo = new Todo("Read Book");
        TaskList taskList = new TaskList();
        todo.markAsDone();
        taskList.add(todo);
        Ui ui = new Ui();
        Storage storage = new Storage("./testAdd.txt", "./testAliasList.txt");

        assertEquals(1, taskList.size());
        assertEquals("[T][X] Read Book", taskList.get(0).toString());

        Command unmarkCommand = new UnmarkCommand(0);

        unmarkCommand.run(taskList, storage, ui);

        String output = BAR + "\r\nOK, I've marked this task as not done yet:\r\n  " + taskList.get(0) + "\r\n"
                + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());

        TaskList storageTaskList = new TaskList(storage.loadTask());
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] Read Book", taskList.get(0).toString());

        assertEquals(1, storageTaskList.size());
        assertEquals("[T][ ] Read Book", storageTaskList.get(0).toString());
    }

    /**
     * Set up the testing environment after each test.
     * Deletes any existing file to ensure a clean state.
     */
    @AfterEach
    public void reset() {
        try {
            Files.deleteIfExists(new File("./testAdd.txt").toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
