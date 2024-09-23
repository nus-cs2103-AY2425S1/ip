package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.tasks.Todo;

public class TodoTest {
    @Test
    public void createTodo_unmarked_success() throws Exception {
        // toString
        assertEquals("[T][ ] test description", new Todo("test description").toString());

        // save string
        assertEquals("T,false,test description", new Todo("test description").getSaveString());
    }

    @Test
    public void createTodo_marked_success() throws Exception {
        // toString
        Todo testTodo1 = new Todo("test description");
        testTodo1.markAsDone();
        assertEquals("[T][X] test description", testTodo1.toString());

        // save string
        Todo testTodo2 = new Todo("test description");
        testTodo2.markAsDone();
        assertEquals("T,true,test description", testTodo2.getSaveString());
    }
}
