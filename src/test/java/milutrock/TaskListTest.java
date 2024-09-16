package milutrock;

import org.junit.jupiter.api.Test;

import milutrock.tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTask() {    
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("DummyTask"));

        assertEquals(taskList.getNumberOfTasks(), 1);
        assertEquals(taskList.getTaskAtIndexAsString(0), "[ ] DummyTask");
        
        taskList.addTask(new Task("DummyTask 2"));
        
        assertEquals(taskList.getNumberOfTasks(), 2);
        assertEquals(taskList.getTaskAtIndexAsString(1), "[ ] DummyTask 2");
    }
    
    @Test
    public void testRemoveTask() {    
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("DummyTask"));
        taskList.addTask(new Task("DummyTask 2"));
        taskList.removeTask(0);

        assertEquals(taskList.getNumberOfTasks(), 1);
        assertEquals(taskList.getTaskAtIndexAsString(0), "[ ] DummyTask 2");
    }
    
    @Test
    public void testMarkTaskAsDone() {    
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("DummyTask"));
        taskList.markTaskAsDone(0);

        assertEquals(taskList.getTaskAtIndexAsString(0), "[X] DummyTask");
    }
    
    @Test
    public void testUnmarkTaskAsDone() {    
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("DummyTask"));
        taskList.markTaskAsDone(0);
        taskList.unmarkTaskAsDone(0);

        assertEquals(taskList.getTaskAtIndexAsString(0), "[ ] DummyTask");
    }
}
