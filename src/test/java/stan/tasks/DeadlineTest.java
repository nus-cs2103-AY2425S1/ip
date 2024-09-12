package stan.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import stan.exceptions.StanInvalidDateTimeFormatException;



class DeadlineTest {

    @Test
    void createValidDeadline() throws StanInvalidDateTimeFormatException {
        Deadline deadline = new Deadline("Submit report", "2023-08-30 2359");
        assertEquals("[D][ ] Submit report (by: Aug 30 2023, 11:59PM)", deadline.toString());
    }

    @Test
    void createDeadlineWithInvalidDateFormat() {
        Exception exception = assertThrows(StanInvalidDateTimeFormatException.class, () -> {
            new Deadline("Submit report", "30-08-2023 2359");
        });
        assertEquals("The deadline time must be in the format yyyy-MM-dd "
                + "HHmm.\nE.g. 2021-07-29 2359", exception.getMessage());
    }

    @Test
    void testDeadlineToStorageString() throws StanInvalidDateTimeFormatException {
        Deadline deadline = new Deadline("Submit report", "2023-08-30 2359");
        assertEquals("D | 0 | Submit report | 2023-08-30 2359", deadline.toStorageString());
    }
}
