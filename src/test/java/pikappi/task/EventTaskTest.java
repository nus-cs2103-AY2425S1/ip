package pikappi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pikappi.exception.PikappiException;

public class EventTaskTest {
    @Test
    public void getFrom_validDateTimeFormat_success() throws PikappiException {
        assertEquals("Aug 25 2021 6:00PM",
                new EventTask("test", "2021-08-25 1800", "2021-08-26 1800").getFrom());
    }

    @Test
    public void getFrom_invalidDateTimeFormat_success() throws PikappiException {
        assertEquals("today",
                new EventTask("test", "today", "tomorrow").getFrom());
    }

    @Test
    public void toString_tooManyKeywords_exceptionThrown() {
        try {
            assertEquals("[E][ ] test (from: Mar 2 2023 6:00PM to: 2024-09-09)",
                    new EventTask("test", "2024-03-02 1800", "2023-09-09").toString());
            fail();
        } catch (PikappiException e) {
            assertEquals("Pika..? Start time cannot be after end time..", e.getMessage());
        }
    }
}
