package nayana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nayana.task.Deadline;
import nayana.task.Task;
import nayana.task.ToDos;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        Task task = new ToDos("Sample task");
        taskList.addTask(task);
        assertEquals(1, taskList.getSize(), "TaskList should contain one task after adding.");
        assertEquals(task, taskList.getTasks().get(0), "The added task should match the retrieved task.");
    }

    @Test
    void testDeleteTask() throws NayanaException {
        Task task = new ToDos("Sample task");
        taskList.addTask(task);
        Task deletedTask = taskList.delete(0);
        assertEquals(task, deletedTask, "The deleted task should match the original task.");
        assertEquals(0, taskList.getSize(), "TaskList should be empty after deleting the task.");
    }

    @Test
    void testDeleteTaskInvalidIndex() {
        assertThrows(NayanaException.class, () -> taskList.delete(0),
              "Deleting a task with an invalid index should throw NayanaException.");
    }

    @Test
    void testMarkAsDone() throws NayanaException {
        Task task = new ToDos("Sample task");
        taskList.addTask(task);
        Task updatedTask = taskList.markAsDone(0);
        assertTrue(updatedTask.getDone(), "The task should be marked as done.");
    }

    @Test
    void testMarkAsDoneInvalidIndex() {
        assertThrows(NayanaException.class, () -> taskList.markAsDone(0),
              "Marking a task with an invalid index should throw NayanaException.");
    }

    @Test
    void testMarkAsNotDone() throws NayanaException {
        Task task = new ToDos("Sample task");
        task.markAsDone(); // First mark it as done
        taskList.addTask(task);
        Task updatedTask = taskList.markAsNotDone(0);
        assertFalse(updatedTask.getDone(), "The task should be marked as not done.");
    }

    @Test
    void testMarkAsNotDoneInvalidIndex() {
        assertThrows(NayanaException.class, () -> taskList.markAsNotDone(0),
              "Marking a task with an invalid index should throw NayanaException.");
    }

    @Test
    void testToString() {
        Task task1 = new ToDos("Task 1");
        Task task2 = new Deadline("Task 2", LocalDate.now());
        taskList.addTask(task1);
        taskList.addTask(task2);
        String expectedOutput = "Here are the tasks in your list:\n1. " + task1 + "\n2. " + task2 + "\n";
        assertEquals(expectedOutput, taskList.toString(),
              "The string representation of the task list should match the expected output.");
    }
}
