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


public class DeleteCommandTest {

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
        DeleteCommand command = new DeleteCommand("1");
        command.execute(taskList, ui);
        assertEquals("""
                Ok dear, I've removed this task:
                  [T][ ] Sample Task 1
                Now you have 0 tasks in the list.
                """, outContent.toString());
    }

    @Test
    public void testExecuteWithWord() {
        DeleteCommand command = new DeleteCommand("test");
        command.execute(taskList, ui);
        assertEquals("Please enter a positive integer after delete.\n\n", outContent.toString());
    }

    @Test
    public void testExecuteWithIndexOutOfRange() {
        DeleteCommand command = new DeleteCommand("10");
        command.execute(taskList, ui);
        assertEquals("Invalid index: 10\nPlease enter a number within 1 to number of current tasks.\n\n",
                outContent.toString());
    }

    @Test
    public void testExecuteWithNegativeIndex() {
        DeleteCommand command = new DeleteCommand("-1");
        command.execute(taskList, ui);
        assertEquals("Invalid index: -1\nPlease enter a number within 1 to number of current tasks.\n\n",
                outContent.toString());
    }
}
