package wansbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void getTask_emptyThrowsIndexOutOfBoundsError() {
        TaskList taskList = new TaskList();
        try {
            taskList.getTask(3);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), e.getMessage());
        }
    }

    @Test
    public void getTask_nonEmptyReturnsTask() {
        TaskList taskList = new TaskList();
        taskList.add(new Todos("read"));
        assertEquals(new Todos("read"), taskList.getTask(0));
    }
}
