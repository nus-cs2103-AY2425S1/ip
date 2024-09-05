package mylo.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void ongoingOnDate_startDateZeroHours_true() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999,9,9), LocalTime.MIDNIGHT);
            boolean result = eventTask.isOngoing(testDateTime);
            assertTrue(result);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_endDate_true() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999,9,19), LocalTime.MIDNIGHT);
            boolean result = eventTask.isOngoing(testDateTime);
            assertTrue(result);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_afterEndDate_false() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999,9,20), LocalTime.MIDNIGHT);
            boolean result = eventTask.isOngoing(testDateTime);
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_beforeStartDate_false() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999,9,8), LocalTime.MIDNIGHT);
            boolean result = eventTask.isOngoing(testDateTime);
            assertFalse(result);
        } catch (Exception e) {
            fail();
        }
    }
}
