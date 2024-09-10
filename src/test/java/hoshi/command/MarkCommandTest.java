package hoshi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private Ui ui;
    private Storage storage;


    /**
     * Set up mocked objects for use in JUnit testing
     */
    @BeforeEach
    void setUp() {
        tasks = mock(TaskList.class);
        ui = mock(Ui.class);
        storage = mock(Storage.class);
    }

    /**
     * Tests the success case of execute(mark a task) function
     */
    @Test
    public void executeTest_mark_success() throws IOException {

        // prepare mocked objects/behaviour and input
        int markIndex = 0;
        MarkCommand markCommand = spy(new MarkCommand(markIndex));

        Task task = new Todo("Mocked Description");

        when(tasks.size()).thenReturn(1);
        when(tasks.get(anyInt())).thenReturn(task);
        when(ui.displayTaskMarked(task)).thenReturn("Nice! I've marked this task as done: \n [T][X] desc1");

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = markCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Nice! I've marked this task as done: \n [T][X] desc1", response);

    }

    /**
     * Tests the fail case of execute(mark a task) function
     */
    @Test
    public void executeTest_markInvalidIndex_failure() throws IOException {

        // prepare mocked objects/behaviour and input
        int markIndex = 2;
        MarkCommand markCommand = spy(new MarkCommand(markIndex));

        when(tasks.size()).thenReturn(1);
        when(ui.displayError("Hoshi doesn't have such a task!"))
                .thenReturn("Hoshi doesn't have such a task!");

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = markCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Hoshi doesn't have such a task!", response);

    }
}
