package vuewee.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import vuewee.TaskListUI;
import vuewee.parser.CommandParser;
import vuewee.parser.IllegalCommandException;
import vuewee.task.TaskList;

public class AddCommandTest {
    @Test
    public void testTodoTask() {
        TaskList taskList = new TaskList();
        TaskListUI taskListUI = new TaskListUI(taskList);

        TodoCommand command = new TodoCommand();
        assertThrows(IllegalCommandException.class,
                () -> command.execute(taskListUI, taskList, new CommandParser("todo")));

        command.execute(taskListUI, taskList, new CommandParser("todo task"));
        assertEquals(taskList.size(), 1);
        assertEquals(taskList.serialize(), "T | 0 | task\n");
    }

    @Test
    public void testDeadlineTask() {
        TaskList taskList = new TaskList();
        TaskListUI taskListUI = new TaskListUI(taskList);

        DeadlineCommand command = new DeadlineCommand();
        // Test missing params
        assertThrows(IllegalCommandException.class,
                () -> command.execute(taskListUI, taskList, new CommandParser("deadline")));
        // Test missing by date
        assertThrows(IllegalCommandException.class,
                () -> command.execute(taskListUI, taskList, new CommandParser("deadline desc /by")));
        // Test invalid date (yyyy-mm-dd)
        assertThrows(IllegalCommandException.class,
                () -> command.execute(taskListUI, taskList, new CommandParser("deadline a /by 2021-13-01")));
        assertThrows(IllegalCommandException.class,
                () -> command.execute(taskListUI, taskList, new CommandParser("deadline a /by 2021-01-32")));

        command.execute(taskListUI, taskList, new CommandParser("deadline a /by 2020-08-01"));
        assertEquals(taskList.size(), 1);
        assertEquals(taskList.serialize(), "D | 0 | a | 2020-08-01\n");
    }

    @Test
    public void testEventTask() {
        TaskList taskList = new TaskList();
        TaskListUI taskListUI = new TaskListUI(taskList);

        EventCommand command = new EventCommand();
        // Test missing params
        assertThrows(IllegalCommandException.class,
                () -> command.execute(taskListUI, taskList, new CommandParser("event")));
        // Test missing from and to
        assertThrows(IllegalCommandException.class,
                () -> command.execute(taskListUI, taskList, new CommandParser("event desc /from 2020-08-01")));
        assertThrows(IllegalCommandException.class,
                () -> command.execute(taskListUI, taskList, new CommandParser("event desc /to 2020-08-01")));
        // Test invalid date (yyyy-mm-dd)
        assertThrows(IllegalCommandException.class, () -> command.execute(taskListUI, taskList,
                new CommandParser("event a /from 2021-13-01 /to 2020-08-01")));
        assertThrows(IllegalCommandException.class, () -> command.execute(taskListUI, taskList,
                new CommandParser("event a /to 2021-01-32 /from 2020-08-01")));

        // Test 2 valid events with inverted from and to
        command.execute(taskListUI, taskList, new CommandParser("event a /from 2020-08-01 /to 2020-09-01"));
        command.execute(taskListUI, taskList, new CommandParser("event b /to 2020-09-01 /from 2020-08-01"));
        assertEquals(taskList.size(), 2);
        assertEquals(taskList.serialize(),
                "E | 0 | a | 2020-08-01 | 2020-09-01\nE | 0 | b | 2020-08-01 | 2020-09-01\n");
    }
}