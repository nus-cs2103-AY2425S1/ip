package lawrence.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import lawrence.utils.DateParser;

public class DeadlineTest {
    @Test
    public void toString_constructorWithNoIsCompleteUsed_correctString() {
        LocalDateTime testDate = LocalDateTime.of(2024, 9, 30, 10, 0);
        String taskName = "Test Task 1";
        Deadline deadline = new Deadline(taskName, testDate);

        String expectedString = String.format("[D][ ] %s (by: %s)", taskName, DateParser.toOutputString(testDate));
        assertEquals(expectedString, deadline.toString());
    }

    @Test
    public void toString_isCompleteValueTruePassed_correctString() {
        LocalDateTime testDate = LocalDateTime.of(2024, 7, 9, 1, 42);
        String taskName = "Test Task 2";
        Deadline deadline = new Deadline(taskName, true, testDate);

        String expectedString = String.format("[D][X] %s (by: %s)", taskName, DateParser.toOutputString(testDate));
        assertEquals(expectedString, deadline.toString());
    }

    @Test
    public void toString_isCompleteValueFalsePassed_correctString() {
        LocalDateTime testDate = LocalDateTime.of(2024, 10, 20, 14, 15);
        String taskName = "Test Task 3";
        Deadline deadline = new Deadline(taskName, false, testDate);

        String expectedString = String.format("[D][ ] %s (by: %s)", taskName, DateParser.toOutputString(testDate));
        assertEquals(expectedString, deadline.toString());
    }

    @Test
    public void toSaveFormat_noBooleanInitialised_correctStoreFormat() {
        LocalDateTime testDate = LocalDateTime.of(2024, 9, 30, 10, 0);
        Deadline deadline = new Deadline("No Boolean Initialised", testDate);

        String expectedFormat = String.format("D | 0 | %s | %s",
                "No Boolean Initialised",
                DateParser.toOutputString(testDate));
        assertEquals(expectedFormat, deadline.toSaveFormat());
    }

    @Test
    public void toSaveFormat_booleanInitialisedAsTrue_correctStoreFormat() {
        LocalDateTime testDate = LocalDateTime.of(2024, 9, 30, 10, 0);
        String taskName = "Task is complete";
        Deadline deadline = new Deadline(taskName, true, testDate);

        String expectedFormat = String.format("D | 1 | %s | %s",
                taskName,
                DateParser.toOutputString(testDate));
        assertEquals(expectedFormat, deadline.toSaveFormat());
    }

    @Test
    public void toSaveFormat_booleanInitialisedAsFalse_correctStoreFormat() {
        LocalDateTime testDate = LocalDateTime.of(2024, 9, 30, 10, 0);
        String taskName = "Task is incomplete";
        Deadline deadline = new Deadline(taskName, false, testDate);

        String expectedFormat = String.format("D | 0 | %s | %s",
                taskName,
                DateParser.toOutputString(testDate));
        assertEquals(expectedFormat, deadline.toSaveFormat());
    }
}
