package chatsy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chatsy.commands.Command;
import chatsy.exceptions.ChatsyException;
import chatsy.parser.Parser;
import chatsy.tasks.DeadlineTask;
import chatsy.tasks.EventTask;
import chatsy.tasks.Task;
import chatsy.tasks.TodoTask;


class CommandHandlerTest {
    private TaskManager taskManager;
    private Parser parser;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
        parser = new Parser(taskManager);
    }

    @Test
    void testAddTodoCommand() throws ChatsyException {
        Command command = parser.parse("todo Read book");
        command.execute(taskManager);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Read book", tasks.get(0).getDescription());
        assertTrue(tasks.get(0) instanceof TodoTask);
        assertFalse(tasks.get(0).isDone());
    }

    @Test
    void testAddDeadlineCommand() throws ChatsyException {
        Command command = parser.parse("deadline Finish project /by 2024-09-30");
        command.execute(taskManager);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Finish project", tasks.get(0).getDescription());
        assertTrue(tasks.get(0) instanceof DeadlineTask);
        DeadlineTask deadlineTask = (DeadlineTask) tasks.get(0);
        assertEquals(LocalDate.of(2024, 9, 30), deadlineTask.getBy());
    }

    @Test
    void testAddEventCommand() throws ChatsyException {
        Command command = parser.parse("event Attend conference /from 2024-09-28 10:00 /to 2024-09-28 18:00");
        command.execute(taskManager);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Attend conference", tasks.get(0).getDescription());
        assertTrue(tasks.get(0) instanceof EventTask);
    }

    @Test
    void testMarkTaskCommand() throws ChatsyException {
        Command addTodoCommand = parser.parse("todo Read book");
        addTodoCommand.execute(taskManager);

        Command markCommand = parser.parse("mark 1");
        markCommand.execute(taskManager);

        List<Task> tasks = taskManager.getTasks();
        assertTrue(tasks.get(0).isDone());
    }

    @Test
    void testUnmarkTaskCommand() throws ChatsyException {
        Command addTodoCommand = parser.parse("todo Read book");
        addTodoCommand.execute(taskManager);

        Command markCommand = parser.parse("mark 1");
        markCommand.execute(taskManager);

        Command unmarkCommand = parser.parse("unmark 1");
        unmarkCommand.execute(taskManager);

        List<Task> tasks = taskManager.getTasks();
        assertFalse(tasks.get(0).isDone());
    }

    @Test
    void testDeleteTaskCommand() throws ChatsyException {
        Command addTodoCommand = parser.parse("todo Read book");
        addTodoCommand.execute(taskManager);

        Command deleteCommand = parser.parse("delete 1");
        deleteCommand.execute(taskManager);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(0, tasks.size());
    }
}
