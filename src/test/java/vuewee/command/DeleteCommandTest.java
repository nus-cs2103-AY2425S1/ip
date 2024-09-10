package vuewee.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.task.TodoTask;
import vuewee.ui.TaskListCli;

public class DeleteCommandTest {
    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("task1"));
        taskList.add(new TodoTask("task2"));
        taskList.add(new TodoTask("task3"));
        TaskListCli taskListUI = TaskListCli.getTaskListInstance(taskList);

        DeleteCommand command = new DeleteCommand();

        // Test edge case deletion (1-index)
        assertThrows(IndexOutOfBoundsException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("delete 0")));
        assertEquals(3, taskList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("delete 4")));
        assertEquals(3, taskList.size());

        command.executeCommand(taskListUI, taskList, new CommandParser("delete 3"));
        assertEquals(2, taskList.size());
        assertEquals("T | 0 | task1\nT | 0 | task2\n", taskList.serialize());

        command.executeCommand(taskListUI, taskList, new CommandParser("delete 1"));
        assertEquals(1, taskList.size());
        assertEquals("T | 0 | task2\n", taskList.serialize());

        taskListUI.close();
    }
}
