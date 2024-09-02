package friendlybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import friendlybot.FriendlyBotStub;

/**
 * A JUnit test for TaskList.
 */
public class TaskListTest {
    /**
     * Tests adding a Task to the TaskList.
     */
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new ToDo("test 1"));
        taskList.addTask(new ToDo("test 2"));
        taskList.addTask(new ToDo("test 3"));
        assertEquals(3, taskList.getNumTasks());
    }

    /**
     * Tests deleting a Task from the TaskList.
     */
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

    /**
     * Tests getting Tasks of a specified date from the TaskList.
     */
    @Test
    public void testGetTasksOnDate() {
        LocalDate date1 = LocalDate.of(2024, 8, 26);
        LocalDate date2 = LocalDate.of(2024, 1, 1);
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new ToDo("test 1"));
        taskList.addTask(new Deadline("test 2", date2));
        taskList.addTask(new Event("test 3", date1, date1));
        taskList.addTask(new Event("test 4", date1, date2));
        assertEquals(taskList.getTasksOnDate(date1).size(), 2);
    }

    /**
     * Tests if findTasks correctly finds the tasks with the given keyword.
     */
    @Test
    public void testFindTasks() {
        LocalDate dummyDate1 = LocalDate.of(2024, 8, 1);
        LocalDate dummyDate2 = LocalDate.of(2024, 8, 26);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        friendlyBotStub.getTasks().addTask(new Event("borrow book", dummyDate1, dummyDate1));
        friendlyBotStub.getTasks().addTask(new ToDo("test task 1"));
        friendlyBotStub.getTasks().addTask(new ToDo("read book"));
        friendlyBotStub.getTasks().addTask(new ToDo("test task 2"));
        friendlyBotStub.getTasks().addTask(new Deadline("return book", dummyDate2));
        friendlyBotStub.getTasks().addTask(new ToDo("test task 3"));
        assertEquals(6, friendlyBotStub.getTasks().getNumTasks());
        assertEquals(3, friendlyBotStub.getTasks().findTasks("book").size());
    }
}
