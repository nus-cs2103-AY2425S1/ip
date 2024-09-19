package friday.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import friday.task.Deadline;
import friday.task.Event;
import friday.task.Task;
import friday.task.Todo;

/**
 * Tests the functionality of the TaskList class.
 * Verifies that tasks can be added, deleted, and that the TaskList behaves correctly with different indices.
 */
public class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up the test environment by creating a TaskList object
     * and adding some sample Todo tasks before each test is run.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Task 1"));
        taskList.addTask(new Todo("Task 2"));
    }

    /**
     * Tests the addition of a task with a valid index.
     * Verifies that the task is correctly added to the list and that the size of the list is updated.
     */
    @Test
    public void addTask_validIndex_success() {
        Task newTask = new Todo("Task 3");
        taskList.addTask(newTask);
        assertEquals(3, taskList.getSize());
        assertEquals("Task 3", taskList.getTask(2).getDescription());
    }

    /**
     * Tests the deletion of a task with a valid index.
     * Verifies that the task is correctly removed from the list and that the size of the list is updated.
     */
    @Test
    public void deleteTask_validIndex_success() {
        Task removedTask = taskList.deleteTask(0);
        assertNotNull(removedTask);
        assertEquals("Task 1", removedTask.getDescription());
        assertEquals(1, taskList.getSize());
    }

    /**
     * Tests the marking of a task as done with a valid index.
     * Verifies that the task is correctly marked as done and that the task's status is updated.
     */
    @Test
    public void markTaskAsDone_validIndex_success() {
        Task markedTask = taskList.markTaskAsDone(0);
        assertTrue(markedTask.isTaskDone());
    }

    /**
     * Tests the unmarking of a task with a valid index.
     * Verifies that the task is correctly unmarked and that the task's status is updated.
     */
    @Test
    public void unmarkTaskAsDone_validIndex_success() {
        taskList.markTaskAsDone(0);
        Task unmarkedTask = taskList.unmarkTaskAsDone(0);
        assertTrue(!unmarkedTask.isTaskDone());
    }

    /**
     * Tests the filtering of tasks based on a condition.
     * Verifies that the tasks are correctly filtered and that the filtered list is returned.
     */
    @Test
    public void filterTasks_condition_success() {
        Predicate<Task> condition = task -> task.getDescription().contains("1");
        TaskList filteredTasks = taskList.filterTasks(condition);
        assertEquals(1, filteredTasks.getSize());
        assertEquals("Task 1", filteredTasks.getTask(0).getDescription());
    }

    /**
     * Tests the finding of tasks by a specific date.
     * Verifies that the tasks are correctly found and that the list of tasks with the specified date is returned.
     */
    @Test
    public void findTasksByDate_validDate_success() {
        LocalDate date = LocalDate.of(2023, 10, 10);
        taskList.addTask(new Deadline("Deadline 1", "2023-10-10 2359"));
        TaskList tasksByDate = taskList.findTasksByDate(date);
        assertEquals(1, tasksByDate.getSize());
        assertEquals("Deadline 1", tasksByDate.getTask(0).getDescription());
    }

    /**
     * Tests the sorting of tasks by date.
     * Verifies that the tasks are correctly sorted and that the sorted list is returned.
     */
    @Test
    public void sortTasksByDate_success() {
        taskList.addTask(new Deadline("Deadline 1", "2023-10-10 2359"));
        taskList.addTask(new Event("Event 1", "2023-10-09 1000", "2023-10-09 1200"));
        TaskList sortedTasks = taskList.sortTasksByDate();
        assertEquals("Event 1", sortedTasks.getTask(0).getDescription());
        assertEquals("Deadline 1", sortedTasks.getTask(1).getDescription());
    }

    /**
     * Tests the getting of tasks.
     * Verifies that the tasks are correctly returned.
     */
    @Test
    public void getTasks_success() {
        assertEquals(2, taskList.getTasks().size());
    }

    /**
     * Tests the getting of the size of the task list.
     * Verifies that the size of the task list is correctly returned.
     */
    @Test
    public void getSize_success() {
        assertEquals(2, taskList.getSize());
    }

    /**
     * Tests the checking of whether the task list is empty.
     * Verifies that the task list is correctly checked and that the result is returned.
     */
    @Test
    public void isTaskListEmpty_success() {
        assertTrue(!taskList.isTaskListEmpty());
        taskList.deleteTask(0);
        taskList.deleteTask(0);
        assertTrue(taskList.isTaskListEmpty());
    }

    /**
     * Tests the getting of a task with a valid index.
     * Verifies that the task is correctly returned.
     */
    @Test
    public void getTask_validIndex_success() {
        Task task = taskList.getTask(0);
        assertEquals("Task 1", task.getDescription());
    }
}
