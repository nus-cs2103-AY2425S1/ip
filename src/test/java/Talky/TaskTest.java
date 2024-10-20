package Talky;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void invalidSaveFormat() {
        Deadline test = new Deadline("name", LocalDate.of(2020, 1, 18).atStartOfDay());
        assertEquals("Deadline name 18/01/2020 0000 false", test.toSaveFormat());
    }
}