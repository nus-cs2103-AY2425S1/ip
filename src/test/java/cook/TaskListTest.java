package cook;

import org.junit.jupiter.api.Test;
import tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void markTaskTest() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");

        tasks.addTask(todo);

        assertEquals(true, tasks.markTask(1, true));
    }

    @Test
    public void unmarkTaskTest() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");

        tasks.addTask(todo);

        assertEquals(false, tasks.markTask(1, false));
    }

    @Test
    public void markMarkedTaskTest() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");
        todo.mark(true);

        tasks.addTask(todo);

        assertEquals(false, tasks.markTask(1, true));
    }

    @Test
    public void unmarkMarkedTaskTest() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");
        todo.mark(true);

        tasks.addTask(todo);

        assertEquals(true, tasks.markTask(1, false));
    }

    @Test
    public void deleteTaskTest() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");

        tasks.addTask(todo);
        tasks.deleteTask(1);

        assertEquals("", tasks.toString());
    }

}