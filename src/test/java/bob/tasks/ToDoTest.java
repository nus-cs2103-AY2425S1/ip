package bob.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void EventTaskExportTest() {
        ToDo toDo = new ToDo("Test");
        assertEquals("todo false Test", toDo.export());

        ToDo toDoMarked = new ToDo("Test");
        toDoMarked.mark();
        assertEquals("todo true Test", toDoMarked.export());
    }

    @Test
    public void DeadlineToStringTest() {
        ToDo toDo = new ToDo("Test");
        assertEquals("[T][ ] Test", toDo.toString());

        ToDo toDoMarked = new ToDo("Test");
        toDoMarked.mark();
        assertEquals("[T][X] Test", toDoMarked.toString());
    }

}
