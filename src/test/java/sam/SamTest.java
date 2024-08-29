package sam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SamTest {
    @Test
    public void markItemDone() {
        Item item = new Item("task");
        item.markAsDone();
        assertEquals("X", item.getStatusIcon());
    }
}
