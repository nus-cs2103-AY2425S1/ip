package luke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testUndoneDeadlineCreation() {
        Deadline dl = new Deadline("return book", "2/12/2020 15:00", false);
        assertEquals("[D][ ] return book (by: Dec 2 2020, 15:00)", dl.taskDescription());
        assertEquals("- | deadline | return book by Dec 2 2020, 15:00\n", dl.taskInSaveData());
    }

    @Test
    public void testDoneDeadlineCreation() {
        Deadline dl = new Deadline("return book", "2/12/2020 15:00", true);
        assertEquals("[D][X] return book (by: Dec 2 2020, 15:00)", dl.taskDescription());
        assertEquals("X | deadline | return book by Dec 2 2020, 15:00\n", dl.taskInSaveData());
    }
}
