package tars.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tars.tasks.Task;
import tars.tasks.TaskList;
import tars.tasks.ToDo;


import java.util.List;
import java.util.ArrayList;

public class DeleteCommandTest {

    @Test
    public void deleteTasks() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            taskList.add(new ToDo(String.format("todo %d", i+1)));
        }

        TaskList tasks = new TaskList(taskList);
        DeleteCommand deleteCommand = new DeleteCommand();


        String result = deleteCommand.execute("delete 5", tasks);
        String expected = String.format("""
                Wow you're freeing yourself up
                   %s
                You now have %s tasks left""",
                new ToDo("todo 5"), tasks.noOfTasks());

        assertEquals(9, tasks.noOfTasks());
        assertEquals(expected, result);


    }

}
