package muller.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muller.command.MullerException;



class TaskListTest {

    private TaskList taskList;
    private Task task;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task = new Task("read book");
        taskList.addTask(task);
    }

    @Test
    void testAddTask() throws MullerException {
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    void testDeleteTask() throws Exception {
        taskList.deleteTask(0);
        assertEquals(0, taskList.size());
    }
}


