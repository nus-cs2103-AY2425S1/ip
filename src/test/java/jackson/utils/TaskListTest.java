package jackson.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jackson.exceptions.DuplicatedTaskException;
import jackson.exceptions.OutOfListException;
import jackson.tasks.Todo;

public class TaskListTest {
    @Test
    public void addTask_invalidIndex_duplicatedTaskExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertThrows(DuplicatedTaskException.class, () -> taskList.addTask(new Todo("lambda")));
    }

    @Test
    public void deleteTask_invalidIndex_outOfListExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertThrows(OutOfListException.class, () -> taskList.deleteTask(2));
        assertThrows(OutOfListException.class, () -> taskList.deleteTask(-1));
    }

    @Test
    public void deleteTask_validIndex_noExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertDoesNotThrow(() -> taskList.deleteTask(0));
    }

    @Test
    public void mark_invalidIndex_outOfListExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertThrows(OutOfListException.class, () -> taskList.mark(2));
        assertThrows(OutOfListException.class, () -> taskList.mark(-1));
    }

    @Test
    public void unmark_invalidIndex_outOfListExceptionThrown() {
        TaskList taskList = new TaskList(new Todo("lambda"));

        assertThrows(OutOfListException.class, () -> taskList.unmark(2));
        assertThrows(OutOfListException.class, () -> taskList.unmark(-1));
    }
}
