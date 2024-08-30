package dude.task;

import dude.exception.DudeDuplicatedTaskException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        task1 = new ToDo("task 1");
        task2 = new Deadline("task 2", LocalDateTime.of(2024, 8, 30, 10, 50));

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        taskList = new TaskList(tasks);
    }

    @Test
    public void testAddTask_success() {
        Task newTask = new Event("task 3", LocalDateTime.of(2024, 9, 1, 9, 0)
                , LocalDateTime.of(2024, 9, 1, 10, 0));
        try {
            taskList.addTask(newTask);
        } catch (DudeDuplicatedTaskException e) {
            //do nothing
        }

        assertEquals(3, taskList.getLength());
        assertEquals(newTask, taskList.getTasks().get(2));
    }

    @Test
    public void testAddTask_duplicate_throwsDudeDuplicatedTaskException() {
        DudeDuplicatedTaskException exception = assertThrows(DudeDuplicatedTaskException.class, () -> {
            taskList.addTask(task1);
        });

        assertEquals("Oops!! This task already exist.", exception.getMessage());
    }

    @Test
    public void testDeleteTask_validIndex() {
        Task deletedTask = taskList.deleteTask(1);

        assertEquals(task1, deletedTask);
        assertEquals(1, taskList.getLength());
        assertFalse(taskList.getTasks().contains(task1));
    }

    @Test
    public void testMarkTask_validIndex() {
        Task markedTask = taskList.markTask(2);

        assertEquals("X", markedTask.getStatusIcon());
        assertEquals("[D][X] task 2 (by: Aug 30 2024 10:50)", markedTask.toString());
    }

    @Test
    public void testUnmarkTask_validIndex() {
        Task markedTask = taskList.markTask(1);
        Task unmarkedTask = taskList.unmarkTask(1);

        assertEquals(" ", unmarkedTask.getStatusIcon());
        assertEquals("[T][ ] task 1", unmarkedTask.toString());
    }

    @Test
    public void testGetLength() {
        assertEquals(2, taskList.getLength());

        Task newTask = new Event("task 3", LocalDateTime.of(2024, 9, 1, 9, 0)
                , LocalDateTime.of(2024, 9, 1, 10, 0));
        try {
            taskList.addTask(newTask);
        } catch (DudeDuplicatedTaskException e) {
            //do nothing
        }

        assertEquals(3, taskList.getLength());
    }

    @Test
    public void testGetTasks() {
        ArrayList<Task> tasks = taskList.getTasks();

        assertEquals(2, tasks.size());
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
    }
}
