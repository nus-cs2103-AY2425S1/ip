package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTodo() {
        Todo todo = taskList.addTodo("Buy milk");
        assertTrue(taskList.get(0) instanceof Todo);
    }

    @Test
    void testRemoveTask() {
        taskList.addTodo("Buy milk");
        Task removedTask = taskList.removeTask(0);
        assertEquals(0, taskList.size());
        assertTrue(removedTask instanceof Todo);
    }
}
