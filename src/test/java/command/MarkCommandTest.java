package command;

import java.io.File;

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
        File file = new File("data/d_plus_plus_e.txt");
        if (file.exists()) {
            file.delete();
        }
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

        File file = new File("data/d_plus_plus_e.txt");
        if (file.exists()) {
            file.delete();
        }
        System.err.println("Failed to throw exception when given invalid index");
        fail();
    }

}
