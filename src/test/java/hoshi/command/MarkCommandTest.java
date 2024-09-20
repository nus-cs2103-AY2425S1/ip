package hoshi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.task.Todo;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

public class MarkCommandTest {

    private TaskList tasks;
    private Storage storage;
    private final Ui ui = new Ui();

    /**
     * Set up mocked objects for use in JUnit testing
     */
    @BeforeEach
    void setUp() {
        tasks = mock(TaskList.class);
        storage = mock(Storage.class);
    }

    /**
     * Tests the success case of execute(mark a task) function
     */
    @Test
    public void executeTest_mark_success() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"mark", "1"};
        MarkCommand markCommand = spy(new MarkCommand(splitInput, true));

        Task task = new Todo("Mocked Description");

        when(tasks.size()).thenReturn(1);
        when(tasks.get(anyInt())).thenReturn(task);

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = markCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Nice! Hoshi has marked this task as done: \n"
                + "[T][X] Mocked Description", response);
        assertTrue(task.isDone());

    }

    /**
     * Tests the success case of execute(unmark a task) function
     */
    @Test
    public void executeTest_unmark_success() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"unmark", "1"};
        MarkCommand markCommand = spy(new MarkCommand(splitInput, false));

        Task task = new Todo("Mocked Description");
        task.setIsDone(true);

        when(tasks.size()).thenReturn(1);
        when(tasks.get(anyInt())).thenReturn(task);

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = markCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Nice! Hoshi has marked this task as not done: \n"
                + "[T][ ] Mocked Description", response);
        assertFalse(task.isDone());
    }

    /**
     * Tests the fail case of execute(mark a task) function
     */
    @Test
    public void executeTest_markInvalidIndex_failure() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"mark", "2"};
        MarkCommand markCommand = spy(new MarkCommand(splitInput, true));

        when(tasks.size()).thenReturn(1);

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = markCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Hoshi doesn't have such a task!", response);
    }

    /**
     * Tests the invalid index fail case of execute(unmark a task) function
     */
    @Test
    public void executeTest_unmarkInvalidIndex_failure() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"unmark", "2"};
        MarkCommand markCommand = spy(new MarkCommand(splitInput, false));

        when(tasks.size()).thenReturn(1);

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = markCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Hoshi doesn't have such a task!", response);
    }

    /**
     * Tests the already marked fail case of execute(mark a task) function
     */
    @Test
    public void executeTest_markAlreadyMarked_failure() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"mark", "1"};
        MarkCommand markCommand = spy(new MarkCommand(splitInput, true));
        Task newTask = new Todo("Mocked Description");
        newTask.setIsDone(true);

        when(tasks.size()).thenReturn(1);
        when(tasks.get(anyInt())).thenReturn(newTask);

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = markCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Hoshi has already marked this task!", response);
    }

    /**
     * Tests the already unmarked fail case of execute(unmark a task) function
     */
    @Test
    public void executeTest_unmarkAlreadyUnmarked_failure() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"unmark", "1"};
        MarkCommand markCommand = spy(new MarkCommand(splitInput, false));
        Task newTask = new Todo("Mocked Description");
        newTask.setIsDone(false);

        when(tasks.size()).thenReturn(1);
        when(tasks.get(anyInt())).thenReturn(newTask);

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = markCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Hoshi has already unmarked this task!", response);
    }
}
