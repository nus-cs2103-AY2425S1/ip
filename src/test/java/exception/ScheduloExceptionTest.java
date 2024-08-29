package exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScheduloExceptionTest {

    @Test
    public void testScheduloException() {
        assertThrows(ScheduloException.class, () -> {
            throw new ScheduloException("This is a custom exception");
        });
    }
}
