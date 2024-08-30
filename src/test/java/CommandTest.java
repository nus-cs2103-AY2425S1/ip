import Commands.AddTaskCommand;
import Commands.Command;
import Commands.DeleteCommand;
import Parser.Parser;
import Storage.Storage;
import Task.TaskList;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandTest {

    private final String STORAGE_PATH  = "src/main/data/blitz.txt";
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void actionBasedOnInputTest1() {
        TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
        AddTaskCommand command = new AddTaskCommand("blah");
        command.execute(taskList);
        assertEquals("THIS IS NOT A VALID TASK LAH", testOut.toString().trim());
    }

    @Test
    public void actionBasedOnInputTest2() {
        TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
        AddTaskCommand command = new AddTaskCommand("todo");
        command.execute(taskList);
        assertEquals("Wah, no description then I record what?", testOut.toString().trim());
    }

    @Test
    public void actionBasedOnInputTest3() {
        TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
        AddTaskCommand command = new AddTaskCommand("event /from 10/10/2019 /to 11/10/2019");
        command.execute(taskList);
        assertEquals("Wah, no description then I record what?", testOut.toString().trim());
    }

    @Test
    public void actionBasedOnInputTest4() {
        TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
        DeleteCommand command = new DeleteCommand(10000);
        command.execute(taskList);
        assertEquals("No valid index was given!!", testOut.toString().trim());
    }
}
