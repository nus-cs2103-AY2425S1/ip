package nextgpt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import nextgpt.task.Task;
import nextgpt.task.Todo;
import nextgpt.task.Event;
import nextgpt.task.Deadline;

import java.util.ArrayList;
import java.util.Scanner;
public class TaskListTest {
    @Test
    public void taskList_initialiseNewList_success() {
        Task task1 = new Todo("do assignment");
        Task task2 = new Deadline("submit research", "2024-08-01");
        Task task3 = new Event("holiday", "2024-08-04", "2024-08-10");
        task2.mark();
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(task1);
        expected.add(task2);
        expected.add(task3);

        TaskList result = new TaskList(new Scanner("T, ,do assignment\n" +
                "D,X,submit research,2024-08-01\n" +
                "E, ,holiday,2024-08-04,2024-08-10"));
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void validAddTask() {
        TaskList result = new TaskList(new Scanner(""));
        assertEquals(0, result.size());

        result.add(new Todo("do assignment"));
        assertEquals(1, result.size());

        result.add(new Deadline("finish homework", "2024-08-01"));
        assertEquals(2,result.size());

    }

}
