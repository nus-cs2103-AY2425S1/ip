package jbot.task;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"UnqualifiedFieldAccess", "UnqualifiedStaticUsage"})
public class DeadlineTaskTest {

    private static final String VALID_INPUT = "deadline return book /by 2/12/2020 1900";
    private static final String INVALID_INPUT = "deadline /by 2/12/2019 25:00";
    private static final String VALID_NAME = "return book";
    private static final String VALID_DEADLINE_STRING = "2020-12-02T19:00";
    private static final String INVALID_DEADLINE_STRING = "invalid-date";

    private DeadlineTask validTask;
    private DeadlineTask validTaskForDeserialization;

    @BeforeEach
    public void setUp() {
        validTask = new DeadlineTask(VALID_INPUT);
        validTaskForDeserialization = new DeadlineTask(VALID_NAME, VALID_DEADLINE_STRING);
    }

    @Test
    public void constructor_validInput_correctDeadlineParsed() {
        assertNotNull(validTask);
        assertEquals(VALID_NAME, validTask.getName());
        assertNotNull(validTask.getDeadline());
        assertEquals(LocalDateTime.of(2020, 12, 2, 19, 0), validTask.getDeadline());
    }

    @Test
    public void constructor_invalidInput_printsError() {
        // The constructor prints error messages rather than throwing exceptions
        // Here, we are testing that an invalid input does not throw an uncaught exception
        assertDoesNotThrow(() -> new DeadlineTask(INVALID_INPUT));
    }

    @Test
    public void constructor_forDeserialization_correctDeadlineParsed() {
        assertNotNull(validTaskForDeserialization);
        assertEquals(VALID_NAME, validTaskForDeserialization.getName());
        assertEquals(LocalDateTime.parse(VALID_DEADLINE_STRING), validTaskForDeserialization.getDeadline());
    }

    @Test
    public void toString_validDeadline_correctFormattedString() {
        String expectedOutput = String.format("[D] [ ]  %s (by: Dec 02 2020, 7:00 pm)", VALID_NAME);
        assertEquals(expectedOutput, validTask.toString());
    }

    @Test
    public void getDeadlineAsString_validDeadline_correctIsoFormat() {
        assertEquals(VALID_DEADLINE_STRING, validTaskForDeserialization.getDeadlineAsString());
    }

    @Test
    public void getDeadlineAsString_invalidDeadline_throwsException() {
        assertThrows(DateTimeParseException.class, () -> {
            new DeadlineTask(VALID_NAME, INVALID_DEADLINE_STRING);
        });
    }
}