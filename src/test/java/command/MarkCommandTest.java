package command;

import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MarkCommandTest {
    @Test
    void execute_validIndex_taskMarkedAsDone() {
        TaskList taskList = new TaskList();
        taskList.add(new Task.Todo("A todo task"));
        Task task = new Task.Deadline("Description for deadline", "tomorrow");
        taskList.add(task);
        MarkCommand markCommand = new MarkCommand(1, taskList);
        markCommand.execute();

        assertEquals(taskList.get(0).isDone(), true);
    }

    @Test
    void execute_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        taskList.add(new Task.Todo("A todo task"));
        MarkCommand markCommand = new MarkCommand(2, taskList);

        try {
            markCommand.execute();
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        System.err.println("Failed to throw exception when given invalid index");
        fail();
    }

}
