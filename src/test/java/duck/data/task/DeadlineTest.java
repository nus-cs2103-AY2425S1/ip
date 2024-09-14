package duck.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class DeadlineTest {

    private static final LocalDateTime INCOMPLETE_BY = LocalDateTime.of(2021, 9, 30, 23, 59);
    private static final LocalDateTime COMPLETE_BY = LocalDateTime.of(2022, 1, 1, 23, 59);
    private static final String INCOMPLETE_DESCRIPTION = "incomplete";
    private static final String COMPLETE_DESCRIPTION = "complete";
    private static final String EXPECTED_TO_STRING_INCOMPLETE_DEADLINE =
            "[D][ ] " + INCOMPLETE_DESCRIPTION + " (by: Sep 30 2021 23:59)";
    private static final String EXPECTED_FILE_FORMAT_INCOMPLETE_DEADLINE =
            "D | 0 | " + INCOMPLETE_DESCRIPTION + " | 2021-09-30 2359";
    private static final String EXPECTED_TO_STRING_COMPLETE_DEADLINE =
            "[D][X] " + COMPLETE_DESCRIPTION + " (by: Jan 01 2022 23:59)";
    private static final String EXPECTED_FILE_FORMAT_COMPLETE_DEADLINE =
            "D | 1 | " + COMPLETE_DESCRIPTION + " | 2022-01-01 2359";

    private Deadline incompleteDeadline;
    private Deadline completeDeadline;

    /**
     * Set up the deadline for testing.
     */
    @BeforeEach
    public void setUp() {
        incompleteDeadline = new Deadline(INCOMPLETE_DESCRIPTION, INCOMPLETE_BY);
        completeDeadline = new Deadline(true, COMPLETE_DESCRIPTION, COMPLETE_BY);
    }

    /**
     * Test if the description of the deadline is correct.
     */
    @Test
    public void getBy() {
        assertEquals(INCOMPLETE_BY, incompleteDeadline.getBy());
        assertEquals(COMPLETE_BY, completeDeadline.getBy());
    }

    /**
     * Test if the deadline is on the given date.
     */
    @Test
    public void isOn() {
        assertTrue(incompleteDeadline.isOn(LocalDate.from(INCOMPLETE_BY)));
        assertTrue(completeDeadline.isOn(LocalDate.from(COMPLETE_BY)));
    }

    /**
     * Test if the file format of the deadline is correct.
     */
    @Test
    public void toFileFormat() {

        assertEquals(EXPECTED_FILE_FORMAT_INCOMPLETE_DEADLINE, incompleteDeadline.toFileFormat());
        assertEquals(EXPECTED_FILE_FORMAT_COMPLETE_DEADLINE, completeDeadline.toFileFormat());
    }

    /**
     * Test if the string representation of the deadline is correct.
     */
    @Test
    public void testToString() {
        assertEquals(EXPECTED_TO_STRING_INCOMPLETE_DEADLINE, incompleteDeadline.toString());
        assertEquals(EXPECTED_TO_STRING_COMPLETE_DEADLINE, completeDeadline.toString());
    }
}
