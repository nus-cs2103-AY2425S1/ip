package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dude.exception.DudeDuplicatedTaskException;

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
        Task newTask = new Event("task 3", LocalDateTime.of(2024, 9, 1, 9, 0),
                LocalDateTime.of(2024, 9, 1, 10, 0));
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

        Task newTask = new Event("task 3", LocalDateTime.of(2024, 9, 1, 9, 0),
                LocalDateTime.of(2024, 9, 1, 10, 0));
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

    @Test
    public void testFindAllTask_exactMatch() {
        ArrayList<Task> result = taskList.findAllTask("task 1");

        assertEquals(1, result.size());
        assertEquals(task1, result.get(0));
    }

    @Test
    public void testFindAllTask_partialMatch() {
        ArrayList<Task> result = taskList.findAllTask("ask");

        assertEquals(2, result.size());
        assertTrue(result.contains(task1));
        assertTrue(result.contains(task2));
    }

    @Test
    public void testFindAllTask_noMatch() {
        ArrayList<Task> result = taskList.findAllTask("dummy");

        assertTrue(result.isEmpty());
    }
}
