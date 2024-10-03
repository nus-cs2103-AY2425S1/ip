package duck.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class EventTest {

    private static final LocalDateTime INCOMPLETE_FROM =
            LocalDateTime.of(2021, 9, 30, 23, 58);
    private static final LocalDateTime INCOMPLETE_TO =
            LocalDateTime.of(2021, 10, 25, 23, 59);



    private static final LocalDateTime COMPLETE_FROM =
            LocalDateTime.of(2022, 12, 31, 23, 58);
    private static final LocalDateTime COMPLETE_TO =
            LocalDateTime.of(2023, 1, 1, 23, 59);

    private static final LocalDate DUMMY_DATE =
            LocalDate.of(2022, 12, 30);

    private static final String INCOMPLETE_DESCRIPTION = "incomplete";
    private static final String COMPLETE_DESCRIPTION = "complete";

    private static final String EXPECTED_TO_STRING_INCOMPLETE_EVENT =
            "[E][ ] " + INCOMPLETE_DESCRIPTION + " (from: Sep 30 2021 23:58 to: Oct 25 2021 23:59)";
    private static final String EXPECTED_FILE_FORMAT_INCOMPLETE_EVENT =
            "E | 0 | " + INCOMPLETE_DESCRIPTION + " | 2021-09-30 2358 | 2021-10-25 2359";
    private static final String EXPECTED_TO_STRING_COMPLETE_EVENT =
            "[E][X] " + COMPLETE_DESCRIPTION + " (from: Dec 31 2022 23:58 to: Jan 01 2023 23:59)";
    private static final String EXPECTED_FILE_FORMAT_COMPLETE_EVENT =
            "E | 1 | " + COMPLETE_DESCRIPTION + " | 2022-12-31 2358 | 2023-01-01 2359";

    private Event incompleteEvent;
    private Event completeEvent;

    /**
     * Set up the event for testing.
     */
    @BeforeEach
    void setUp() {
        incompleteEvent = new Event(INCOMPLETE_DESCRIPTION, INCOMPLETE_FROM, INCOMPLETE_TO);
        completeEvent = new Event(true, COMPLETE_DESCRIPTION, COMPLETE_FROM, COMPLETE_TO);
    }

    /**
     * Test if the description of the event is correct.
     */
    @Test
    public void getFrom() {
        assertEquals(INCOMPLETE_FROM, incompleteEvent.getFrom());
        assertEquals(INCOMPLETE_TO, incompleteEvent.getTo());

        assertEquals(COMPLETE_FROM, completeEvent.getFrom());
        assertEquals(COMPLETE_TO, completeEvent.getTo());
    }

    /**
     * Test if the event is starting before the given date.
     */
    @Test
    public void getTo() {
        assertEquals(INCOMPLETE_FROM, incompleteEvent.getFrom());
        assertEquals(INCOMPLETE_TO, incompleteEvent.getTo());

        assertEquals(COMPLETE_FROM, completeEvent.getFrom());
        assertEquals(COMPLETE_TO, completeEvent.getTo());
    }

    /**
     * Test if the event is starting before the given date.
     */
    @Test
    public void isStartingBefore() {
        assertTrue(incompleteEvent.isStartingBefore(DUMMY_DATE));
        assertFalse(completeEvent.isStartingBefore(DUMMY_DATE));
    }

    /**
     * Test if the event is ending after the given date.
     */
    @Test
    public void isEndingAfter() {
        assertFalse(incompleteEvent.isEndingAfter(DUMMY_DATE));
        assertTrue(completeEvent.isEndingAfter(DUMMY_DATE));
    }

    /**
     * Test if the file format of the event is correct.
     */
    @Test
    public void toFileFormat() {
        assertEquals(EXPECTED_FILE_FORMAT_INCOMPLETE_EVENT, incompleteEvent.toFileFormat());
        assertEquals(EXPECTED_FILE_FORMAT_COMPLETE_EVENT, completeEvent.toFileFormat());
    }

    /**
     * Test if the string representation of the event is correct.
     */
    @Test
    public void testToString() {
        assertEquals(EXPECTED_TO_STRING_INCOMPLETE_EVENT, incompleteEvent.toString());
        assertEquals(EXPECTED_TO_STRING_COMPLETE_EVENT, completeEvent.toString());
    }
}
