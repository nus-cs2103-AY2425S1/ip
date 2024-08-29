package Tasks;

import Exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void dummyTest() {
        try {
            assertEquals("[E][ ] read book (from: 4pm to: 6pm)", new Events("read book /from 4pm /to 6pm").print());
        } catch (EmptyEventTimingException e) {
            assertEquals("     OOPS! Event start time not given leh", e.getMessage());
        } catch (EmptyEventException e) {
            assertEquals("      OOPS!!! The description of a event cannot be empty leh.", e.getMessage());
        }
    }
}