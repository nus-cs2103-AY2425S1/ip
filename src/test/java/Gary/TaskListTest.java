package Gary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import Gary.task.Task;
import Gary.task.ToDo;
import Gary.task.Event;
import Gary.task.Deadline;

import java.util.ArrayList;
import java.util.Scanner;


public class TaskListTest {
    @Test
    public void taskListInitTest() {
        Task task1 = new ToDo("buy bread");
        Task task2 = new Deadline("finish quiz", "2024-08-23");
        Task task3 = new Event("competition", "2024-08-24 0800", "2024-08-24 1300");
        task2.editStatus();
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(task1);
        expectedTaskList.add(task2);
        expectedTaskList.add(task3);

        TaskList result = new TaskList(new Scanner("T | 0 | buy bread\n" +
                "D | 1 | finish quiz | 2024-08-23\n" +
                "E | 0 | competition | 2024-08-24 0800 | 2024-08-24 1300"));
        assertEquals(expectedTaskList.toString(), result.toString());
    }

    @Test
    public void taskListAddTest() {
        TaskList result = new TaskList(new Scanner(""));
        assertEquals(0, result.size());

        result.addTask(new ToDo("buy bread"));
        assertEquals(1, result.size());

        result.addTask(new Deadline("finish quiz", "2024-08-23"));
        assertEquals(2,result.size());
    }
}
