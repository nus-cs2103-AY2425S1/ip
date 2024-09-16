package quack.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quack.exception.FailedUpdateException;
import quack.exception.InvalidCommandException;
import quack.tasks.DeadlineTask;
import quack.tasks.Task;
import quack.tasks.ToDoTask;

/**
 * This class is to test the functionality of the TaskList class.
 */
public class TaskListTest {

    /** Date time format for printing LocalDateTime objects */
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /** List to store tasks */
    private TaskList taskList;

    /**
     * Initializes and sets all variables to its default value after each test.
     */
    @BeforeEach
    public void initializeVariables() {

        this.taskList = new TaskList();
        ArrayList<Task> list = this.taskList.getToDoList();

        ToDoTask dummyTask = new ToDoTask("ToDo Dummy");
        list.add(dummyTask);
    }

    /**
     * Tests the add task functionality.
     * <p>
     * Ensures that the task is correctly added into the task list.
     */
    @Test
    void testAddTask() {

        LocalDateTime dummyDate = LocalDateTime.parse("10/08/2024 10:00:00", this.dateFormat);
        DeadlineTask dummyTask = new DeadlineTask("Dummy", dummyDate);
        DeadlineTask expectedTaskAdded = new DeadlineTask("Dummy", dummyDate);
        ArrayList<Task> list = this.taskList.getToDoList();

        this.taskList.addTask(dummyTask);

        assertEquals(2, this.taskList.getLength(),
            "The task list did not add the task into the list");

        Task task = list.get(1);

        assertEquals(expectedTaskAdded, task,
            "The task wrong task is added into the list");
    }

    /**
     * Tests the delete task functionality.
     * <p>
     * Ensures that the correct task is deleted from the task list.
     */
    @Test
    void testDeleteTask() {

        LocalDateTime dummyDate = LocalDateTime.parse("10/08/2024 10:00:00", this.dateFormat);
        DeadlineTask dummyTask = new DeadlineTask("Dummy", dummyDate);
        DeadlineTask expectedTaskLeft = new DeadlineTask("Dummy", dummyDate);
        ArrayList<Task> list = this.taskList.getToDoList();

        list.add(dummyTask);

        this.taskList.deleteTask(1);

        assertEquals(1, this.taskList.getLength(),
            "The task list did not delete the task at all");

        Task task = list.get(0);

        assertEquals(expectedTaskLeft, task,
            "The wrong task is deleted into the list");
    }

    /**
     * Tests the filter task list functionality.
     * <p>
     * Ensures that the correct tasks are filtered from the task list.
     */
    @Test
    void testFilterTasks() {

        TaskList resultNoTask = this.taskList.filterTasks("There should be no task");

        assertNotEquals(this.taskList, resultNoTask,
            "The task list did not filter the list correctly");

        TaskList resultHaveTask = this.taskList.filterTasks("Dum");

        assertEquals(this.taskList, resultHaveTask,
            "The task list did not filter the list correctly");
    }

    /**
     * Tests the toString function.
     * <p>
     * Ensures that the toString function outputs the correct string
     * representation for the task list.
     */
    @Test
    void testToString() {
        LocalDateTime dummyDate = LocalDateTime.parse("10/08/2024 10:00:00", this.dateFormat);
        DeadlineTask dummyTask = new DeadlineTask("Dummy Dummy", dummyDate);
        String expectedOutput = "1) [T][ ] ToDo Dummy\n"
            + "2) [D][ ] Dummy Dummy (Due by: 10/08/2024 10:00:00)\n";
        ArrayList<Task> list = this.taskList.getToDoList();

        list.add(dummyTask);

        String output = this.taskList.toString();

        assertEquals(expectedOutput, output,
            "The toString function did not output the correct format");
    }

    /**
     * Tests the tagging task functionality.
     * <p>
     * Ensures that the correct task is tagged with the correct lable.
     */
    @Test
    void testAddTag() {

        ToDoTask dummyTask = new ToDoTask("Dummy");
        ToDoTask expectedTask = new ToDoTask("Dummy");
        Exception exception = null;
        expectedTask.tag("Test");
        ArrayList<Task> list = this.taskList.getToDoList();
        list.add(dummyTask);

        try {
            Task result = this.taskList.updateTag(2, "addtag", "Test");
            assertEquals(expectedTask, result,
                "updateTag function did not update the correct task with the correct tag");
        } catch (InvalidCommandException commandError) {
            exception = commandError;
        }

        // The test case should not cause an error to be thrown
        assertEquals(null, exception,
            "updateTag function threw an exception when it is not supposed to");
    }

    /**
     * Tests the remove tag functionality.
     * <p>
     * Ensures that the tag from the correct task is removed.
     */
    @Test
    void testRemoveTag() {

        ToDoTask expectedTask = new ToDoTask("ToDo Dummy");
        Exception exception = null;

        try {
            this.taskList.updateTag(1, "removetag", "Test");
            Task result = this.taskList.updateTag(1, "removetag");
            assertEquals(expectedTask, result,
                "updateTag function did not remove the tag from the corrosponding task");
        } catch (InvalidCommandException commandError) {
            exception = commandError;
        }

        // The test case should not cause an error to be thrown
        assertEquals(null, exception,
            "updateTag function threw an exception when it is not supposed to");
    }

    /**
     * Tests the mark task functionality.
     * <p>
     * Ensures that correct task is marked.
     */
    @Test
    void testUpdateTaskMark() {

        ToDoTask dummyTask = new ToDoTask("Dummy");
        ToDoTask expectedTask = new ToDoTask("Dummy");
        expectedTask.mark();
        ArrayList<Task> list = this.taskList.getToDoList();
        list.add(dummyTask);

        try {
            Task result = this.taskList.updateTask(2, "mark");
            assertEquals(expectedTask, result,
                "updateTask function did not mark the correct task");
        } catch (FailedUpdateException updateError) {
            // The test case should not cause an error to be thrown
            assertEquals(true, false,
                "updateTask function threw an exception when it is not supposed to");
        } catch (InvalidCommandException updateError) {
            // The test case should not cause an error to be thrown
            assertEquals(true, false,
                "updateTask function threw an exception when it is not supposed to");
        }
    }

    /**
     * Tests the unmark task functionality.
     * <p>
     * Ensures that correct task is unmarked.
     */
    @Test
    void testUpdateTaskUnmark() {

        ToDoTask expectedTask = new ToDoTask("ToDo Dummy");

        try {
            this.taskList.updateTask(1, "mark");
            Task result = this.taskList.updateTask(1, "unmark");
            assertEquals(expectedTask, result,
                "updateTask function did not mark the correct task");
        } catch (FailedUpdateException updateError) {
            // The test case should not cause an error to be thrown
            assertEquals(true, false,
                "updateTask function threw an exception when it is not supposed to");
        } catch (InvalidCommandException updateError) {
            // The test case should not cause an error to be thrown
            assertEquals(true, false,
                "updateTask function threw an exception when it is not supposed to");
        }
    }

    /**
     * Tests the equals functionality.
     * <p>
     * Ensures that the correct output is given when 2 task lists are the same.
     */
    @Test
    void testEquals() {

        ToDoTask dummyTask1 = new ToDoTask("Hello");
        ToDoTask dummyTask2 = new ToDoTask("ToDo Dummy");
        ToDoTask dummyTask3 = new ToDoTask("Nope");

        TaskList expectedTaskList = new TaskList();
        ArrayList<Task> expectedList = expectedTaskList.getToDoList();
        expectedList.add(dummyTask1);
        expectedList.add(dummyTask2);

        ArrayList<Task> observedTaskList = this.taskList.getToDoList();
        observedTaskList.add(dummyTask1);

        assertEquals(expectedTaskList, this.taskList,
            "The 2 list should be equal");

        observedTaskList.add(dummyTask3);

        assertNotEquals(expectedTaskList, this.taskList,
            "The 2 list should not be equal");
    }
}
