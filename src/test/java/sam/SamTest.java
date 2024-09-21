package sam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SamTest {
    /**
     * Test case for the markItemDone method in the Item class.
     * It verifies that the status icon of an item is correctly updated to "X" when marked as done.
     */
    @Test
    public void markItemDone_sam_success() {
        Item item = new Item("task");
        item.markAsDone();
        assertEquals("X", item.getStatusIcon());
    }
}
