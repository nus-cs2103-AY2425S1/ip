package pixel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void constructor_readBook_correctInstantiation() {
        Deadline newDeadline = new Deadline("read book", "2023-01-01 12:00");
        assertEquals("read book", newDeadline.getDescription());
        assertEquals("2023-01-01 12:00", newDeadline.getBy());
    }

    @Test
    public void toString_readBook_correctOutput() {
        Deadline newDeadline = new Deadline("read book", "2023-01-01 12:00");
        assertEquals("[D][ ] read book (by: 2023-01-01 12:00)", newDeadline.toString());
    }

    @Test
    public void toStringDoneUndone_markAsDoneUndone_correctOutput() {
        Deadline newDeadline = new Deadline("read book", "2023-01-01 12:00");
        newDeadline.markAsDone();
        assertEquals("[D][X] read book (by: 2023-01-01 12:00)", newDeadline.toString());

        newDeadline.markAsUndone();
        assertEquals("[D][ ] read book (by: 2023-01-01 12:00)", newDeadline.toString());
    }

    @Test
    public void getFileString_readBook_correctOutput() {
        Deadline newDeadline = new Deadline("read book", "2023-01-01 12:00");
        assertEquals("D | 0 | read book | 2023-01-01 12:00", newDeadline.getFileString());
    }

    @Test
    public void getFileStringDoneUndone_markAsDoneUndone_correctOutput() {
        Deadline newDeadline = new Deadline("read book", "2023-01-01 12:00");
        newDeadline.markAsDone();
        assertEquals("D | 1 | read book | 2023-01-01 12:00", newDeadline.getFileString());

        newDeadline.markAsUndone();
        assertEquals("D | 0 | read book | 2023-01-01 12:00", newDeadline.getFileString());
    }
}
