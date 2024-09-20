package juno.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import juno.manager.exception.TaskManagerException;

public class DeadlineTest {
    @Test
    public void deadline_validInput_success() throws TaskManagerException {
        String description = "Finish project";
        String endTimeString = "2024 11 17 10.00AM";
        String taskType = "deadline";

        Deadline deadline = new Deadline(description, endTimeString, taskType);
        assertEquals(endTimeString, deadline.getEndTimeString());

        assertEquals(description, deadline.getDescription());
        assertEquals(endTimeString, deadline.getEndTimeString());
        assertEquals("[⏰ Deadline] [ ] Finish project - Don't miss it! ⏳ (due: 17 Nov 2024, 10:00AM)",
                deadline.toString());
    }

    @Test
    public void deadline_invalidInput_exceptionThrown() {
        String description = "Finish project";
        String invalidEndTimeString = "2024-11-17 10:00 AM";
        String taskType = "deadline";

        TaskManagerException e = assertThrows(TaskManagerException.class, () -> {
            new Deadline(description, invalidEndTimeString, taskType);
        });

        assertEquals(TaskManagerException.ErrorType.INVALID_DATETIME_ARGUMENT, e.getErrorType());
        assertEquals("Your format for date is wrong! Please use this format: add deadline {TASK_DESCRIPTION}"
                + "/yyyy MM dd hh.mma (e.g. add deadline homework /2024 11 17 10.00AM)", e.getMessage());
    }
}
