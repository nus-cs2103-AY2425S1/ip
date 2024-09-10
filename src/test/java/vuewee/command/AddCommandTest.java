package vuewee.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import vuewee.parser.CommandParser;
import vuewee.parser.IllegalCommandException;
import vuewee.task.TaskList;
import vuewee.ui.TaskListCli;

public class AddCommandTest {
    @Test
    public void testTodoTask() {
        TaskList taskList = new TaskList();
        TaskListCli taskListUI = TaskListCli.getTaskListInstance(taskList);

        System.out.println(taskList);

        TodoCommand command = new TodoCommand();
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("todo")));

        command.executeCommand(taskListUI, taskList, new CommandParser("todo task"));
        assertEquals(1, taskList.size());
        assertEquals("T | 0 | task\n", taskList.serialize());

        taskListUI.close();
    }

    @Test
    public void testDeadlineTask() {
        TaskList taskList = new TaskList();
        TaskListCli taskListUI = TaskListCli.getTaskListInstance(taskList);

        DeadlineCommand command = new DeadlineCommand();
        // Test missing params
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("deadline")));
        // Test missing by date
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("deadline desc /by")));
        // Test invalid date (yyyy-mm-dd)
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("deadline a /by 2021-13-01")));
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("deadline a /by 2021-01-32")));

        command.executeCommand(taskListUI, taskList, new CommandParser("deadline a /by 2020-08-01"));
        assertEquals(1, taskList.size());
        assertEquals("D | 0 | a | 2020-08-01\n", taskList.serialize());

        taskListUI.close();
    }

    @Test
    public void testEventTask() {
        TaskList taskList = new TaskList();
        TaskListCli taskListUI = TaskListCli.getTaskListInstance(taskList);

        EventCommand command = new EventCommand();
        // Test missing params
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("event")));
        // Test missing from and to
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("event desc /from 2020-08-01")));
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("event desc /to 2020-08-01")));
        // Test invalid date (yyyy-mm-dd)
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("event a /from 2021-13-01 /to 2020-08-01")));
        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("event a /to 2021-01-32 /from 2020-08-01")));

        // Test 2 valid events with inverted from and to
        command.executeCommand(taskListUI, taskList, new CommandParser("event a /from 2020-08-01 /to 2020-09-01"));
        command.executeCommand(taskListUI, taskList, new CommandParser("event b /to 2020-09-01 /from 2020-08-01"));
        assertEquals(2, taskList.size());
        assertEquals("E | 0 | a | 2020-08-01 | 2020-09-01\nE | 0 | b | 2020-08-01 | 2020-09-01\n", taskList
                .serialize());

        taskListUI.close();
    }
}
