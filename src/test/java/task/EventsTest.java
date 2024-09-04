package task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import exception.CommandFoundButInvalidException;
public class EventsTest {
    @Test
    public void throwExceptionTest() {
        boolean thrown = false;
        try {
            new Events("This is just a random String /start");
        } catch (CommandFoundButInvalidException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}
