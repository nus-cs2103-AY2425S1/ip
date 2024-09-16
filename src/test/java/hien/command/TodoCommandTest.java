package hien.command;
import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TodoCommandTest {
    @Test
    public void testValidTodoCommand() throws HienException{
        TaskList taskList = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage("data/test/test_todo.txt");
        String input = "todo Read a book";
        TodoCommand command = new TodoCommand(input, false);
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size(), "TaskList should contain 1 task.");
        assertEquals("Read a book", taskList.getTask(0).getDescription(), "Task description should match.");
    }
    @Test
    public void testInValidTodoCommand() {
        TaskList taskList = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage("data/test/test_todo.txt");
        String input = "todo";
        TodoCommand command = new TodoCommand(input, false);
        Exception exception = assertThrows(HienException.class, () -> command.execute(taskList, ui, storage));
        assertEquals("☹ OOPS!!! The description of todo cannot be empty", exception.getMessage());
    }

}
