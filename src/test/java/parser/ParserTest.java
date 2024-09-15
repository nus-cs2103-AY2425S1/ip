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

public class ParserTest {

    private TaskListStub taskList;
    private StorageStub storage;

    @BeforeEach
    public void setUp() {
        Todo todo = new Todo();
        todo.convertStringToTask(new String[]{"todo", "finish homework"});
        taskList = new TaskListStub(new ArrayList<>());
        taskList.addTask(todo);
        storage = new StorageStub();
    }

    @Test
    public void testMarkCommandWithInitialTask() throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
        String response = Parser.parseUserCommand("mark 1", taskList, storage);
        assertTrue(taskList.getTask(0).getIsDone(), "The first task should be marked as done");
        assertEquals("OK, I've marked this task as done:\n[T][X] finish homework", response);
    }

    @Test
    void testTodoCommandWithStorage() throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
        String response = Parser.parseUserCommand("todo read book", taskList, storage);

        assertTrue(storage.getIsSaveTasksCalled(), "saveTasks should be called");
        assertEquals(2, storage.getSavedTasks().size(), "There should be two tasks saved");
        assertEquals("Got it. I've added this task:\n[T][ ] read book\nNow you have 2 tasks in the list", response);
    }

    @Test
    void testUnmarkCommand() throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
        String response = Parser.parseUserCommand("unmark 1", taskList, storage);
        assertTrue(!taskList.getTask(0).getIsDone(), "The first task should be marked as not done");
        assertEquals("OK, I've marked this task as not done yet:\n[T][ ] finish homework", response);
    }

    @Test
    void testDeleteCommand() throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
        String response = Parser.parseUserCommand("delete 1", taskList, storage);

        assertTrue(storage.getIsSaveTasksCalled(), "saveTasks should be called after delete");
        assertEquals(0, taskList.getTasks().size(), "There should be no tasks left in the list");
        assertEquals("Noted. I've removed this task:\n[T][ ] finish homework\nNow you have 0 tasks in the list", response);
    }
}
