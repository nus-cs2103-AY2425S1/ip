package panorama.task; // same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import panorama.DateParser;

public class EventTest {
    private String name = "Event Task";
    private LocalDate from = LocalDate.now();
    private LocalDate to = from.plusYears(1);
    private Event e = new Event(name, from, to);

    @Test
    public void toString_success() {
        String dateFrom = DateParser.format(from);
        String dateTo = DateParser.format(to);
        String expected = "[E] [ ] " + name + " (from: " + dateFrom + " to: " + dateTo + ")";
        assertEquals(expected, e.toString());
    }

    @Test
    public void toFileString_success() {
        String dateFrom = DateParser.formatForHardDisk(from);
        String dateTo = DateParser.formatForHardDisk(to);
        String expected = "E|0|" + name + "|" + dateFrom + "|" + dateTo;
        assertEquals(expected, e.toFileString());
    }
}



