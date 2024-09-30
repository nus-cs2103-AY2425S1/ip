package neko.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import neko.NekoException;

public class TaskListTest {

    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList(new ArrayList<>());
    }

    @Test
    public void testAddTask_validTodoTask_success() throws NekoException {
        Task todo = new Todo("Sample Todo");
        tasks.addTask(todo);
        assertEquals(1, tasks.size());
        assertEquals(todo, tasks.getTask(0));
    }

    @Test
    public void testAddTask_nullTask_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> tasks.addTask(null));
    }

    @Test
    public void testGetTask_validIndex_success() throws NekoException {
        Task todo = new Todo("Sample Todo");
        tasks.addTask(todo);
        assertEquals(todo, tasks.getTask(0));
    }

    @Test
    public void testGetTask_invalidIndex_throwsException() {
        assertThrows(NekoException.class, () -> tasks.getTask(1));
    }

    @Test
    public void testListTasks_emptyList_returnsMessage() {
        String expectedMessage = "You don't have any tasks yet meow!";
        assertEquals(expectedMessage, tasks.listTasks());
    }

    @Test
    public void testListTasks_nonEmptyList_returnsTaskList() {
        Task todo = new Todo("Sample Todo");
        tasks.addTask(todo);
        String expectedMessage = "Here are the tasks in your list meow:\n1. [T][ ] Sample Todo";
        assertEquals(expectedMessage, tasks.listTasks());
    }

    @Test
    public void testMarkTask_validIndices_marksAsDone() throws NekoException {
        Task todo1 = new Todo("Task 1");
        Task todo2 = new Todo("Task 2");
        tasks.addTask(todo1);
        tasks.addTask(todo2);

        Task[] markedTasks = tasks.markTask(0, 1);
        assertTrue(markedTasks[0].isDone());
        assertTrue(markedTasks[1].isDone());
    }

    @Test
    public void testMarkTask_invalidIndex_throwsException() {
        assertThrows(NekoException.class, () -> tasks.markTask(1));
    }

    @Test
    public void testUnmarkTask_validIndices_marksAsNotDone() throws NekoException {
        Task todo = new Todo("Task 1");
        todo.markAsDone();
        tasks.addTask(todo);

        Task[] unmarkedTasks = tasks.unmarkTask(0);
        assertTrue(!unmarkedTasks[0].isDone());
    }

    @Test
    public void testUnmarkTask_invalidIndex_throwsException() {
        assertThrows(NekoException.class, () -> tasks.unmarkTask(1));
    }

    @Test
    public void testDeleteTask_validIndices_deletesTasks() throws NekoException {
        Task todo1 = new Todo("Task 1");
        Task todo2 = new Todo("Task 2");
        tasks.addTask(todo1);
        tasks.addTask(todo2);

        Task[] deletedTasks = tasks.deleteTask(0, 1);
        assertEquals(0, tasks.size());
        assertEquals(todo2, deletedTasks[0]); // delete the task with greater index first
        assertEquals(todo1, deletedTasks[1]);
    }

    @Test
    public void testDeleteTask_invalidIndex_throwsException() {
        assertThrows(NekoException.class, () -> tasks.deleteTask(1));
    }

    @Test
    public void testFindTasks_validKey_findsMatchingTasks() throws NekoException {
        Task todo1 = new Todo("Buy milk");
        Task todo2 = new Todo("Do homework");
        tasks.addTask(todo1);
        tasks.addTask(todo2);

        String result = tasks.findTasks("milk");
        String expected = "1.[T][ ] Buy milk";
        assertEquals(expected, result);
    }

    @Test
    public void testFindTasks_noMatchingTasks_returnsEmptyString() throws NekoException {
        Task todo1 = new Todo("Buy milk");
        tasks.addTask(todo1);

        String result = tasks.findTasks("bread");
        assertEquals("", result);
    }

    @Test
    public void testFindTasks_emptyKeyword_throwsException() {
        assertThrows(NekoException.class, () -> tasks.findTasks(""));
    }

    @Test
    public void testCheckValidIndex_validIndex_doesNotThrow() throws NekoException {
        Task todo = new Todo("Task 1");
        tasks.addTask(todo);
        tasks.checkValidIndex(0); // Should not throw any exceptions
    }

    @Test
    public void testCheckValidIndex_invalidIndex_throwsException() {
        assertThrows(NekoException.class, () -> tasks.checkValidIndex(1));
    }
}
