package stobberi.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stobberi.stobberiexception.InvalidNumberStobberiException;
import stobberi.stobberiexception.NoSuchTaskStobberiException;
import stobberi.stobberiexception.StobberiException;
import stobberi.task.Deadline;
import stobberi.task.Event;
import stobberi.task.Task;
import stobberi.task.Todo;

public class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;
    private Deadline deadline;
    private Event event;

    @BeforeEach
    public void setUp() throws StobberiException {
        taskList = new TaskList();
        task1 = new Todo("Task 1");
        task2 = new Todo("Task 2");
        deadline = new Deadline("Deadline Task", "18-09-2024 0900hrs");
        event = new Event("Event Task", "18-09-2024 0800hrs", "18-10-2024 1700hrs");
    }

    @Test
    public void testMarkTask_validNumber() throws StobberiException {
        taskList.addTask(task1);
        String result = taskList.markTask(1);
        assertEquals("Yay! I've marked this task as done:\n"
                + "  [T] [X] Task 1", result);
        assertTrue(task1.isDone());
    }

    @Test
    public void testMarkTask_invalidNumber() {
        taskList.addTask(task1);
        assertThrows(InvalidNumberStobberiException.class, () -> taskList.markTask(2));
    }

    @Test
    public void testUnmarkTask_validNumber() throws StobberiException {
        taskList.addTask(task1);
        taskList.markTask(1); // First mark as done
        String result = taskList.unmarkTask(1);
        assertEquals("OK, I've marked this task as not done:\n"
                + "  [T] [ ] Task 1", result);
        assertFalse(task1.isDone());
    }

    @Test
    public void testUnmarkTask_invalidNumber() {
        taskList.addTask(task1);
        assertThrows(InvalidNumberStobberiException.class, () -> taskList.unmarkTask(2));
    }

    @Test
    public void testDisplayList_noTasks() {
        assertThrows(NoSuchTaskStobberiException.class, () -> taskList.displayList());
    }

    @Test
    public void testDisplayList_withTasks() throws StobberiException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        String result = taskList.displayList();
        assertEquals("Here are the tasks in your list:\n"
                + "1. [T] [ ] Task 1\n"
                + "2. [T] [ ] Task 2", result);
    }

    @Test
    public void testDelete_validNumber() throws StobberiException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        String result = taskList.delete(1);
        assertEquals("Ookiiee! This task is now gone:\n"
                + "  [T] [ ] Task 1\n"
                + "Noww you have 1 tasks in the list.", result);
    }

    @Test
    public void testDelete_invalidNumber() {
        taskList.addTask(task1);
        assertThrows(InvalidNumberStobberiException.class, () -> taskList.delete(2));
    }

    @Test
    public void testDisplayLastAddedTask() {
        taskList.addTask(task1);
        String result = taskList.displayLastAddedTask();
        assertEquals("Yayyy! I've added a new task:\n"
                + "    [T] [ ] Task 1\n"
                + "Noww you have 1 in the list.", result);
    }

    @Test
    public void testFilterListByWord_noMatch() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertThrows(NoSuchTaskStobberiException.class, () -> taskList.filterListByWord("nonexistent"));
    }

    @Test
    public void testFilterListByWord_match() throws StobberiException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        String result = taskList.filterListByWord("Task");
        assertEquals("Here are the matching tasks in your list:\n"
                + "1. [T] [ ] Task 1\n"
                + "2. [T] [ ] Task 2", result);
    }

    @Test
    public void testFilterListByDate_noMatch() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertThrows(NoSuchTaskStobberiException.class, () -> taskList.filterListByDate("18-09-2024"));
    }

    @Test
    public void testFilterListByDate_match() throws StobberiException {
        taskList.addTask(deadline);
        taskList.addTask(event);
        String result = taskList.filterListByDate("18-09-2024");
        assertEquals("Here are the tasks on 18-09-2024:\n"
                + "1. [D] [ ] Deadline Task (by: 18 September 2024 9:00am)\n"
                + "2. [E] [ ] Event Task (from: 18 September 2024 8:00am to: 18 October 2024 5:00pm)", result);
    }

    @Test
    public void testAddTask() {
        String result = taskList.addTask(task1);
        assertEquals("Yayyy! I've added a new task:\n"
                + "    [T] [ ] Task 1\n"
                + "Noww you have 1 in the list.", result);
    }

    @Test
    public void testUpdateTaskList() {
        ArrayList<Task> newList = new ArrayList<>();
        newList.add(task1);
        taskList.updateTaskList(newList);
        assertEquals(newList, taskList.getListOfTasks());
    }

    @Test
    public void testHasTask_exists() {
        taskList.addTask(task1);
        assertTrue(taskList.hasTask("Task 1"));
    }

    @Test
    public void testHasTask_notExists() {
        taskList.addTask(task1);
        assertFalse(taskList.hasTask("Task 2"));
    }

    @Test
    public void testGetListOfTasks() {
        taskList.addTask(task1);
        ArrayList<Task> tasks = taskList.getListOfTasks();
        assertEquals(1, tasks.size());
        assertEquals(task1, tasks.get(0));
    }
}
