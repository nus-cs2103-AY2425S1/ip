package snowy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void toString_normalName_success() {
        assertEquals("[D][ ] Return Book (by September 11, 2024)",
                new Deadline("Return Book", "2024-09-11").toString());
    }

    @Test
    public void toFileStorage_normalName_success() {
        assertEquals("D|0|Return Book|2024-09-11",
                new Deadline("Return Book", "2024-09-11").toFileStorage());
    }

    @Test void constructor_invalidDate_exceptionThrown() {
        try {
            new Deadline("Return book", "12-123-1234");
            fail();
        } catch (SnowyException e) {
            assertEquals("Wrong date format", e.getMessage());
        }
    }
}
