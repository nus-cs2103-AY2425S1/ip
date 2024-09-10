package MeowMeow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class EventTest {
    @Test
    public void toFileFormat_success() throws Exception {
        assertEquals("E | 0 | trip | 2024-10-12 | 2024-10-28", new Event("trip", "2024-10-12", "2024-10-28").toFileFormat());
    }

    @Test
    public void toFileFormat_wrongDateFormat_exceptionThrown() {
        try {
            assertEquals("E | 0 | trip | 2024/10/12 | 2024/10/28", new Event("trip", "2024/10/12", "2024/10/28").toFileFormat());
            fail();
        } catch (Exception e) {
            assertEquals("Text '2024/10/12' could not be parsed at index 4", e.getMessage());
        }
    }

}
