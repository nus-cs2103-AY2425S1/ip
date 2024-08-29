package cloud.util;

import cloud.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private static class TaskStub extends Task {
        private TaskStub(String description) {
            super(description);
        }

    }

    private TaskList taskList;
    private TaskStub task;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
        this.task = new TaskStub("Test task");
    }

    @Test
    public void add_addTask_increaseTaskCount() {
        int before = taskList.getTaskCount();
        taskList.add(task);
        assertTrue(before < taskList.getTaskCount());
    }

    @Test
    public void getLatestTask_emptyList_correctRepresentation() {
        assertEquals("", taskList.getLatestTask());
    }

    @Test
    public void toString_withMarkedTasks_correctRepresentation() {
        task.markDone();
        taskList.add(task);
        String expected = "1: [X] Test task\n";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void toString_withUnmarkedTasks_correctRepresentation() {
        taskList.add(task);
        String expected = "1: [ ] Test task\n";
        assertEquals(expected, taskList.toString());
    }

}
