package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link TaskList} class.
 * This class contains unit tests for the methods in the {@link TaskList} class,
 * including adding tasks and marking them as done.
 */
public class TaskListTest {

    /**
     * Tests the {@link TaskList#addToDo(String, Ui)} method.
     * Verifies that a todo task is correctly added to the task list and its
     * description matches the input provided. The test checks if the size of
     * the task list is updated accordingly and the task description is correct.
     */
    @Test
    public void testAddTodo() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        try {
            Task todo = new ToDo("test task1", TaskType.TODO);
            taskList.addToDo("todo " + todo.getDescription(), ui);
            assertEquals(1, taskList.size());
            assertEquals(todo.getDescription(), taskList.getTask(0).getDescription());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Tests the {@link TaskList#markTask(int, Ui)} method.
     * Verifies that a task is correctly marked as done in the task list. The test
     * first adds a todo task and then marks it as done. It checks if the task's
     * status is updated correctly.
     */
    @Test
    public void testMarkTask() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        try {
            Task todo = new ToDo("test task1", TaskType.TODO);
            taskList.addToDo("todo " + todo.getDescription(), ui);
            taskList.markTask(0, ui);
            assertTrue(taskList.getTask(0).isDone());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }
    }
}
