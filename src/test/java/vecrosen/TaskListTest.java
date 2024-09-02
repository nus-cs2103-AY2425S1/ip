package vecrosen;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    /*@Test
    public void deadlineTest(){
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new Deadline("This is a deadline", "2019-12-15"));
        assertEquals("[D][ ] This is a deadline (by: 15 Dec 2019)", taskList.);
    }//*/
    @Test
    public void addDeleteTaskTest() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new Task("Hello world"));
        taskList.addTask(new Task("Hello world again"));
        taskList.addTask(new Task("Hello world thrice"));
        taskList.deleteTask(2);
        try {
            taskList.deleteTask(3);
        } catch (IndexOutOfBoundsException ignored) {
        }
        assertEquals(2, taskList.getListSize());
    }
}
