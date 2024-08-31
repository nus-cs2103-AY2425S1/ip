package rainy.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markTest() throws InvalidMarkAndUnmarkException {
        Task newTask = new Task("Rainy");
        newTask.mark();
        assertEquals(true, newTask.getIsDone());
        try {
            newTask.mark();
        } catch (InvalidMarkAndUnmarkException e) {
            assertEquals("Task is already marked as done!", e.getMessage());
        }
    }

    @Test
    public void unmarkTest() throws InvalidMarkAndUnmarkException {
        Task newTask = new Task("Rainy");
        try {
            newTask.unmark();
        } catch (InvalidMarkAndUnmarkException e) {
            assertEquals("Task is still undone!", e.getMessage());
        }
        newTask.mark();
        newTask.unmark();
        assertEquals(false, newTask.getIsDone());
    }
}