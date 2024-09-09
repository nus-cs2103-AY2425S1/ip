package twilight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void toStorageString() {
        assertEquals("E,0,project meeting,Mon 2pm,4pm", new Event("project meeting", "Mon 2pm", "4pm").toStorageString());
    }
}
