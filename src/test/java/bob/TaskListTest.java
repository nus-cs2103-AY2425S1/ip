package bob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void testAddTodo() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        try {
            Task todo = new ToDo("test task1", TaskType.TODO);
            taskList.addToDo("todo " + todo.getDescription(), ui);
            assertEquals(1, taskList.size());
            assertEquals(todo.getDescription(), taskList.getTask(0).getDescription());
        } catch (ChatBotException e){
            ui.showError(e.getMessage());
        }
    }

    @Test
    public void testMarkTask() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        try {
            Task todo = new ToDo("test task1", TaskType.TODO);
            taskList.addToDo("todo " + todo.getDescription(), ui);
            taskList.markTask(0, ui);
            assertTrue(taskList.getTask(0).isDone());
        } catch (ChatBotException e){
            ui.showError(e.getMessage());
        }
    }
}
