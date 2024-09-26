package mylo.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void ongoingOnDate_startDateZeroHours_true() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999, 9, 9), LocalTime.MIDNIGHT);
            boolean isOngoing = eventTask.isOngoing(testDateTime);
            assertTrue(isOngoing);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_endDate_true() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999, 9, 19), LocalTime.MIDNIGHT);
            boolean isOngoing = eventTask.isOngoing(testDateTime);
            assertTrue(isOngoing);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_afterEndDate_false() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999, 9, 20), LocalTime.MIDNIGHT);
            boolean isOngoing = eventTask.isOngoing(testDateTime);
            assertFalse(isOngoing);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_beforeStartDate_false() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999, 9, 8), LocalTime.MIDNIGHT);
            boolean isOngoing = eventTask.isOngoing(testDateTime);
            assertFalse(isOngoing);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_spansMultipleDays_true() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "11-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(LocalDate.of(1999, 9, 10), LocalTime.NOON);
            boolean isOngoing = eventTask.isOngoing(testDateTime);
            assertTrue(isOngoing, "Event should be ongoing on a day between the start and end dates.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_exactEndTime_true() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(1999, 9, 19, 23, 59);
            boolean isOngoing = eventTask.isOngoing(testDateTime);
            assertTrue(isOngoing, "Event should be ongoing at the exact end time.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ongoingOnDate_exactStartTime_true() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0800", "19-09-1999 2359");
            LocalDateTime testDateTime = LocalDateTime.of(1999, 9, 9, 8, 0);
            boolean isOngoing = eventTask.isOngoing(testDateTime);
            assertTrue(isOngoing, "Event should be ongoing at the exact start time.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void equals_todo_false() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            Todo todoTask = new Todo("Test");
            boolean isEqual = eventTask.equals(todoTask);
            assertFalse(isEqual);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void equals_deadlineSameAsStartDate_false() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            Deadline deadlineTask = new Deadline("Test", "09-09-1999 0000");
            boolean isEqual = eventTask.equals(deadlineTask);
            assertFalse(isEqual);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void equals_deadlineSameAsEndDate_false() {
        try {
            Event eventTask = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            Deadline deadlineTask = new Deadline("Test", "19-09-1999 2359");
            boolean isEqual = eventTask.equals(deadlineTask);
            assertFalse(isEqual);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void equals_sameTitleDifferentStartEndDates_false() {
        try {
            Event eventTask1 = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            Event eventTask2 = new Event("Test", "09-09-2000 0000", "19-09-2000 2359");
            boolean isEqual = eventTask1.equals(eventTask2);
            assertFalse(isEqual, "Wrongly interpreted two event tasks of same title but different start "
                    + "and end date as equal.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void equals_sameTitleAndStartDateDifferentEndDates_false() {
        try {
            Event eventTask1 = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            Event eventTask2 = new Event("Test", "09-09-1999 0000", "19-09-2000 2359");
            boolean isEqual = eventTask1.equals(eventTask2);
            assertFalse(isEqual, "Wrongly interpreted two event tasks of same title and start date but "
                    + "different end date as equal.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void equals_sameTitleAndEndDateDifferentStartDate_false() {
        try {
            Event eventTask1 = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            Event eventTask2 = new Event("Test", "09-09-2000 0000", "19-09-1999 2359");
            boolean isEqual = eventTask1.equals(eventTask2);
            assertFalse(isEqual, "Wrongly interpreted two event tasks of same title and end date "
                    + "but different start date as equal");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void equals_sameStartEndDateDifferentTitle_false() {
        try {
            Event eventTask1 = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            Event eventTask2 = new Event("Different", "09-09-1999 0000", "19-09-1999 2359");
            boolean isEqual = eventTask1.equals(eventTask2);
            assertFalse(isEqual, "Wrongly interpreted two event tasks of same start and end date "
                    + "but different title as equal");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void equals_differentObjectButSameDetails_true() {
        try {
            Event eventTask1 = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            Event eventTask2 = new Event("Test", "09-09-1999 0000", "19-09-1999 2359");
            boolean isEqual = eventTask1.equals(eventTask2);
            assertTrue(isEqual, "Two equivalent event objects are being misinterpreted as not equal.");
        } catch (Exception e) {
            fail();
        }
    }
}
