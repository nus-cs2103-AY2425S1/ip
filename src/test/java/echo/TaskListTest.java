package echo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

public class TaskListTest {
    @Test
    public void test_list_delete() {

        Task t1 = new Deadline("write essay", "2023/03/23 23:59");
        Task t2 = new Event("read book", "2pm Sunday", "2pm Monday");
        Task t3 = new ToDo("programming");
        ArrayList<Task> list = new ArrayList<>();
        list.add(t1);
        list.add(t3);

        TaskList allTask = new TaskList();
        allTask.add(t1);
        allTask.add(t2);
        allTask.add(t3);
        allTask.delete(1);

        assertEquals(list.toString(), allTask.toString());

    }
}
