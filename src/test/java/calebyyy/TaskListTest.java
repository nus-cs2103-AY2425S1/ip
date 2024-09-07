package calebyyy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import calebyyy.tasks.Task;


public class TaskListTest {

    @Test
    public void testAddTask() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui);
        Task task = new Task("Test Task");
        try {
            taskList.addTask(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, taskList.getTaskCount());
        assertEquals(task, taskList.getTasks().get(0));
    }

    @Test
    public void testDeleteTask() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui);
        Task task = new Task("Test Task");
        try {
            taskList.addTask(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        taskList.deleteTask(1);
        assertEquals(0, taskList.getTaskCount());
    }
}
