package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void createCommandTest() {
        TodoCommand todo = new TodoCommand("test");
        assertEquals(todo.getDescription(), "test");
    }

    @Test
    public void executeCommandTest() throws CowExceptions {
        TodoList td = new TodoList();

        TodoCommand todo = new TodoCommand("test");
        FileSaver fs = new FileSaver("data/cow.txt");
        todo.execute(td, fs);
        assertEquals(td.toString(), "Here are the tasks in your list:\n" +
                "1.[T][ ] test");
    }
}
