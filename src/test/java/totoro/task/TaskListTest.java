package totoro.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList taskList;
    private Deadline deadline;
    private Event event;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        deadline = new Deadline("Submit assignment",
                LocalDateTime.of(2024, 9, 30, 23, 59));
        event = new Event("Team meeting",
                LocalDateTime.of(2024, 9, 30, 10, 0),
                LocalDateTime.of(2024, 9, 30, 11, 0));
        taskList.addTask(deadline);
        taskList.addTask(event);
    }

    @Test
    public void testAddTask() {
        Task newTask = new Deadline("Read book",
                LocalDateTime.of(2024, 10, 1, 12, 0));
        taskList.addTask(newTask);
        assertEquals(3, taskList.size());
        assertEquals(newTask, taskList.get(2));
    }

    @Test
    public void testRemoveTask() {
        Task removedTask = taskList.removeTask(0);
        assertEquals(deadline, removedTask);
        assertEquals(1, taskList.size());
    }

    @Test
    public void testGetAllTasks() {
        ArrayList<Task> tasks = taskList.getAllTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(deadline));
        assertTrue(tasks.contains(event));
    }

    @Test
    public void testSearchTasks() {
        TaskList searchResult = taskList.searchTasks("meeting");
        assertEquals(1, searchResult.size());
        assertTrue(searchResult.getAllTasks().contains(event));
    }

    @Test
    public void testGetTaskForDate() {
        LocalDate date = LocalDate.of(2024, 9, 30);
        List<Task> tasksForDate = taskList.getTaskForDate(date);
        assertEquals(2, tasksForDate.size());
        assertTrue(tasksForDate.contains(deadline));
        assertTrue(tasksForDate.contains(event));
    }

    @Test
    public void testGetTaskForDate_noTasksOnDate() {
        LocalDate date = LocalDate.of(2024, 10, 1);
        List<Task> tasksForDate = taskList.getTaskForDate(date);
        assertEquals(0, tasksForDate.size());
    }
}
