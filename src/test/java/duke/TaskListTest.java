package duke;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TaskListTest {

    private TaskList taskList;
    private IndividualTask task1;
    private IndividualTask task2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        task1 = new ToDo("Finish assignment");
        task2 = new ToDo("Go grocery shopping");

    }

    @Test
    public void testAddTask() {
        taskList.addTask(task1);
        Assertions.assertEquals(1, taskList.getListTask().size());
        Assertions.assertEquals(task1, taskList.getListTask().get(0));
    }

    @Test
    public void testDeleteTask() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.deleteTask(0);
        Assertions.assertEquals(1, taskList.getListTask().size());
        Assertions.assertEquals(task2, taskList.getListTask().get(0));
    }

    @Test
    public void testGetListTask() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        ArrayList<IndividualTask> tasks = taskList.getListTask();
        Assertions.assertEquals(2, tasks.size());
        Assertions.assertTrue(tasks.contains(task1));
        Assertions.assertTrue(tasks.contains(task2));
    }

    @Test
    public void testFormatListMessage() {
        String expected = "      1.[T][ ] Finish assignment";
        String formattedMessage = taskList.formatListMessage("1", task1);
        Assertions.assertEquals(expected, formattedMessage);
    }
}
