package streams.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StreamsExceptionTest {
    @Test
    void testStreamExceptionMessage() {
        String errorMessage = "Test error message";
        StreamsException exception = new StreamsException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}