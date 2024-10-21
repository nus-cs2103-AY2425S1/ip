package kietwoforone.tasks;

import kietwoforone.exceptions.KieTwoForOneException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    Event currEvent = new Event("read book", "from 2024-06-21 1800", "to 2024-06-22 1800");
    String incorrectFormat = "21-06-2024";

    @Test
    public void compareDate_exceptionThrown() {
        try {
            assertEquals("", currEvent.compareDate(incorrectFormat));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("Date must be valid and in the form YYYY-MM-DD!", e.getMessage());
        }
    }

}
