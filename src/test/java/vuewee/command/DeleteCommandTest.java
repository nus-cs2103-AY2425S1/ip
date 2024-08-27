package vuewee.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import vuewee.TaskListUI;
import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.task.TodoTask;

public class DeleteCommandTest {
  @Test
  public void testDeleteTask() {
    TaskList taskList = new TaskList();
    taskList.add(new TodoTask("task1"));
    taskList.add(new TodoTask("task2"));
    taskList.add(new TodoTask("task3"));
    TaskListUI taskListUI = new TaskListUI(taskList);

    DeleteCommand command = new DeleteCommand();

    // Test edge case deletion (1-index)
    assertThrows(IndexOutOfBoundsException.class,
        () -> command.execute(taskListUI, taskList, new CommandParser("delete 0")));
    assertEquals(taskList.size(), 3);
    assertThrows(IndexOutOfBoundsException.class,
        () -> command.execute(taskListUI, taskList, new CommandParser("delete 4")));
    assertEquals(taskList.size(), 3);

    command.execute(taskListUI, taskList, new CommandParser("delete 3"));
    assertEquals(taskList.size(), 2);
    assertEquals(taskList.serialize(), "T | 0 | task1\nT | 0 | task2\n");

    command.execute(taskListUI, taskList, new CommandParser("delete 1"));
    assertEquals(taskList.size(), 1);
    assertEquals(taskList.serialize(), "T | 0 | task2\n");

  }
}