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

    @Test
    public void addAndSortTasks() {
        TaskTracker newTask = new TaskTracker();
        newTask.updateListToDo("borrow book");
        assertEquals("1. [ ] [T] borrow book", newTask.getList().split("\\n")[1]);
        newTask.updateListDeadline("homework", "2024-09-12 1945");
        assertEquals("2. [ ] [D] homework (by Sep 12 2024 19:45)", newTask.getList().split("\\n")[2]);
        newTask.updateListEvent("project meeting", "2024-08-19", "1800 to 2000");
        assertEquals("3. [ ] [E] project meeting (Aug 19 2024 from 18:00 to 20:00)", newTask.getList().split("\\n")[3]);
        newTask.sortList();
        String tempString = newTask.getList();
        assertEquals("Here are your tasks!!! Remember to complete them!!!" + "\n"
            + "1. [ ] [E] project meeting (Aug 19 2024 from 18:00 to 20:00)" + "\n" +
                "2. [ ] [D] homework (by Sep 12 2024 19:45)" + "\n" +
                    "3. [ ] [T] borrow book", tempString);
    }

    @Test
    public void deleteAndSortTasks() throws InvalidIndexException {
        TaskTracker newTask = new TaskTracker();
        newTask.updateListToDo("borrow book");
        newTask.delete(0);
        String tempString = newTask.getList();
        assertEquals("No tasks currently!", tempString);
        try {
            newTask.delete(-1);
        } catch (InvalidIndexException e) {
            assertEquals("You have entered an invalid task number! Please try again.", e.getMessage());
        }

    }
}