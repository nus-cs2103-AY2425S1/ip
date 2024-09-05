package bob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void eventTaskExportTest() {
        EventTask eventTaskStandard = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        assertEquals("event false Test /from 12/01/2001 /to 12/01/2002", eventTaskStandard.export());

        EventTask eventTaskWithStartTime = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        assertEquals("event false Test /from 12/01/2001 1800 /to 12/01/2002", eventTaskWithStartTime.export());

        EventTask eventTaskWithEndTime = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        assertEquals("event false Test /from 12/01/2001 /to 12/01/2002 1800", eventTaskWithEndTime.export());

        EventTask eventTaskComplete = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        assertEquals("event false Test /from 12/01/2001 1800 /to 12/01/2002 1800", eventTaskComplete.export());

        EventTask eventTaskStandardMarked = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        eventTaskStandardMarked.mark();
        assertEquals("event true Test /from 12/01/2001 /to 12/01/2002", eventTaskStandardMarked.export());

        EventTask eventTaskWithStartTimeMarked = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        eventTaskWithStartTimeMarked.mark();
        assertEquals("event true Test /from 12/01/2001 1800 /to 12/01/2002", eventTaskWithStartTimeMarked.export());

        EventTask eventTaskWithEndTimeMarked = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        eventTaskWithEndTimeMarked.mark();
        assertEquals("event true Test /from 12/01/2001 /to 12/01/2002 1800", eventTaskWithEndTimeMarked.export());

        EventTask eventTaskCompleteMarked = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        eventTaskCompleteMarked.mark();
        assertEquals("event true Test /from 12/01/2001 1800 /to 12/01/2002 1800", eventTaskCompleteMarked.export());
    }

    @Test
    public void eventToStringTest() {
        EventTask eventTaskStandard = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        assertEquals("[E][ ] Test (from: Jan 12 2001 to: Jan 12 2002)", eventTaskStandard.toString());

        EventTask eventTaskWithStartTime = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        assertEquals("[E][ ] Test (from: Jan 12 2001 18:00 to: Jan 12 2002)", eventTaskWithStartTime.toString());

        EventTask eventTaskWithEndTime = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        assertEquals("[E][ ] Test (from: Jan 12 2001 to: Jan 12 2002 18:00)", eventTaskWithEndTime.toString());

        EventTask eventTaskComplete = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        assertEquals("[E][ ] Test (from: Jan 12 2001 18:00 to: Jan 12 2002 18:00)", eventTaskComplete.toString());

        EventTask eventTaskStandardMarked = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        eventTaskStandardMarked.mark();
        assertEquals("[E][X] Test (from: Jan 12 2001 to: Jan 12 2002)", eventTaskStandardMarked.toString());

        EventTask eventTaskWithStartTimeMarked = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        eventTaskWithStartTimeMarked.mark();
        assertEquals("[E][X] Test (from: Jan 12 2001 18:00 to: Jan 12 2002)", eventTaskWithStartTimeMarked.toString());

        EventTask eventTaskWithEndTimeMarked = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        eventTaskWithEndTimeMarked.mark();
        assertEquals("[E][X] Test (from: Jan 12 2001 to: Jan 12 2002 18:00)", eventTaskWithEndTimeMarked.toString());

        EventTask eventTaskCompleteMarked = new EventTask("Test",
                LocalDate.parse("12/01/2001", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")),
                LocalDate.parse("12/01/2002", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        eventTaskCompleteMarked.mark();
        assertEquals("[E][X] Test (from: Jan 12 2001 18:00 to: Jan 12 2002 18:00)", eventTaskCompleteMarked.toString());
    }

}
