package nether.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void addTask_validTask_taskAddedSuccessfully() {
        TodoTask task = new TodoTask("Read book");

        taskList.addTask(task);

        assertEquals(1, taskList.getSize());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void deleteTask_invalidIndexUnder_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void deleteTask_invalidIndexUpper_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(taskList.getSize() + 1));
    }

    // Tests for getSize()
    @Test
    void getSize_withEmptyTaskList_returnsZero() {
        assertEquals(0, taskList.getSize());
    }

    @Test
    void getSize_withMultipleTasks_returnsCorrectSize() {
        TaskList taskListTest = new TaskList();
        taskListTest.addTask(new TodoTask("Read a book"));
        taskListTest.addTask(new DeadlineTask("Submit assignment", "2024-09-01 2359"));
        assertEquals(2, taskListTest.getSize());
    }

    // Tests for getTask()
    @Test
    void getTask_withValidIndex_returnsCorrectTask() {
        Task task = new TodoTask("Go jogging");
        TaskList taskListTest = new TaskList();
        taskListTest.addTask(task);
        assertEquals(task, taskListTest.getTask(0));
    }

    // Tests for getTasks()
    @Test
    void getTasks_returnsListOfAllTasks() {
        List<Task> taskListTest = new ArrayList<>();
        taskListTest.add(new TodoTask("Finish project"));
        taskListTest.add(new EventTask("Team meeting", "2024-09-01 2100", "2024-09-02 2300"));
        TaskList taskList = new TaskList(taskListTest);
        assertEquals(taskListTest, taskList.getTasks());
    }
}
