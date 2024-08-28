package chatsy;

import chatsy.CommandHandler;
import chatsy.TaskManager;
import chatsy.UI;
import chatsy.exceptions.ChatsyException;
import chatsy.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {
    private TaskManager taskManager;
    private UI ui;
    private CommandHandler commandHandler;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
        ui = new UI();
        commandHandler = new CommandHandler(taskManager, ui);
    }

    @Test
    void testAddTodoCommand() throws ChatsyException {
        commandHandler.handle("todo Read book");
        List<Task> tasks = taskManager.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("Read book", tasks.get(0).getDescription());
    }

    @Test
    void testAddDeadlineCommand() throws ChatsyException {
        commandHandler.handle("deadline Finish project /by 2024-09-30");
        List<Task> tasks = taskManager.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("Finish project", tasks.get(0).getDescription());
    }
}
