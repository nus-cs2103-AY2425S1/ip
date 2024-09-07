package hoshi.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.task.Todo;
import hoshi.ui.Ui;


/**
 * Parser test class for JUnit testing of Parser class
 */
public class ParserTest {

    private TaskList taskList;
    private Ui ui;
    private Parser parser;


    /**
     * Set up mocked objects for use in JUnit testing
     */
    @BeforeEach
    void setUp() {
        taskList = mock(TaskList.class);
        ui = mock(Ui.class);
        parser = new Parser();
    }

    /**
     * Tests the success case of handleMark function
     */
    @Test
    public void handleMarkTest_markCompleted_success() {

        // prepare mocked objects/behaviour and input

        Task mockedTask = mock(Task.class);
        //taskList.add(mockedTask);

        String input = "mark 1";

        // when taskList.size() is called, return 1
        when(taskList.size()).thenReturn(1);

        // when taskList.get(0) is called, return mocked task
        when(taskList.get(0)).thenReturn(mockedTask);

        // test
        parser.handleMark(input, taskList, ui);

        // assert

        // check if task was marked
        verify(mockedTask).setIsDone(true);

        // check if UI was displayed
        verify(ui).displayTaskMarked(mockedTask);

    }

    /**
     * Tests the invalid index case of handleMark function
     */
    @Test
    public void handleMarkTest_invalidIndex_errorDisplayed() {

        // prepare mocked objects/behaviour and input

        String input = "mark 2";

        // when taskList.size() is called, return 1
        when(taskList.size()).thenReturn(1);

        // note - need to mock parser and handleMark as well if not the mocked task is saved to txt

        // test
        parser.handleMark(input, taskList, ui);

        // assert

        // check if error UI was displayed
        verify(ui).displayError("Hoshi doesn't have such a task!");

    }

    /**
     * Tests the success case of handleUnmark function
     */
    @Test
    public void handleUnmarkTest_unmarkCompleted_success() {

        // prepare mocked objects/behaviour and input

        Task mockedTask = mock(Task.class);

        String input = "unmark 1";

        // when taskList.size() is called, return 1
        when(taskList.size()).thenReturn(1);

        // when taskList.get(0) is called, return mocked task
        when(taskList.get(0)).thenReturn(mockedTask);

        // test
        parser.handleUnmark(input, taskList, ui);

        // assert

        // check if task was marked
        verify(mockedTask).setIsDone(false);

        // check if UI was displayed
        verify(ui).displayTaskUnmarked(mockedTask);

    }

    /**
     * Tests the invalidIndex case of handleUnmark function
     */
    @Test
    public void handleUnMarkTest_invalidIndex_errorDisplayed() {

        // prepare mocked objects/behaviour and input

        String input = "unmark 2";

        // when taskList.size() is called, return 1
        when(taskList.size()).thenReturn(1);

        // test
        parser.handleUnmark(input, taskList, ui);

        // assert

        // check if error UI was displayed
        verify(ui).displayError("Hoshi doesn't have such a task!");

    }

    /**
     * Tests the success case of handleDelete function
     */
    @Test
    public void handleDeleteTest_deleteCompleted_success() {

        // prepare mocked objects/behaviour and input

        Task mockedTask = mock(Task.class);
        String input = "delete 1";

        // when taskList.size() is called, return 1
        when(taskList.size()).thenReturn(1);

        // when taskList.get(0) is called, return mocked task
        when(taskList.get(0)).thenReturn(mockedTask);

        // test
        parser.handleDelete(input, taskList, ui);

        // assert

        // check if taskList deleted the object
        verify(taskList).delete(0);

        // check if success UI was displayed
        verify(ui).displayTaskDeleted(mockedTask);

    }

    /**
     * Tests the invalidIndex case of handleDelete function
     */
    @Test
    public void handleDeleteTest_invalidIndex_errorDisplayed() {

        // prepare mocked objects/behaviour and input

        String input = "delete 2";

        // when taskList.size() is called, return 1
        when(taskList.size()).thenReturn(1);

        // test
        parser.handleDelete(input, taskList, ui);

        // assert

        // check if success UI was displayed
        verify(ui).displayError("Hoshi doesn't have such a task!");

    }

    /**
     * Tests the success case of handleAdd function for todo class
     */
    @Test
    public void handleAddTest_addCompleted_success() {

        // prepare mocked objects/behaviour and input
        String input = "add todo description";

        // Mock the parser class to instruct handleSave to do nothing later on
        Parser parser = Mockito.spy(new Parser());

        // Tell handleSave to do nothing during this JUnit test
        Mockito.doNothing().when(parser).handleSave(Mockito.any(Ui.class), Mockito.any(TaskList.class));

        // test
        parser.handleAdd(input, taskList, ui);

        // assert
        verify(taskList).add(Mockito.any(Todo.class));
        verify(ui).displayTaskAdded("description");

    }

    /**
     * Tests the emptyDescription case of handleMark function
     */
    @Test
    public void handleAddTest_emptyDescription_errorDisplayed() {

        // prepare mocked objects/behaviour and input

        String input = "add todo";

        // test
        parser.handleAdd(input, taskList, ui);

        // assert
        verify(ui).displayError("Hoshi needs a task description!");


    }

}
