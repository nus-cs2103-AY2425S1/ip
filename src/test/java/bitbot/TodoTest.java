package bitbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Unit test for Todo class
 */
public class TodoTest {

    private Task toDos = new Todo("Do Homework");

    @Test
    public void correctlySetsStatus() {
        assertFalse(toDos.isDone, "Task should be initially marked as not done");
    }

    @Test
    public void finalString_unmarkedTask_returnsCorrectString() {
        assertEquals("[T][ ] Do Homework", toDos.finalString());
    }

    @Test
    public void finalString_markedTask_returnsCorrectString() {
        toDos.markAsDone();
        assertEquals("[T][X] Do Homework", toDos.finalString());
    }

    @Test
    public void toFileFormat_withDescription() {
        assertEquals("T| |Do Homework|NIL|NIL|NIL", toDos.toFileFormat());
    }
}
