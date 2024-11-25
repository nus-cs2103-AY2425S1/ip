package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.EchoException;


public class TaskListTest {
    @Test
    public void test_list_delete() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList allTask = new TaskList();
        try {
            Task t1 = new Deadline("write essay", "2023/03/23 23:59");
            Task t2 = new Event("read book", "2023/03/02 08:00", "2023/03/02 20:00");
            Task t3 = new ToDo("programming");

            list.add(t1);
            list.add(t3);

            allTask.add(t1);
            allTask.add(t2);
            allTask.add(t3);
            allTask.delete(1);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(list.toString(), allTask.toString());
    }
}
