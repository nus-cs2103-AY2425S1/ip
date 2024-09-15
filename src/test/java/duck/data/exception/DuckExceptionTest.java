package duck.data.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the DuckException class.
 */
public class DuckExceptionTest {

    /**
     * Tests the DuckException class with a message.
     */
    @Test
    public void testDuckExceptionMessage() {
        DuckException de = new DuckException("Test error message");
        assertEquals("Test error message", de.getMessage(), "Exception message should match.");
    }
}

