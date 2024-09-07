package demurebot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import demurebot.task.TaskList;
import demurebot.task.Todo;
import demurebot.ui.Ui;


public class MarkCommandTest {

    private TaskList taskList;
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        taskList = new TaskList(new ArrayList<>());
        ui = new Ui();
        taskList.addTask(new Todo("Sample Task 1", false));
    }

    @Test
    public void testExecuteWithValidNumber() {
        MarkCommand command = new MarkCommand("1");
        command.execute(taskList, ui);
        assertEquals("""
                Nice! You have completed
                  [T][X] Sample Task 1
                Very demure, very mindful!
                """, outContent.toString());
    }

    @Test
    public void testExecuteWithWord() {
        MarkCommand command = new MarkCommand("test");
        command.execute(taskList, ui);
        assertEquals("Please enter a positive integer after mark.\n\n", outContent.toString());
    }

    @Test
    public void testExecuteWithIndexOutOfRange() {
        MarkCommand command = new MarkCommand("10");
        command.execute(taskList, ui);
        assertEquals("Invalid index: 10\nPlease enter a number within 1 to number of current tasks.\n\n",
                outContent.toString());
    }

    @Test
    public void testExecuteWithNegativeIndex() {
        MarkCommand command = new MarkCommand("-1");
        command.execute(taskList, ui);
        assertEquals("Invalid index: -1\nPlease enter a number within 1 to number of current tasks.\n\n",
                outContent.toString());
    }
}
