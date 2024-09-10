package cook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.ToDo;

public class TaskListTest {

    @Test
    public void mark_task_trueReturned() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");

        tasks.addTask(todo);

        assertEquals(true, tasks.markTask(1));
    }

    @Test
    public void unmark_task_falseReturned() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");

        tasks.addTask(todo);

        assertEquals(false, tasks.unmarkTask(1));
    }

    @Test
    public void mark_markedTask_trueReturned() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");
        todo.mark();

        tasks.addTask(todo);

        assertEquals(false, tasks.markTask(1));
    }

    @Test
    public void unmark_markedTask_falseReturned() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");
        todo.mark();

        tasks.addTask(todo);

        assertEquals(true, tasks.unmarkTask(1));
    }

    @Test
    public void delete_task_emptyStringReturned() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test ToDo");

        tasks.addTask(todo);
        tasks.deleteTask(1);

        assertEquals("", tasks.toString());
    }

}
