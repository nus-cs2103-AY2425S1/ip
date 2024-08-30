package task;
import exception.CommandFoundButInvalidException;
import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDos;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventsTest {
    @Test
    public void throwExceptionTest() {
        boolean thrown = false;
        try {
            Events myEvent = new Events("This is just a random String /start");
        } catch (CommandFoundButInvalidException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}
