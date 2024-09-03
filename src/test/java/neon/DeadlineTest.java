package neon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadline_toStringDone_success() {
        assertEquals("[D][ ] borrow book (by: 15th Sept)", new Deadline("borrow book", false,
                "15th Sept").toString());
    }
}
