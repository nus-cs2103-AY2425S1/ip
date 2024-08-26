package friendlybot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new ToDo("test 1"));
        taskList.addTask(new ToDo("test 2"));
        taskList.addTask(new ToDo("test 3"));
        assertEquals(3, taskList.getNumTasks());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new ToDo("test 1"));
        taskList.addTask(new ToDo("test 2"));
        taskList.addTask(new ToDo("test 3"));
        taskList.deleteTask(2);
        assertEquals(2, taskList.getNumTasks());
        assertEquals("test 1", taskList.getTask(1).description);
        assertEquals("test 3", taskList.getTask(2).description);
    }

    @Test
    public void testGetTasksOnDate() {
        LocalDate date1 = LocalDate.of(2024,8,26);
        LocalDate date2 = LocalDate.of(2024, 1, 1);
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new ToDo("test 1"));
        taskList.addTask(new Deadline("test 2", date2));
        taskList.addTask(new Event("test 3", date1, date1));
        taskList.addTask(new Event("test 4", date1, date2));
        assertEquals(taskList.getTasksOnDate(date1).size(), 2);
    }
}
