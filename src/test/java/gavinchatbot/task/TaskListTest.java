package gavinchatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gavinchatbot.util.GavinException;

public class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task1 = new Task("Read book");
        task2 = new Task("Write essay");
        taskList.addTask(task1);
        taskList.addTask(task2);
    }

    @Test
    void addTask_newTask_tasksSizeIncreases() {
        int initialSize = taskList.size();
        Task newTask = new Task("Go jogging");
        taskList.addTask(newTask);
        assertEquals(initialSize + 1, taskList.size());
    }

    @Test
    void getTask_validIndex_returnsCorrectTask() throws GavinException {
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
    }

    @Test
    void getTask_invalidIndex_throwsException() {
        assertThrows(GavinException.class, () -> taskList.getTask(-1));
        assertThrows(GavinException.class, () -> taskList.getTask(5));
    }

    @Test
    void markTask_validIndex_marksTaskAsDone() throws GavinException {
        taskList.markTask(0);
        assertTrue(task1.isDone());
    }

    @Test
    void unmarkTask_validIndex_unmarksTaskAsNotDone() throws GavinException {
        task1.markAsDone();
        taskList.unmarkTask(0);
        assertFalse(task1.isDone());
    }

    @Test
    void deleteTask_validIndex_deletesTask() throws GavinException {
        Task deletedTask = taskList.deleteTask(0);
        assertEquals(task1, deletedTask);
        assertEquals(1, taskList.size());
    }

    @Test
    void deleteTask_invalidIndex_throwsException() {
        assertThrows(GavinException.class, () -> taskList.deleteTask(-1));
        assertThrows(GavinException.class, () -> taskList.deleteTask(5));
    }

    @Test
    void countDoneTasks_tasksMarkedAsDone_returnsCorrectCount() throws GavinException {
        taskList.markTask(0);
        assertEquals(1, taskList.countDoneTasks());
    }

    @Test
    void size_afterAddingTasks_returnsCorrectSize() {
        assertEquals(2, taskList.size());
    }
}
