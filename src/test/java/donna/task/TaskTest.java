package donna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import donna.DonnaException;

public class TaskTest {

    @Test
    void testTaskInitialisationWithValidDescription() throws DonnaException {
        Task task = new ToDo("Valid Task");
        assertEquals("[T][ ] Valid Task ", task.toString());
        assertFalse(task.isDone());
    }

    @Test
    void testTaskInitialisationWithEmptyDescription() {
        assertThrows(DonnaException.class, () -> new ToDo(""),
                "Expected to throw DonnaException for empty description.");
    }

    @Test
    void testMarkTaskAsDone() throws DonnaException {
        Task task = new ToDo("Valid Task");
        task.markDone();
        assertTrue(task.isDone());
        assertEquals("[T][X] Valid Task ", task.toString());
    }

    @Test
    void testMarkTaskAsNotDone() throws DonnaException {
        Task task = new ToDo("Valid Task");
        task.markDone(); // Mark done first
        task.markNotDone(); // Then mark not done
        assertFalse(task.isDone());
        assertEquals("[T][ ] Valid Task ", task.toString());
    }
}
