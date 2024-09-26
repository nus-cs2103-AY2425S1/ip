package stobberi.stobberiexception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StobberiExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Test with a sample message
        String message = "Something went wrong.";
        StobberiException exception = new StobberiException(message);

        // Check if the message is correctly formatted with the prefix
        String expectedMessage = "UMmmmmm, I'm weallly sowwyyy!!!!\n\n" + message;
        assertEquals(expectedMessage, exception.getMessage(), "The exception message should be correctly prefixed.");
    }

    @Test
    public void testEmptyMessage() {
        // Test with an empty message
        String message = "";
        StobberiException exception = new StobberiException(message);

        // Check if the prefix is still added even when the message is empty
        String expectedMessage = "UMmmmmm, I'm weallly sowwyyy!!!!\n\n";
        assertEquals(expectedMessage, exception.getMessage(),
                "The exception message should only contain the prefix for empty input.");
    }

    @Test
    public void testNullMessage() {
        // Test with a null message
        String message = null;
        StobberiException exception = new StobberiException(message);

        // Check if the message is "null" after being prefixed
        String expectedMessage = "UMmmmmm, I'm weallly sowwyyy!!!!\n\nnull";
        assertEquals(expectedMessage, exception.getMessage(),
                "The exception message should correctly handle null values.");
    }

    @Test
    public void testLongMessage() {
        // Test with a longer message
        String message = "This is a very long message to check the exception's handling of long input messages.";
        StobberiException exception = new StobberiException(message);

        // Check if the message is correctly prefixed
        String expectedMessage = "UMmmmmm, I'm weallly sowwyyy!!!!\n\n" + message;
        assertEquals(expectedMessage, exception.getMessage(),
                "The exception message should handle long messages correctly.");
    }

    @Test
    public void testMessageWithSpecialCharacters() {
        // Test with a message containing special characters
        String message = "Error! Something went wrong with @, #, and $.";
        StobberiException exception = new StobberiException(message);

        // Check if the message with special characters is correctly prefixed
        String expectedMessage = "UMmmmmm, I'm weallly sowwyyy!!!!\n\n" + message;
        assertEquals(expectedMessage, exception.getMessage(),
                "The exception message should handle special characters correctly.");
    }
}
