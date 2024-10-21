package kietwoforone.tasks;

import kietwoforone.KieTwoForOne;
import kietwoforone.exceptions.KieTwoForOneException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    Deadline currDeadline = new Deadline("read book", "by 2024-06-21 1800");
    String incorrectFormat = "21-06-2024";

    @Test
    public void compareDate_exceptionThrown() {
        try {
            assertEquals("", currDeadline.compareDate(incorrectFormat));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("Date must be valid and in the form YYYY-MM-DD!", e.getMessage());
        }
    }

}
