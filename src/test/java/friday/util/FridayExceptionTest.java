package friday.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FridayExceptionTest {

    @Test
    void exceptionMessage_isCorrect() {
        FridayException exception = new FridayException("Test message");
        assertEquals("Test message", exception.getMessage());
    }
}
