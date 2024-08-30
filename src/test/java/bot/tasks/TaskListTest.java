package bot.tasks;

import bot.Parser;
import bot.exceptions.InvalidTaskDescriptionException;
import bot.storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void taskList_initial_sizeZero() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    public void taskList_addValidTask_correctSize() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("foo"));
        assertEquals(1, tasks.size());
        tasks.add(new Todo("bar"));
        assertEquals(2, tasks.size());
    }

    @Test
    public void taskList_deleteValidTask_correctSize() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("foo"));
        tasks.add(new Todo("bar"));
        tasks.remove(0);
        assertEquals(1, tasks.size());
    }

    @Test
    public void taskList_deleteInvalidIndex_exceptionThrown() {
        IndexOutOfBoundsException e = assertThrows(
                IndexOutOfBoundsException.class,
                () -> new TaskList().remove(0)
        );

        assertEquals(
                new IndexOutOfBoundsException("Index 0 out of bounds for length 0").getMessage(),
                e.getMessage()
        );
    }

    @Test
    public void taskList_get_correctTask() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("foo"));
        assertEquals(new Todo("foo").toString(), tasks.get(0).toString());
    }

    @Test
    public void taskList_getInvalidIndex_exceptionThrown() {
        IndexOutOfBoundsException e = assertThrows(
                IndexOutOfBoundsException.class,
                () -> new TaskList().get(0)
        );

        assertEquals(
                new IndexOutOfBoundsException("Index 0 out of bounds for length 0").getMessage(),
                e.getMessage()
        );
    }

    @Test
    public void taskList_addDefaultTask_taskUnmarked() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("foo"));
        assertFalse(tasks.isMarked(0));
    }

    @Test
    public void taskList_addAlreadyMarkedTask_taskMarked() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("foo", true));
        assertTrue(tasks.isMarked(0));
    }

    @Test
    public void taskList_mark_taskMarked() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("foo"));
        tasks.mark(0);
        assertTrue(tasks.isMarked(0));
        tasks.mark(0);
        assertTrue(tasks.isMarked(0));
    }

    @Test
    public void taskList_unmark_taskUnmarked() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("foo"));
        tasks.mark(0);
        tasks.unmark(0);
        assertFalse(tasks.isMarked(0));
        tasks.unmark(0);
        assertFalse(tasks.isMarked(0));
    }
}
