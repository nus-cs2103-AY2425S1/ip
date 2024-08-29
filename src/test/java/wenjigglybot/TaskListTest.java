package wenjigglybot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;
    private Task testTask;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        testTask = new ToDoTask("meemaw");
    }

    @Test
    public void addTask_emptyTaskList_success() {
        taskList.addTask(testTask);
        assertEquals(1, taskList.size());
    }

    @Test
    public void getTask_oneTask_success() {
        taskList.addTask(testTask);
        assertEquals(testTask.toString(), taskList.get(0).toString());
    }
}