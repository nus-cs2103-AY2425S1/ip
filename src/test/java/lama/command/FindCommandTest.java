package lama.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * Test cases for find command.
 * Unit test cases for find command class.
 */
public class FindCommandTest {

    private static final String BAR = "____________________________________________________________";

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Tests the run method of FindCommand to ensure it correctly finds and displays tasks
     *
     * @throws LamaException Thrown if there is an error in task operations.
     */
    @Test
    public void runTest() throws LamaException {
        System.setOut(new PrintStream(outputStream));
        Ui ui = new Ui();
        Storage storage = new Storage("./testing.txt", "./testAliasList.txt");
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        Task task2 = new Todo("Sleep");
        taskList.add(task);
        taskList.add(task2);

        Command findCommand = new FindCommand("read");
        findCommand.run(taskList, storage, ui);
        assertEquals(2, taskList.size());

        String output = BAR + "\r\nHere are the matching tasks in your list:\r\n"
                + "1.[T][ ] Read Book\r\n" + BAR + "\n\r\n";

        assertEquals(output, outputStream.toString());
    }

    /**
     * Tests the run method of FindCommand when there is no keyword found in the task list.
     *
     * @throws LamaException Thrown if there is an error in task operations.
     */
    @Test
    public void runNoneTest() throws LamaException {
        System.setOut(new PrintStream(outputStream));
        Ui ui = new Ui();
        Storage storage = new Storage("./testing.txt", "./testAliasList.txt");
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        Task task2 = new Todo("Sleep");
        taskList.add(task);
        taskList.add(task2);

        Command findCommand = new FindCommand("xxxx");
        findCommand.run(taskList, storage, ui);
        assertEquals(2, taskList.size());

        String output = BAR + "\r\nNo matching tasks found!\r\n" + BAR + "\n\r\n";

        assertEquals(output, outputStream.toString());
    }
}
