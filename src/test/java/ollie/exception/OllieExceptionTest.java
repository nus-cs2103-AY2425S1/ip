package ollie.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link OllieException} class.
 */
public class OllieExceptionTest {

    /**
     * Tests the {@link OllieException} constructor and {@link OllieException#getMessage()} method.
     * Verifies that the exception's message is correctly set and returned.
     */
    @Test
    public void ollieExceptionTest() {
        OllieException ollieException = new OllieException("Test Ollie Exception");
        assertEquals("Test Ollie Exception", ollieException.getMessage());
    }
}
