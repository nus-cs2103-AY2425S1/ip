package exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for the ScheduloException class.
 */
public class ScheduloExceptionTest {

    /**
     * Tests that ScheduloException is thrown correctly with a custom message.
     */
    @Test
    public void testScheduloException() {
        assertThrows(ScheduloException.class, () -> {
            throw new ScheduloException("This is a custom exception");
        });
    }
}
