package rizzler.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rizzler.Storage;
import rizzler.task.Deadline;
import rizzler.task.Event;
import rizzler.task.Task;
import rizzler.task.TaskLog;
import rizzler.task.ToDo;

public class ListCommandTest {
    private static final Storage testStorage = new Storage();
    private static final TaskLog testTaskLog= new TaskLog();

    @Test
    public void execute_emptyTasklog_emptyTasklogResponse() {
        ListCommand myListCommand = new ListCommand();

        assertEquals(1, myListCommand.execute(testStorage, testTaskLog).length);

        assertEquals(ListCommand.EMPTY_LIST_RESPONSE, myListCommand.execute(testStorage, testTaskLog)[0]);
    }

    @Test
    public void execute_oneElementTasklog_appropriateTasklogResponse() {
        ListCommand myListCommand = new ListCommand();
        Task myTodo = new ToDo("this is a placeholder");
        testTaskLog.addTask(myTodo);

        assertEquals(2, myListCommand.execute(testStorage, testTaskLog).length);

        assertEquals(ListCommand.TASK_LIST_HEADER, myListCommand.execute(testStorage, testTaskLog)[0]);

        assertEquals("1. " + myTodo, myListCommand.execute(testStorage, testTaskLog)[1]);

        testTaskLog.deleteTask(1);
    }

    @Test
    public void execute_multipleElementTasklog_appropriateTasklogResponse() {
        ListCommand myListCommand = new ListCommand();
        Task todo1 = new ToDo("first todo");
        Task todo2 = new ToDo("second todo");
        Task event1 = new Event("event1", "tomorrow", "the day after");
        Task deadline1 = new Deadline("deadline", "2020-01-01");
        Task event2 = new Event("event2", "now", "later");

        testTaskLog.addTask(todo1);
        testTaskLog.addTask(todo2);
        testTaskLog.addTask(event1);
        testTaskLog.addTask(deadline1);
        testTaskLog.addTask(event2);

        assertEquals(6, myListCommand.execute(testStorage, testTaskLog).length);

        assertEquals(ListCommand.TASK_LIST_HEADER, myListCommand.execute(testStorage, testTaskLog)[0]);

        assertEquals("1. " + todo1, myListCommand.execute(testStorage, testTaskLog)[1]);
        assertEquals("2. " + todo2, myListCommand.execute(testStorage, testTaskLog)[2]);
        assertEquals("3. " + event1, myListCommand.execute(testStorage, testTaskLog)[3]);
        assertEquals("4. " + deadline1, myListCommand.execute(testStorage, testTaskLog)[4]);
        assertEquals("5. " + event2, myListCommand.execute(testStorage, testTaskLog)[5]);

        testTaskLog.deleteTask(1);
        testTaskLog.deleteTask(1);
        testTaskLog.deleteTask(1);
        testTaskLog.deleteTask(1);
        testTaskLog.deleteTask(1);
    }
}
