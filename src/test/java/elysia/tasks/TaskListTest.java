package elysia.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    TaskList taskList;

    @BeforeEach
    void setup() {
        taskList = new TaskList();
    }

    @Test
    void addTask_correctTask_taskAdded() {
        Task newTask = new Task("task");
        taskList.addTask(newTask);
        assertEquals(1, taskList.list.size());
        assertEquals(newTask, taskList.list.get(0));
    }

    @Test
    void deleteTask_correctIndex_taskDeleted() {
        Task newTask = new Task("task");
        taskList.addTask(newTask);
        assertEquals(newTask, taskList.deleteTask(1));
        assertEquals(0, taskList.list.size());
    }

    @Test
    void deleteTask_wrongIndex_exceptionThrown() {
        try {
            taskList.deleteTask(1);
            fail();
        } catch (IndexOutOfBoundsException ignored) {}
    }
}
