import commands.AddTaskCommand;
import commands.DeleteCommand;
import storage.Storage;
import task.TaskList;

import exceptions.InvalidDateException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            AddTaskCommand command = new AddTaskCommand("blah");
            command.execute(taskList);
            assertEquals("THIS IS NOT A VALID TASK LAH", testOut.toString().trim());
        } catch (InvalidDateException e) {
            System.out.println();
        }
    }

    @Test
    public void actionBasedOnInputTest2() {
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            AddTaskCommand command = new AddTaskCommand("todo");
            command.execute(taskList);
            assertEquals("Wah, no description then I record what?", testOut.toString().trim());
        } catch (InvalidDateException e) {
            System.out.println();
        }
    }

    @Test
    public void actionBasedOnInputTest3() {
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            AddTaskCommand command = new AddTaskCommand("event /from 10/10/2019 /to 11/10/2019");
            command.execute(taskList);
            assertEquals("Wah, no description then I record what?", testOut.toString().trim());
        } catch (InvalidDateException e) {

        }
    }

    @Test
    public void actionBasedOnInputTest4() {
        try {
            TaskList taskList = new TaskList(Storage.createStorage(STORAGE_PATH));
            DeleteCommand command = new DeleteCommand(10000);
            command.execute(taskList);
            assertEquals("No valid index was given!!", testOut.toString().trim());
        } catch (InvalidDateException e) {

        }
    }
}
