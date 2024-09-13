package chatsy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import chatsy.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chatsy.exceptions.ChatsyException;
import chatsy.tasks.DeadlineTask;
import chatsy.tasks.EventTask;
import chatsy.tasks.Task;
import chatsy.tasks.TodoTask;

class CommandHandlerTest {
    private TaskManager taskManager;
    private Parser commandHandler;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
        commandHandler = new Parser(taskManager);
    }

    @Test
    void testAddTodoCommand() throws ChatsyException {
        commandHandler.handle("todo Read book");
        List<Task> tasks = taskManager.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("Read book", tasks.get(0).getDescription());
        assertTrue(tasks.get(0) instanceof TodoTask);
        assertFalse(tasks.get(0).isDone());
    }

    @Test
    void testAddDeadlineCommand() throws ChatsyException {
        commandHandler.handle("deadline Finish project /by 2024-09-30");
        List<Task> tasks = taskManager.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("Finish project", tasks.get(0).getDescription());
        assertTrue(tasks.get(0) instanceof DeadlineTask);
        DeadlineTask deadlineTask = (DeadlineTask) tasks.get(0);
        assertEquals(LocalDate.of(2024, 9, 30), deadlineTask.getBy());
    }

    @Test
    void testAddEventCommand() throws ChatsyException {
        commandHandler.handle("event Attend conference /from 2024-09-28 10:00 /to 2024-09-28 18:00");
        List<Task> tasks = taskManager.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("Attend conference", tasks.get(0).getDescription());
        assertTrue(tasks.get(0) instanceof EventTask);
    }

    @Test
    void testMarkTaskCommand() throws ChatsyException {
        commandHandler.handle("todo Read book");
        commandHandler.handle("mark 1");
        List<Task> tasks = taskManager.getTasks();

        assertTrue(tasks.get(0).isDone());
    }

    @Test
    void testUnmarkTaskCommand() throws ChatsyException {
        commandHandler.handle("todo Read book");
        commandHandler.handle("mark 1");
        commandHandler.handle("unmark 1");
        List<Task> tasks = taskManager.getTasks();

        assertFalse(tasks.get(0).isDone());
    }

    @Test
    void testDeleteTaskCommand() throws ChatsyException {
        commandHandler.handle("todo Read book");
        commandHandler.handle("delete 1");
        List<Task> tasks = taskManager.getTasks();

        assertEquals(0, tasks.size());
    }

}
