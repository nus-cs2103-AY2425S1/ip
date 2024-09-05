import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.task.ToDo;





class TaskListTest {

    @Test
    void addTask_addsTaskCorrectly() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Test ToDo");

        tasks.addTask(task);

        assertEquals(1, tasks.getSize(), "Task list size should be 1 after adding a task");
        assertEquals(task, tasks.getTask(0), "The task added should be the same as the one retrieved");
    }
}
