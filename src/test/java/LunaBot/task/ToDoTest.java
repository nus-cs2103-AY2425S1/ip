package LunaBot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToDoCreation() {
        ToDo todo = new ToDo("eat");
        assertEquals("eat", todo.getDescription());
    }

    @Test
    public void testToDoCreationIsDone() {
        ToDo todo = new ToDo("clean the house", true);
        assertEquals("clean the house", todo.getDescription());
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void testToString() {
        ToDo todo = new ToDo("fix phone");
        assertEquals("[T][ ] fix phone", todo.toString());

        ToDo completedTodo = new ToDo("read book", true);
        assertEquals("[T][X] read book", completedTodo.toString());
    }

    @Test
    public void testToFileFormat() {
        ToDo todo = new ToDo("fix phone");
        assertEquals("T | 0 | fix phone", todo.toFileFormat());

        ToDo completedTodo = new ToDo("read book", true);
        assertEquals("T | 1 | read book", completedTodo.toFileFormat());
    }
}
