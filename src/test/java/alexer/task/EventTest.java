package alexer.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EventTest {
    @Test
    public void loadEvent_invalidEvent_error() {
        String taskString = "E|1|aaaaa no event details?!";
        Event event = Event.fromTaskString(taskString);
        assertNull(event);
    }

    @Test
    public void loadEvent_validEvent_created() {
        String taskString = "E|0|important activity|today|tomorrow";
        Event event = Event.fromTaskString(taskString);
        assertNotNull(event);
    }
}
