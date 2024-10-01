package garfield.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import garfield.exceptions.GarfieldException;


class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        task1 = new Todo("Submit assignment");
        task2 = new Todo("Buy groceries");
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    void constructor_emptyList_taskListInitialized() {
        assertTrue(taskList.isEmpty());
    }

    @Test
    void constructor_listWithTasks_taskListInitializedWithTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        TaskList populatedTaskList = new TaskList(tasks);

        assertEquals(2, populatedTaskList.size());
        assertEquals("Submit assignment", populatedTaskList.getArrayList().get(0).getTaskDescription());
        assertEquals("Buy groceries", populatedTaskList.getArrayList().get(1).getTaskDescription());
    }

    @Test
    void addTask_taskAdded_taskListSizeIncreased() {
        taskList.add(task1);
        assertEquals(1, taskList.size());
        assertEquals("Submit assignment", taskList.getArrayList().get(0).getTaskDescription());

        taskList.add(task2);
        assertEquals(2, taskList.size());
        assertEquals("Buy groceries", taskList.getArrayList().get(1).getTaskDescription());
    }

    @Test
    void deleteTask_validIndex_taskDeleted() throws GarfieldException {
        taskList.add(task1);
        taskList.add(task2);

        String deletedTask = taskList.delete(1);
        assertEquals("[T][ ] Submit assignment", deletedTask);
        assertEquals(1, taskList.size());
        assertEquals("Buy groceries", taskList.getArrayList().get(0).getTaskDescription());
    }

    @Test
    void deleteTask_invalidIndex_exceptionThrown() {
        assertThrows(GarfieldException.class, () -> taskList.delete(1));
    }

    @Test
    void markTask_validIndex_taskMarkedAsDone() throws GarfieldException {
        taskList.add(task1);
        String markedTask = taskList.mark(1);
        assertEquals("[T][X] Submit assignment", markedTask);
    }

    @Test
    void markTask_invalidIndex_exceptionThrown() {
        assertThrows(GarfieldException.class, () -> taskList.mark(1));
    }

    @Test
    void unmarkTask_validIndex_taskMarkedAsUndone() throws GarfieldException {
        taskList.add(task1);
        taskList.mark(1); // Mark it as done first
        String unmarkedTask = taskList.unmark(1);
        assertEquals("[T][ ] Submit assignment", unmarkedTask);
    }

    @Test
    void unmarkTask_invalidIndex_exceptionThrown() {
        assertThrows(GarfieldException.class, () -> taskList.unmark(1));
    }

    @Test
    void listTask_emptyList_emptyStringReturned() {
        assertTrue(taskList.isEmpty());
        assertEquals("", taskList.list());
    }

    @Test
    void listTask_tasksInList_formattedStringReturned() {
        taskList.add(task1);
        taskList.add(task2);

        String expectedList = "1. [T][ ] Submit assignment\n"
                + "2. [T][ ] Buy groceries\n";

        assertEquals(expectedList, taskList.list());
    }

    @Test
    void listKeywordTasks_noTasksWithKeyword_emptyStringReturned() {
        taskList.add(task1);
        taskList.add(task2);

        assertEquals("", taskList.listKeywordTasks("meeting"));
    }

    @Test
    void listKeywordTasks_tasksWithKeyword_formattedStringReturned() {
        taskList.add(task1);
        taskList.add(task2);

        String expected = "1. [T][ ] Submit assignment\n";
        assertEquals(expected, taskList.listKeywordTasks("Submit"));
    }
}
