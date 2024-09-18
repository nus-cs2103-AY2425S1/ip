package janet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @BeforeAll
    public static void setUp() {
        Locale.setDefault(new Locale("en", "SG"));
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
    }

    @Test
    public void eventCreation_success() throws Exception {
        assertEquals(
                new Event("project meeting",
                        "E",
                        LocalDateTime.of(2024, 12, 30, 18, 0),
                        LocalDateTime.of(2024, 12, 30, 18, 30)
                ).getDescription(),
                new Event("event project meeting /from 2024-12-30 18:00 /to 2024-12-30 18:30").getDescription()
        );

        assertEquals(
                new Event("event project meeting /from 2024-10-30 18:00 /to 2024-10-30 18:30").getStartDate(),
                LocalDateTime.of(2024, 10, 30, 18, 0).format(
                        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a")
                )
        );

        assertEquals(
                new Event("event project meeting /from 2024-10-30 18:00 /to 2024-10-30 18:30").getEndDate(),
                LocalDateTime.of(2024, 10, 30, 18, 30).format(
                        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a")
                )
        );
    }

    @Test
    public void eventCreation_failure() throws Exception {
        // end date earlier than start date
        JanetException e1 = assertThrows(JanetException.class,
                () -> {new Event("event project meeting /from 2024-12-30 18:00 /to 2024-11-30 18:30");});
        assertEquals(
                e1.getMessage(), "WHOOPS! Your event's start date cannot be later than/equal to the end date!"
        );

        // start date = end date
        JanetException e2 = assertThrows(JanetException.class,
                () -> {new Event("event project meeting /from 2024-12-30 18:30 /to 2024-12-30 18:30");});
        assertEquals(
                e2.getMessage(), "WHOOPS! Your event's start date cannot be later than/equal to the end date!"
        );
    }

    @Test
    public void eventCreationFromTxtTest() throws Exception {
        String[] textLine = "E | 1 | dinner | Nov 30 2024 18:30 pm-Nov 30 2024 22:00 pm".split("\\|");
        LocalDateTime startDate = LocalDateTime.parse("Nov 30 2024 18:30 pm",
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a"));
        LocalDateTime endDate = LocalDateTime.parse("Nov 30 2024 22:00 pm",
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a"));

        Event expectedEvent = new Event(textLine);
        Event actualEvent = new Event("dinner", "E", startDate, endDate);

        assertEquals(expectedEvent.getDescription(), actualEvent.getDescription());
        assertEquals(expectedEvent.getSymbol(), actualEvent.getSymbol());
        assertEquals(expectedEvent.getScheduledDateAndTime(), actualEvent.getScheduledDateAndTime());
        assertEquals(expectedEvent.getEndDateAndTime(), actualEvent.getEndDateAndTime());
    }
}
