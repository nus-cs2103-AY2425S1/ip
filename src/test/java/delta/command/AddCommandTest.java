package delta.command;

import delta.task.Task;
import delta.task.Todo;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    @Test
    public void testExecute() {
        Todo todo1 = new Todo("test");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todo1);

        TaskList testList = new TaskList();
        Todo todo2 = new Todo("test");
        AddCommand testCommand = new AddCommand(todo2);

        try {
            testCommand.execute(testList, new Ui(), new Storage("./data/tasks.txt"));

            assertEquals(tasks.toString(), testList.getTasks().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
