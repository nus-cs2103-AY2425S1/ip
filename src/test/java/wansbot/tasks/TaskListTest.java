package wansbot.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void number_emptyReturnsNull() {
        TaskList taskList = new TaskList();
        assertEquals(null, taskList.number(3));
    }

    @Test
    public void number_nonEmptyReturnsTask() {
        TaskList taskList = new TaskList();
        taskList.add(new Todos("read"));
        assertEquals(new Todos("read"), taskList.number(0));
    }
}
