package pikappi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pikappi.exception.PikappiException;

public class DeadlineCommandTest {
    @Test
    public void execute_validTask_success() throws PikappiException {
        assertEquals("2021-08-25 1800",
                new DeadlineCommand("deadline test task /by 2021-08-25 1800").by);
    }

    @Test
    public void execute_noDeadline_exceptionThrown() {
        try {
            assertEquals("none",
                    new DeadlineCommand("deadline test task").by);
            fail();
        } catch (PikappiException e) {
            assertEquals("Pika..? When is the deadline?", e.getMessage());
        }
    }
}
