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
 * Test class for DeleteCommand
 * Contains unit test case for DeleteCommand class
 */
public class DeleteCommandTest {

    private static final String BAR = "____________________________________________________________";
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Set up the testing environment before each test.
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
     * Verifies that the task is correctly deleted in the task list, output
     * as expected, save the new task list properly to the storage.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void runTest() throws LamaException {

        System.setOut(new PrintStream(outputStream));

        Ui ui = new Ui();
        Storage storage = new Storage("./testAdd.txt", "./testAliasList.txt");
        Task todo = new Todo("Read Book");
        Task todo2 = new Todo("Return Book");
        TaskList taskList = new TaskList();

        taskList.add(todo);
        taskList.add(todo2);

        Command deleteCommand = new DeleteCommand(1);

        assertEquals(2, taskList.size());
        assertEquals("[T][ ] Read Book", taskList.get(0).toString());
        assertEquals("[T][ ] Return Book", taskList.get(1).toString());

        deleteCommand.run(taskList, storage, ui);
        assertEquals(1, taskList.size());

        String output = BAR + "\r\nNoted. I've removed this task:\r\n  " + todo2.toString()
                + "\r\nNow you have 1 tasks in the list.\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());

        TaskList storageTaskList = new TaskList(storage.loadTask());
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
