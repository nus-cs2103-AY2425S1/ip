package hoshi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;


/**
 * Handles JUnit testing of AddCommand class
 */
public class AddCommandTest {

    private TaskList taskList;
    private Storage storage;
    private final Ui ui = new Ui();

    /**
     * Set up mocked objects for use in JUnit testing
     */
    @BeforeEach
    void setUp() {
        taskList = mock(TaskList.class);
        storage = mock(Storage.class);
    }

    /**
     * Tests the success case of execute(add to do) function
     */
    @Test
    public void executeTest_addTodo_success() throws IOException {
        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "Todo", "Finish ASG1"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        // Mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        //verify(ui).displayTaskAdded("Finish ASG1", splitInput[1]);
        verify(storage).save(any(TaskList.class));
        assertEquals("Hoshi has added Todo: Finish ASG1", response);
    }

    @Test
    public void executeTest_addDeadline_success() throws IOException {
        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "Deadline", "Finish ASG1", "2023-12-05"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        // Mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        verify(storage).save(any(TaskList.class));
        assertEquals("Hoshi has added Deadline: Finish ASG1", response);
    }

    @Test
    public void executeTest_addEvent_success() throws IOException {
        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "Event", "Finish ASG1", "2023-12-05", "2023-12-22"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        // Mock storage so save is not called
        doNothing().when(storage).save(any(TaskList.class));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        verify(storage).save(any(TaskList.class));
        assertEquals("Hoshi has added Event: Finish ASG1", response);
    }

    /**
     * Tests the fail case of execute(add to do) function
     */
    @Test
    public void executeTest_todoEmptyDescription_failure() {
        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "todo", ""};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        assertEquals("Hoshi doesn't understand! The task description is empty.", response);
    }

    /**
     * Tests the fail case of execute(unknown task type) function
     */
    @Test
    public void executeTest_unknownTaskType_failure() {
        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "random"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        assertEquals("Hoshi doesn't understand! Unknown task type.", response);
    }

    /**
     * Tests the fail case of execute(no task type) function
     */
    @Test
    public void executeTest_noTaskType_failure() {
        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        assertEquals("Hoshi wants you to try specifying the task!", response);
    }

    /**
     * Tests the fail case invalid date of execute(add deadline) function
     */
    @Test
    public void executeTest_deadlineInvalidDate_failure() {
        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "deadline", "Finish ASG1", "25th January"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        assertEquals("Hoshi doesn't understand! Try YYYY-MM-DD format for the deadline.", response);
    }

    /**
     * Tests the fail case invalid date of execute(add event) function
     */
    @Test
    public void executeTest_eventInvalidDate_failure() {
        // prepare mocked objects/behaviour and input
        String[] splitInput = {"add", "event", "Finish", "ASG1", "25th January"};
        AddCommand addCommand = spy(new AddCommand(splitInput));

        // execute
        String response = addCommand.execute(taskList, ui, storage);

        // assert
        assertEquals("Hoshi doesn't understand! Try YYYY-MM-DD format for the event.", response);
    }


}
