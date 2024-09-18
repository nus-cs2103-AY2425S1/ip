package tudee.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tudee.TudeeException;

class DeadlineTest {

    private Deadline deadline;

    @BeforeEach
    void setUp() throws TudeeException {
        deadline = new Deadline("Submit assignment", "2024-09-01");
    }

    @Test
    void testIsDueSoon() {
        LocalDate currentDate = LocalDate.parse("2024-08-31");
        boolean isDueSoon = deadline.isDueSoon(currentDate);
        assertEquals(true, isDueSoon);

        currentDate = LocalDate.parse("2024-08-29");
        isDueSoon = deadline.isDueSoon(currentDate);
        assertEquals(false, isDueSoon);
    }

    @Test
    void testGetDeadline() {
        LocalDate expectedDate = LocalDate.parse("2024-09-01");
        assertEquals(expectedDate, deadline.getDeadline());
    }

    @Test
    void testToString() {
        String expectedString = "[D][  ] Submit assignment (by: Sep 1 2024)";
        assertEquals(expectedString, deadline.toString());
    }

    @Test
    void testToFileString() {
        String expectedFileString = "D | 0 | Submit assignment | 2024-09-01";
        assertEquals(expectedFileString, deadline.toFileString());
    }
}
