package vuewee.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import vuewee.parser.CommandParser;
import vuewee.parser.IllegalCommandException;
import vuewee.task.TaskList;
import vuewee.task.TodoTask;
import vuewee.ui.TaskListCli;

public class FindCommandTest {
    @Test
    public void testFindCommand() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("task1"));
        taskList.add(new TodoTask("task2"));
        taskList.add(new TodoTask("task3"));
        TaskListCli taskListUI = TaskListCli.getTaskListInstance(taskList);

        FindCommand command = new FindCommand();

        assertThrows(IllegalCommandException.class, () -> command.executeCommand(taskListUI, taskList,
                new CommandParser("find xxx")));

        // Can only test successful execution
        command.executeCommand(taskListUI, taskList, new CommandParser("find task2"));

        taskListUI.close();
    }
}
