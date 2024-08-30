package bmo.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void saveFormatTest() {
        Task task = new Deadline("2102 Tutorial", "05/09/2024");
        assertEquals(task.getSavedFormat(),
            "D | 0 | 2102 Tutorial | 05/09/2024\n" );
    }
}
