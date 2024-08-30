package bmo.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void saveFormatTest() {
        Task task = new Event("Public Garden", "28/09/2024", "29/09/2024");
        assertEquals(task.getSavedFormat(),
            "E | 0 | Public Garden | 28/09/2024 | 29/09/2024\n" );
    }
}
