package pixel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void constructor_readBook_correctDescription() {
        ToDo newToDo = new ToDo("read book");
        assertEquals("read book", newToDo.getDescription());
    }

    @Test
    public void toString_readBook_correctOutput() {
        ToDo newToDo = new ToDo("read book");
        assertEquals("[T][ ] read book", newToDo.toString());
    }

    @Test
    public void toStringDoneUndone_markAsDoneUndone_correctOutput() {
        ToDo newToDo = new ToDo("read book");
        newToDo.markAsDone();
        assertEquals("[T][X] read book", newToDo.toString());

        newToDo.markAsUndone();
        assertEquals("[T][ ] read book", newToDo.toString());
    }

    @Test
    public void getFileString_readBook_correctOutput() {
        ToDo newToDo = new ToDo("read book");
        assertEquals("T | 0 | read book", newToDo.getFileString());
    }

    @Test
    public void getFileStringDoneUndone_markAsDoneUndone_correctOutput() {
        ToDo newToDo = new ToDo("read book");
        newToDo.markAsDone();
        assertEquals("T | 1 | read book", newToDo.getFileString());

        newToDo.markAsUndone();
        assertEquals("T | 0 | read book", newToDo.getFileString());
    }
}
