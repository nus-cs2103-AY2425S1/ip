package jackson.utils;

import jackson.exceptions.DuplicatedTaskException;
import jackson.exceptions.OutOfListException;
import jackson.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void addTask_invalidIndex_DuplicatedTaskExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertThrows(DuplicatedTaskException.class, () -> {taskList.addTask(new Todo("lambda"));});
    }

    @Test
    public void deleteTask_invalidIndex_OutOfListExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertThrows(OutOfListException.class, () -> {taskList.deleteTask(2);});
        assertThrows(OutOfListException.class, () -> {taskList.deleteTask(-1);});
    }

    @Test
    public void deleteTask_validIndex_NoExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertDoesNotThrow(() -> {taskList.deleteTask(0);});
    }

    @Test
    public void mark_invalidIndex_OutOfListExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertThrows(OutOfListException.class, () -> {taskList.mark(2);});
        assertThrows(OutOfListException.class, () -> {taskList.mark(-1);});
    }

    @Test
    public void unmark_invalidIndex_OutOfListExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertThrows(OutOfListException.class, () -> {taskList.unmark(2);});
        assertThrows(OutOfListException.class, () -> {taskList.unmark(-1);});
    }
}
