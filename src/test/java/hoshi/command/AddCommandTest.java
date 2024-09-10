package hoshi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;


/**
 * Parser test class for JUnit testing of Parser class
 */
public class AddCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;


    /**
     * Set up mocked objects for use in JUnit testing
     */
    @BeforeEach
    void setUp() {
        taskList = mock(TaskList.class);
        ui = mock(Ui.class);
        storage = mock(Storage.class);
    }

    /**
     * Tests the success case of execute(add to do) function
     */
    @Test
    public void executeTest_addTodo_success() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "todo", "Finish ASG1"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        when(ui.displayTaskAdded("Finish ASG1")).thenReturn("added: Finish ASG1");

        // Mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        verify(ui).displayTaskAdded("Finish ASG1");
        assertEquals("added: Finish ASG1", response);

    }

    @Test
    public void executeTest_addDeadline_success() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "deadline", "Finish ASG1", "2023-12-05"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        when(ui.displayTaskAdded("Finish ASG1")).thenReturn("added: Finish ASG1");

        // Mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        verify(ui).displayTaskAdded("Finish ASG1");
        assertEquals("added: Finish ASG1", response);

    }

    @Test
    public void executeTest_addEvent_success() throws IOException {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "event", "Finish ASG1", "2023-12-05", "2023-12-22"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        when(ui.displayTaskAdded("Finish ASG1")).thenReturn("added: Finish ASG1");

        // Mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        verify(ui).displayTaskAdded("Finish ASG1");
        assertEquals("added: Finish ASG1", response);

    }

    /**
     * Tests the fail case of execute(add to do) function
     */
    @Test
    public void executeTest_todoEmptyDescription_failure() {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "todo", ""};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        when(ui.displayError("Hoshi doesn't understand! The task description is empty."))
                .thenReturn("Hoshi needs a task description!");

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        assertEquals("Hoshi needs a task description!", response);

    }

    /**
     * Tests the fail case of execute(add deadline) function
     */
    @Test
    public void executeTest_deadlineInvalidDate_failure() {

        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "deadline", "Finish ASG1", "25th January"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        when(ui.displayError("Hoshi doesn't understand! Try YYYY-MM-DD format for the deadline."))
                .thenReturn("Hoshi doesn't understand! Try YYYY-MM-DD format for the deadline.");

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        assertEquals("Hoshi doesn't understand! Try YYYY-MM-DD format for the deadline.", response);

    }


}
