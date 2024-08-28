package com.appleaster.task;

import com.appleaster.exception.AppleasterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void addTask_validTask_taskAdded() {
        Todo todo = new Todo("Buy groceries");
        taskList.addTask(todo);
        
        assertEquals(1, taskList.getTaskCount());
        assertEquals(todo, taskList.getTasks().get(0));
    }

    @Test
    void deleteTask_validIndex_taskDeleted() throws AppleasterException {
        Todo todo1 = new Todo("Buy groceries");
        Todo todo2 = new Todo("Do laundry");
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        
        int initialCount = taskList.getTaskCount();
        taskList.deleteTask(0);
        
        assertEquals(initialCount - 1, taskList.getTaskCount());
        assertEquals(todo2, taskList.getTasks().get(0));
    }

    @Test
    void deleteTask_invalidIndex_throwsException() {
        Todo todo = new Todo("Buy groceries");
        taskList.addTask(todo);
        
        assertThrows(AppleasterException.class, () -> {
            taskList.deleteTask(1);
        });
    }

    @Test
    void markTask_validIndex_taskMarked() throws AppleasterException {
        Todo todo = new Todo("Buy groceries");
        taskList.addTask(todo);
        
        taskList.markTask(0, true);
        
        assertTrue(taskList.getTasks().get(0).isCompleted());
    }
}