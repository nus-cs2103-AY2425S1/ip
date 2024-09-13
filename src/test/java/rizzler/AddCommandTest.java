package rizzler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class AddCommandTest {
    private static final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void executeCommand_success() throws Exception {
        Ui ui = new Ui();
        FileStorage fileStorage = new FileStorage("data/test.txt");
        TaskList taskList = new TaskList(new ArrayList<>());
        String[] fullCommand = {"todo", "test", "1"};
        new AddCommand(fullCommand).execute(taskList, ui, fileStorage);
        assertEquals(testOut.toString(),
                "_______________________________________________________\n"
                        + "Gotcha! I've added the new task for you:\n"
                        + "[T][ ] test 1\n"
                        + "Now you have 1 tasks in the list.\n"
                        + "_______________________________________________________\n"
                        + "\r\n");
        testOut.reset();
    }

    @Test
    public void executeCommand_incorrectInputFormat() throws Exception {
        // todo task name should not be empty
        try {
            Ui ui = new Ui();
            FileStorage fileStorage = new FileStorage("data/test.txt");
            TaskList taskList = new TaskList(new ArrayList<>());
            String[] fullCommand = {"todo"};
            new AddCommand(fullCommand).execute(taskList, ui, fileStorage);
            fail(); // this should never be reached
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Please add in the task description\n"
                    + "Format:\n"
                    + "todo [task name]");
        }
        // deadline task name should not be empty
        try {
            Ui ui = new Ui();
            FileStorage fileStorage = new FileStorage("data/test.txt");
            TaskList taskList = new TaskList(new ArrayList<>());
            String[] fullCommand = {"deadline", "/by", "2024-08-30"};
            new AddCommand(fullCommand).execute(taskList, ui, fileStorage);
            fail(); // this should never be reached
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "This command was entered incorrectly\n"
                    + "Format:\n"
                    + "deadline [task name] /by [yyyy-mm-dd]");
        }
        // deadline date format should not be wrong
        try {
            Ui ui = new Ui();
            FileStorage fileStorage = new FileStorage("data/test.txt");
            TaskList taskList = new TaskList(new ArrayList<>());
            String[] fullCommand = {"deadline", "test", "2", "/by", "September"};
            new AddCommand(fullCommand).execute(taskList, ui, fileStorage);
            fail(); // this should never be reached
        } catch (RizzlerException e) {
            assertEquals(e.getMessage(),
                    "Please put a valid date-time format\n"
                            + "Format:\n"
                            + "deadline [task name] /by [yyyy-mm-dd]");
        }
    }
}
