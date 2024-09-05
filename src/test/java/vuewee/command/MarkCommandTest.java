package vuewee.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import vuewee.TaskListUI;
import vuewee.parser.CommandParser;
import vuewee.parser.IllegalCommandException;
import vuewee.task.TaskList;
import vuewee.task.TodoTask;

public class MarkCommandTest {
    @Test
    public void testMarkUnmark() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("task1"));
        taskList.add(new TodoTask("task2"));
        taskList.add(new TodoTask("task3"));
        TaskListUI taskListUI = new TaskListUI(taskList);

        MarkCommand markCommand = new MarkCommand();
        UnmarkCommand unmarkCommand = new UnmarkCommand();

        // Test edge case marking (1-index)
        assertThrows(IllegalCommandException.class, () -> markCommand.execute(taskListUI, taskList, new CommandParser(
                "mark 0")));
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 0 | task3\n", taskList.serialize());
        assertThrows(IllegalCommandException.class, () -> markCommand.execute(taskListUI, taskList, new CommandParser(
                "mark 4")));
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 0 | task3\n", taskList.serialize());

        // Test edge case unmarking (1-index)
        assertThrows(IllegalCommandException.class, () -> unmarkCommand.execute(taskListUI, taskList, new CommandParser(
                "unmark 0")));
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 0 | task3\n", taskList.serialize());
        assertThrows(IllegalCommandException.class, () -> unmarkCommand.execute(taskListUI, taskList, new CommandParser(
                "unmark 4")));
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 0 | task3\n", taskList.serialize());

        // Test already unmarked
        assertThrows(IllegalCommandException.class, () -> unmarkCommand.execute(taskListUI, taskList, new CommandParser(
                "unmark 3")));
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 0 | task3\n", taskList.serialize());

        // Test mark
        markCommand.execute(taskListUI, taskList, new CommandParser("mark 3"));
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 1 | task3\n", taskList.serialize());

        // Test already marked
        assertThrows(IllegalCommandException.class, () -> markCommand.execute(taskListUI, taskList, new CommandParser(
                "mark 3")));

        // Test unmark
        unmarkCommand.execute(taskListUI, taskList, new CommandParser("unmark 3"));
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 0 | task3\n", taskList.serialize());

    }
}
