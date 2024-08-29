package milutrock;

import org.junit.jupiter.api.Test;

import milutrock.tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTask() {    
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("DummyTask"));

        assertEquals(taskList.noOfTasks(), 1);
        assertEquals(taskList.taskAtIndexToString(0), "[ ] DummyTask");
        
        taskList.addTask(new Task("DummyTask 2"));
        
        assertEquals(taskList.noOfTasks(), 2);
        assertEquals(taskList.taskAtIndexToString(1), "[ ] DummyTask 2");
    }
    
    @Test
    public void testRemoveTask() {    
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("DummyTask"));
        taskList.addTask(new Task("DummyTask 2"));
        taskList.removeTask(0);

        assertEquals(taskList.noOfTasks(), 1);
        assertEquals(taskList.taskAtIndexToString(0), "[ ] DummyTask 2");
    }
    
    @Test
    public void testMarkTaskAsDone() {    
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("DummyTask"));
        taskList.markTaskAsDone(0);

        assertEquals(taskList.taskAtIndexToString(0), "[X] DummyTask");
    }
    
    @Test
    public void testUnmarkTaskAsDone() {    
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("DummyTask"));
        taskList.markTaskAsDone(0);
        taskList.unmarkTaskAsDone(0);

        assertEquals(taskList.taskAtIndexToString(0), "[ ] DummyTask");
    }
}
