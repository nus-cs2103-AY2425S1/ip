package rainy.tasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTrackerTest {

    @Test
    public void markDoneTest() throws InvalidIndexException, InvalidMarkAndUnmarkException {
        TaskTracker tm = new TaskTracker();
        tm.updateListDeadline("homework", "2024-09-12 1945");
        tm.markDone(0);
        assertEquals("" + "1. [X] [D] homework (by Sep 12 2024 19:45)", tm.getList().split("\\n")[1]);
        try {
            tm.markDone(0);
        } catch (InvalidMarkAndUnmarkException e) {
            assertEquals("Task is already marked as done!", e.getMessage());
        }
    }

    @Test
    public void unmarkDoneTest() throws InvalidIndexException, InvalidMarkAndUnmarkException {
        TaskTracker tm = new TaskTracker();
        tm.updateListDeadline("homework", "2024-09-12 1945");
        tm.markDone(0);
        tm.unmarkDone(0);
        assertEquals("" + "1. [ ] [D] homework (by Sep 12 2024 19:45)", tm.getList().split("\\n")[1]);
        try {
            tm.unmarkDone(0);
        } catch (InvalidMarkAndUnmarkException e) {
            assertEquals("Task is still undone!", e.getMessage());
        }
    }
}