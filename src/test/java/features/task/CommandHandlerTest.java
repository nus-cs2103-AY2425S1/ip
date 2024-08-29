package features.task;

import static org.junit.jupiter.api.Assertions.*;

import config.Config;
import features.command.CommandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import features.command.Command;

import java.io.ByteArrayOutputStream;

public class CommandHandlerTest {

    private CommandHandler commandHandler;
    private TaskManagementStub taskManagement;
    @BeforeEach
    public void setUp() {
        taskManagement = new TaskManagementStub();
        commandHandler = new CommandHandler(taskManagement);
    }

    @Test
    public void testHandleAddTodoTask() throws Exception {
        Command command = new Command("todo Read a book");
        commandHandler.handleCommand(command);
        assertEquals(1, taskManagement.length);
        assertEquals("[T][ ] Read a book", taskManagement.getAllTasks().get(0).toString());
    }

    @Test
    public void testHandleMarkTask() throws Exception {
        Task task = new TestTask("Complete homework");
        taskManagement.add(task);
        int taskId = task.getId();
        Command addCommand = new Command("mark " + taskId);
        commandHandler.handleCommand(addCommand);
        assertTrue(task.getIsMarked());
    }

    @Test
    public void testHandleDeleteTask() throws Exception {
        Task task = new TestTask("Attend meeting");
        taskManagement.add(task);
        int taskId = task.getId();
        Command deleteCommand = new Command("delete " + taskId);
        commandHandler.handleCommand(deleteCommand);
        assertEquals(0, taskManagement.length);
        assertTrue(taskManagement.getAllTasks().isEmpty());
    }
}
