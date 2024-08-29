package cloud.util;

import cloud.exception.CloudException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeTest {

    @Test
    public void of_validDateTimeString_shouldCreateDateTimeObject() {
        String validInput = "25/12/2024 15:30";
        try {
            DateTime dateTime = DateTime.of(validInput);
            // check if dateTime is initialized
            assertNotNull(dateTime);
        } catch (CloudException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void of_invalidDateTimeString_shouldThrowException() {
        String invalidInput = "25.12.2024 15.30";
        assertThrows(CloudException.class, () -> DateTime.of(invalidInput));
    }

    @Test
    public void formatSave_validDateTimeString_returnCorrectFormat() {
        String validInput = "25/12/2024 15:30";
        try {
            DateTime dateTime = DateTime.of(validInput);
            assertEquals(validInput, dateTime.formatSave());
        } catch (CloudException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void toString_validDateTimeString_returnCorrectFormat() {
        String validInput = "25/12/2024 15:30";
        try {
            DateTime dateTime = DateTime.of(validInput);
            assertEquals("Dec 25 2024, 03:30 pm", dateTime.toString());
        } catch (CloudException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
