package quack.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quack.tasks.Task;
import quack.tasks.ToDoTask;
import quack.tasks.DeadlineTask;

public class TaskListTest {

    /** Date time format for printing LocalDateTime objects */
    private DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
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

        LocalDateTime dummyDate = LocalDateTime.parse("10/08/2024 10:00:00", this.DATE_FORMAT);
        DeadlineTask dummyTask = new DeadlineTask("Dummy", dummyDate);

        this.taskList.addTask(dummyTask);

        DeadlineTask expectedTaskAdded = new DeadlineTask("Dummy", dummyDate);

        assertEquals(2, this.taskList.getLength(),
            "The task list did not add the task into the list");
        
        ArrayList<Task> list = this.taskList.getToDoList();
        Task task = list.get(1);

        assertEquals(expectedTaskAdded, task,
            "The task wrong task is added into the list");
    }

    @Test
    void testDeleteTask() {

    }

    @Test
    void testFilterTasks() {

    }

    @Test
    void testToString() {

    }

    @Test
    void testUpdateTag() {

    }

    @Test
    void testUpdateTask() {

    }
}
