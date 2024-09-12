package meowmeow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class EventTest {
    @Test
    public void convertToFileFormat_success() throws Exception {
        assertEquals("E | 0 | trip | 2024-10-12 | 2024-10-28", new Event("trip", "2024-10-12", "2024-10-28").convertToFileFormat());
    }

    @Test
    public void convertToFileFormat_wrongDateFormat_exceptionThrown() {
        try {
            assertEquals("E | 0 | trip | 2024/10/12 | 2024/10/28", new Event("trip", "2024/10/12", "2024/10/28").convertToFileFormat());
            fail();
        } catch (Exception e) {
            assertEquals("Text '2024/10/12' could not be parsed at index 4", e.getMessage());
        }
    }

}
