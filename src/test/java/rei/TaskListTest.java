package rei;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getNumOfTasks_newTaskList_0() {
        TaskList listOfTasks = new TaskList(new ArrayList<Task>());
        assertEquals(0, listOfTasks.getNumOfTasks());
    }

    @Test
    public void getTaskTest() {
        TaskList listOfTasks = new TaskList(new ArrayList<Task>());
        listOfTasks.addTask(Task.createToDo("task"));
        listOfTasks.addTask(Task.createDeadline("task2", LocalDateTime.of(2024, Month.SEPTEMBER, 12, 14, 00)));
        listOfTasks.addTask(Task.createEvent("task3",
                LocalDateTime.of(2024, Month.AUGUST, 17, 8, 00),
                LocalDateTime.of(2024, Month.AUGUST, 17, 22, 00)));
        assertEquals("[T][ ] task",
                listOfTasks.getTask(0).toString());
        assertEquals("[D][ ] task2 (by: Thu, Sep 12 2024 14:00:00)",
                listOfTasks.getTask(1).toString());
        assertEquals("[E][ ] task3 (from: Sat, Aug 17 2024 08:00:00 to: Sat, Aug 17 2024 22:00:00)",
                listOfTasks.getTask(2).toString());

        try {
            listOfTasks.getTask(3);
        } catch (IndexOutOfBoundsException e) {
            // do nothing
        }
    }








}
