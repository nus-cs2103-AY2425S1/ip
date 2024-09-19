package juno.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import juno.manager.exception.TaskManagerException;

public class EventTest {

    @Test
    public void event_validInput_success() throws TaskManagerException {
        String description = "Finish project";
        String startTimeString = "2024 11 17 08.00AM";
        String endTimeString = "2024 11 17 10.00AM";
        String taskType = "event";

        Event event = new Event(description, startTimeString, endTimeString, taskType);
        assertEquals(endTimeString, event.getEndTimeString());

        assertEquals(description, event.getDescription());
        assertEquals(endTimeString, event.getEndTimeString());
        assertEquals(startTimeString, event.getStartTimeString());
        assertEquals("[\uD83D\uDCC5 Event] [ ] Finish project - Mark your calendar! \uD83D\uDDD3\uFE0F "
                + "(from: 17 Nov 2024, 08:00AM to: 17 Nov 2024, 10:00AM)", event.toString());
    }

    @Test
    public void event_invalidInput_exceptionThrown() {
        String description = "Finish project";
        String startTimeString = "2024 11 17 08.00AM";
        String invalidEndTimeString = "2024-11-17 10:00 AM";
        String taskType = "event";

        TaskManagerException e = assertThrows(TaskManagerException.class, () -> {
            new Event(description, startTimeString, invalidEndTimeString, taskType);
        });

        assertEquals(TaskManagerException.ErrorType.INVALID_DATETIME_ARGUMENT, e.getErrorType());
        assertEquals("Your format for date is wrong! Please use this format: add event {description} "
                + "/yyyy MM dd hh.mma /yyyy MM dd hh.mma .", e.getMessage());
    }

}
