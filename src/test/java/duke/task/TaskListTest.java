package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTask_taskListSizeIncreases() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Sample task");
        taskList.add(task);
        assertEquals(1, taskList.getSize(), "TaskList size should increase after adding a task.");
    }

    @Test
    public void getTask_taskIsRetrievedCorrectly() {
        TaskList taskList = new TaskList();
        Task task = new Deadline("Submit assignment", "2024-09-01 1500");
        taskList.add(task);
        assertEquals(task, taskList.get(0), "The task retrieved should be the same as the task added.");
    }
}
