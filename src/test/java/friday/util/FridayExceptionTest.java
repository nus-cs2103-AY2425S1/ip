package friday.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FridayExceptionTest {

    @Test
    void exceptionMessage_isCorrect() {
        FridayException exception = new FridayException("Test message");
        Assertions.assertEquals("Test message", exception.getMessage());
    }
}
