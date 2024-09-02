package yapyap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTask_todoTask_taskAddedSuccess() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("test");
        tasks.addTask(todo);

        assertEquals(1, tasks.size());
        assertEquals(todo, tasks.get(0));
    }

    @Test
    public void deleteTask_exitingTodo_taskDeletedSuccess() {
        TaskList tasks = new TaskList();
        Todo todo1 = new Todo("test1");
        Todo todo2 = new Todo("test2");
        tasks.addTask(todo1);
        tasks.addTask(todo2);
        tasks.deleteTask(todo1);

        assertEquals(1, tasks.size());
        assertEquals(todo2, tasks.get(0));
    }
}
