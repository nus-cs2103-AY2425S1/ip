package cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import cookie.task.Deadline;

public class DeadlineTest {
    @Test
    void testConstructor() {
        String description = "Submit assignment";
        String by = "today";

        Deadline deadline = new Deadline(description, by);
        assertEquals(description, deadline.getDescription());
        assertEquals(by, deadline.getBy());
    }

    @Test
    void testToString() {
        LocalDate deadlineDate = LocalDate.of(2024, 9, 10);
        String by = "2024-09-10";

        DateParserStub.convertStringToDate(by);
        DateParserStub.changePattern(deadlineDate);

        Deadline deadline = new Deadline("Submit assignment", by);
        assertEquals("[D][] Submit assignment (by: Sep 10 2024)", deadline.toString());

        deadline.markDone();
        assertEquals("[D][X] Submit assignment (by: Sep 10 2024)", deadline.toString());
    }

    static class DateParserStub {
        public static LocalDate convertStringToDate(String date) {
            return LocalDate.parse(date);
        }
        public static String changePattern(LocalDate date) {
            return date.format(java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }
}
