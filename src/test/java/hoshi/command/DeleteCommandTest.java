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

public class DeleteCommandTest {

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
     * Tests the success case of execute(delete a task) function
     */
    @Test
    public void executeTest_delete_success() throws IOException {

        // prepare mocked objects/behaviour and input
        int markIndex = 0;
        DeleteCommand deleteCommand = spy(new DeleteCommand(markIndex));

        Task task = new Todo("Mocked Description");

        when(tasks.size()).thenReturn(1);
        when(tasks.get(anyInt())).thenReturn(task);
        when(ui.displayTaskDeleted(task)).thenReturn("OK, Hoshi has removed ( 1 )!");

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = deleteCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("OK, Hoshi has removed ( 1 )!", response);

    }

    /**
     * Tests the fail case of execute(delete a task) function
     */
    @Test
    public void executeTest_deleteInvalidIndex_failure() throws IOException {

        // prepare mocked objects/behaviour and input
        int markIndex = 2;
        DeleteCommand deleteCommand = spy(new DeleteCommand(markIndex));

        when(tasks.size()).thenReturn(1);
        when(ui.displayError("Hoshi doesn't have such a task!"))
                .thenReturn("Hoshi doesn't have such a task!");

        // mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = deleteCommand.execute(tasks, ui, storage);

        // assert
        assertEquals("Hoshi doesn't have such a task!", response);

    }
}
