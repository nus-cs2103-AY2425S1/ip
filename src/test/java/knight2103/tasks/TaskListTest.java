package knight2103.tasks;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void mark_validIndex_correctOutput() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("read book"));
        Task taskToMark = new DeadlineTask("return book", "2020-12-20");
        testTaskList.add(taskToMark);
        taskToMark.markDone(); // for assertEqual later
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));
        assertEquals(taskToMark, testTaskList.mark(1));
    }

    @Test
    public void mark_outOfRangeIndex_exceptionThrown() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("read book"));
        testTaskList.add(new DeadlineTask("return book", "2020-12-20"));
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));
        try {
            assertEquals(0, testTaskList.mark(5));
            fail(); // the test should not reach this line
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 5 out of bounds for length 3", e.getMessage());
        }
    }

    @Test
    public void unmark_validIndex_correctOutput() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("read book"));
        Task taskToUnmark = new DeadlineTask("return book", "2020-12-20");
        taskToUnmark.markDone();
        testTaskList.add(taskToUnmark);
        taskToUnmark.unmarkDone(); // for assertEqual later
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));
        assertEquals(taskToUnmark, testTaskList.unmark(1));
    }

    @Test
    public void unmark_outOfRangeIndex_exceptionThrown() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("read book"));
        testTaskList.add(new DeadlineTask("return book", "2020-12-20"));
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));
        try {
            assertEquals(0, testTaskList.unmark(5));
            fail(); // the test should not reach this line
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 5 out of bounds for length 3", e.getMessage());
        }
    }

    @Test
    public void delete_validIndex_correctOutput() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("read book"));
        Task taskToDelete = new DeadlineTask("return book", "2020-12-20");
        testTaskList.add(taskToDelete);
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));
        assertEquals(taskToDelete, testTaskList.delete(1));
    }

    @Test
    public void delete_outOfRangeIndex_exceptionThrown() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("read book"));
        testTaskList.add(new DeadlineTask("return book", "2020-12-20"));
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));
        try {
            assertEquals(0, testTaskList.delete(5));
            fail(); // the test should not reach this line
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 5 out of bounds for length 3", e.getMessage());
        }
    }

}
