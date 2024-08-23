package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import exceptions.JarException;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Parser parser;
    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        taskList = new TaskList();
        ui = new Ui();
    }

    @Test
    public void parseTask_validToDoCommand_returnsToDoTask() throws JarException {
        Task task = parser.parseTask("todo Read book");
        assertTrue(task instanceof ToDo);
        assertEquals("[T][ ] Read book", task.toString());
    }

    @Test
    public void parseTask_invalidCommand_throwsJarException() {
        Exception exception = assertThrows(JarException.class, () -> {
            parser.parseTask("invalidCommand");
        });

        String expectedMessage = "Unknown command: invalidCommand. Please enter a valid command.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void handleCommand_validToDoCommand_addsTaskToList() throws JarException {
        parser.handleCommand("todo Buy groceries", taskList, ui);
        assertEquals(1, taskList.getTaskCount());
        assertEquals("[T][ ] Buy groceries", taskList.getTask(0).toString());
    }

    @Test
    public void handleCommand_listCommand_showsTasks() throws JarException {
        parser.handleCommand("todo Buy groceries", taskList, ui);
        String listOutput = taskList.listTasks();
        assertTrue(listOutput.contains("1. [T][ ] Buy groceries"));
    }

    @Test
    public void handleCommand_invalidMarkCommand_throwsJarException() {
        Exception exception = assertThrows(JarException.class, () -> {
            parser.handleCommand("mark 1", taskList, ui);
        });

        String expectedMessage = "Invalid task number!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
