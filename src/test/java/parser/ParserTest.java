package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import exceptions.EmptyTaskException;
import exceptions.InvalidInputException;
import exceptions.TaskIndexOutOfBound;
import task.Todo;
import storage.StorageStub;
import task.TaskListStub;
import ui.UiStub;

public class ParserTest {

    private UiStub ui;
    private TaskListStub taskList;
    private StorageStub storage;

    @BeforeEach
    public void setUp() {
        Todo todo = new Todo();
        todo.convertStringToTask(new String[]{"todo", "finish homework"});
        taskList = new TaskListStub(new ArrayList<>());
        taskList.addTask(todo);
        ui = new UiStub();
        storage = new StorageStub();
    }

    @Test
    public void testMarkCommandWithInitialTask() throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
        Parser.parseCommand("mark 1", taskList, ui, storage);
        assertTrue(taskList.getTask(0).getIsDone(), "The first task should be marked as done");
        assertEquals("showMarkTask called for task: finish homework", ui.lastOutput);
    }

    @Test
    void testTodoCommandWithStorage() throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
        Parser.parseCommand("todo read book", taskList, ui, storage);

        assertTrue(storage.getIsSaveTasksCalled(), "saveTasks should be called");
        assertEquals(2, storage.getSavedTasks().size(), "There should be two tasks saved");
        assertEquals("read book", storage.getSavedTasks().get(1).toString(),
                "The second task description should be 'read book'");
    }
}
