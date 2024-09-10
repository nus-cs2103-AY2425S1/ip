package bob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toDoTaskExportTest() {
        ToDo toDo = new ToDo("Test");
        assertEquals("todo false Test", toDo.export());

        ToDo toDoMarked = new ToDo("Test");
        toDoMarked.mark();
        assertEquals("todo true Test", toDoMarked.export());
    }

    @Test
    public void toDoToStringTest() {
        ToDo toDo = new ToDo("Test");
        assertEquals("[T][ ] Test", toDo.toString());

        ToDo toDoMarked = new ToDo("Test");
        toDoMarked.mark();
        assertEquals("[T][X] Test", toDoMarked.toString());
    }

}
