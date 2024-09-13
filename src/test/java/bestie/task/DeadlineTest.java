package bestie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    void constructor_normalInput_correctDescription() {
        Deadline deadline = new Deadline("submit tutorial", "2024-08-29 1900");
        assertEquals("submit tutorial", deadline.getDescription());
        assertEquals("[D][ ] submit tutorial (by: Aug 29 2024 7:00 pm)", deadline.toString());
    }

    @Test
    void constructor_incorrectInput_errorMessageDisplayed() {
    }

    @Test
    void toSaveFormat_normalInput_savedCorrectly() {
        Deadline deadline = new Deadline("submit tutorial", "2024-08-29 1900");
        assertEquals("D | 0 | submit tutorial | 2024-08-29 1900", deadline.toSaveFormat());
    }

    @Test
    void toSaveFormat_completedTask_savedCorrectly() {
        Deadline deadline = new Deadline("submit tutorial", "2024-08-29 1900");
        deadline.isDone = true;
        assertEquals("D | 1 | submit tutorial | 2024-08-29 1900", deadline.toSaveFormat());
    }


}
