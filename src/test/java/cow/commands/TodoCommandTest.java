package cow.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

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
        Ui ui = new Ui();
        todo.execute(td, ui, fs);
        assertEquals(td.toString(), "1.[T][ ] test");
    }

    @Test
    public void executeEmptyDescriptionTest() throws CowExceptions {
        TodoList td = new TodoList();

        TodoCommand todo = new TodoCommand("");
        FileSaver fs = new FileSaver("data/cow.txt");
        Ui ui = new Ui();
        todo.execute(td, ui, fs);
        assertEquals(td.toString(), "1.[T][ ] ");
    }

    @Test
    public void executeWithExceptionTest() {
        TodoList td = new TodoList();

        TodoCommand todo = new TodoCommand("test");
        FileSaver fs = new FileSaver("invalid/path/cow.txt");
        Ui ui = new Ui();
        assertThrows(CowExceptions.class, () -> {
            todo.execute(td, ui, fs);
        });
    }
}
