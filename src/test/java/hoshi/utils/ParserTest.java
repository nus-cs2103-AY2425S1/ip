package hoshi.utils;

import hoshi.Hoshi;
import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.task.Todo;
import hoshi.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Parser test class for JUnit testing of Parser class
 */
public class ParserTest {

    private TaskList taskList;
    private Ui ui;
    private Parser parser;


    @BeforeEach
    void setUp() {
        taskList = mock(TaskList.class);
        ui = mock(Ui.class);
        parser = new Parser();
    }

    @Test
    public void handleMarkTest_success(){

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

    @Test
    public void handleMarkTest_invalidIndex(){

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

    @Test
    public void handleUnmarkTest_success(){

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

    @Test
    public void handleUnMarkTest_invalidIndex(){

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

    @Test
    public void handleDeleteTest_success(){

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

    @Test
    public void handleDeleteTest_invalidIndex(){

        // prepare mocked objects/behaviour and input

        Task mockedTask = mock(Task.class);
        String input = "delete 2";

        // when taskList.size() is called, return 1
        when(taskList.size()).thenReturn(1);

        // test
        parser.handleDelete(input, taskList, ui);

        // assert

        // check if success UI was displayed
        verify(ui).displayError("Hoshi doesn't have such a task!");

    }

    @Test
    public void handleAddTest_success(){

        // prepare mocked objects/behaviour and input

        String input = "add todo";

        Scanner scanner = new Scanner("Test Todo Description"); // Simulating user input

        // test

        parser.handleAdd(input, scanner, taskList, ui);

        // assert

        verify(taskList).add(Mockito.any(Todo.class));
        verify(ui).displayTodoTask();

    }

    @Test
    public void handleAddTest_emptyDescription(){

        // prepare mocked objects/behaviour and input

        String input = "add todo";

        Scanner scanner = new Scanner("\n"); // Simulating empty user input after the next line

        // test

        parser.handleAdd(input, scanner, taskList, ui);

        // assert

        verify(ui).displayTodoTask();
        verify(ui).displayError("Hoshi doesn't understand! Is input empty? \n");


    }

}
