package snowy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void toString_normalName_success() {
        assertEquals("[E][ ] Orientation Camp (from September 11, 2024 to: September 12, 2024)",
                new Event("Orientation Camp", "2024-09-11", "2024-09-12").toString());
    }

    @Test
    public void toFileStorage_normalName_success() {
        assertEquals("[E][ ] Orientation Camp (from September 11, 2024 to: September 12, 2024)",
                new Event("Orientation Camp", "2024-09-11", "2024-09-12").toString());
    }

    @Test void constructor_invalidDate_exceptionThrown() {
        try {
            new Event("Return book", "12-123-1234", "12-123-1234");
            fail();
        } catch (SnowyException e) {
            assertEquals("Wrong date format", e.getMessage());
        }
    }
}
