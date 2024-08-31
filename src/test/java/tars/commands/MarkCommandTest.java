package tars.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tars.tasks.Task;
import tars.tasks.TaskList;
import tars.tasks.ToDo;

import java.util.List;
import java.util.ArrayList;

public class MarkCommandTest {

    @Test
    public void markTasks() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            taskList.add(new ToDo(String.format("todo %d", i+1)));
        }

        TaskList tasks = new TaskList(taskList);
        MarkCommand markCommand = new MarkCommand();


        String result = markCommand.execute("mark 5", tasks);

        Task t = new ToDo("todo 5");
        t.markDone();
        String expected = "Task complete. If I had arms, I might give you a pat on the back.\n" + t;

        assertEquals(10, tasks.noOfTasks());
        assertEquals(expected, result);
    }

    @Test
    public void unMarkTasks() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            taskList.add(new ToDo(String.format("todo %d", i+1)));
        }

        TaskList tasks = new TaskList(taskList);
        MarkCommand markCommand = new MarkCommand();


        String result = markCommand.execute("mark 5", tasks);

        Task t = new ToDo("todo 5");
        t.markDone();
        String expected = "Task complete. If I had arms, I might give you a pat on the back.\n" + t;

        assertEquals(10, tasks.noOfTasks());
        assertEquals(expected, result);

        result = markCommand.execute("unmark 5", tasks);

        t.markUndone();
        expected = "Task undone. No worries, I won't judge... much.\n" + t;
        assertEquals(expected, result);

    }
}
