package wansbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void number_emptyReturnsNull() {
        TaskList taskList = new TaskList();
        assertEquals(null, taskList.getTask(3));
    }

    @Test
    public void number_nonEmptyReturnsTask() {
        TaskList taskList = new TaskList();
        taskList.add(new Todos("read"));
        assertEquals(new Todos("read"), taskList.getTask(0));
    }
}
