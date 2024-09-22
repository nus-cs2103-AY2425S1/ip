package knight2103.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void mark_validIndex_correctOutput() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("read book"));
        testTaskList.add(new DeadlineTask("return book", "2020-12-20"));
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));

        Task taskToMark = new DeadlineTask("return book", "2020-12-20");
        taskToMark.markDone();

        assertEquals(taskToMark.toString(), testTaskList.mark(1).toString());
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
        Task taskToAdd = new DeadlineTask("return book", "2020-12-20");
        taskToAdd.markDone();
        testTaskList.add(taskToAdd);
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));

        Task taskUnmarked = new DeadlineTask("return book", "2020-12-20");

        assertEquals(taskUnmarked.toString(), testTaskList.unmark(1).toString());
    }

    @Test
    public void unmark_outOfRangeIndex_exceptionThrown() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("read book"));
        Task taskToAdd = new DeadlineTask("return book", "2020-12-20");
        taskToAdd.markDone();
        testTaskList.add(taskToAdd);
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
        testTaskList.add(new DeadlineTask("return book", "2020-12-20"));
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));

        Task taskToDelete = new DeadlineTask("return book", "2020-12-20");

        assertEquals(taskToDelete.toString(), testTaskList.delete(1).toString());
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

    @Test
    public void filter_wordCarInput_filteredList() {
        final String WORD_TO_SEARCH = "car";

        // list for testing
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("carry books"));
        testTaskList.add(new DeadlineTask("prepare career interview", "2020-12-20"));
        testTaskList.add(new TodoTask("prepare ca1"));
        testTaskList.add(new EventTask("car fair", "2020-12-21T09:00", "2020-12-22T22:00"));

        // filtered list as reference
        TaskList filteredTaskList = new TaskList();
        filteredTaskList.add(new TodoTask("carry books"));
        filteredTaskList.add(new DeadlineTask("prepare career interview", "2020-12-20"));
        filteredTaskList.add(new EventTask("car fair", "2020-12-21T09:00", "2020-12-22T22:00"));

        assertEquals(filteredTaskList.toString(),
                testTaskList.filter(
                        task -> task.getDescription().contains(WORD_TO_SEARCH))
                        .toString());
    }

    @Test
    public void filter_wordInput_sameList() {
        final String WORD_TO_SEARCH = "book";

        // list for testing
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("carry books"));
        testTaskList.add(new DeadlineTask("write book review", "2020-12-20"));
        testTaskList.add(new TodoTask("borrow books"));
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));

        // filtered list as reference
        TaskList filteredTaskList = new TaskList();
        filteredTaskList.add(new TodoTask("carry books"));
        filteredTaskList.add(new DeadlineTask("write book review", "2020-12-20"));
        filteredTaskList.add(new TodoTask("borrow books"));
        filteredTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));

        assertEquals(filteredTaskList.toString(),
                testTaskList.filter(
                                task -> task.getDescription().contains(WORD_TO_SEARCH))
                        .toString());
    }

    @Test
    public void filter_wordInput_noList() {
        final String WORD_TO_SEARCH = "happy";

        // list for testing
        TaskList testTaskList = new TaskList();
        testTaskList.add(new TodoTask("carry books"));
        testTaskList.add(new DeadlineTask("write book review", "2020-12-20"));
        testTaskList.add(new TodoTask("borrow books"));
        testTaskList.add(new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00"));

        // filtered list as reference
        TaskList filteredTaskList = new TaskList();
        assertEquals(filteredTaskList.toString(),
                testTaskList.filter(
                                task -> task.getDescription().contains(WORD_TO_SEARCH))
                        .toString());
    }
}
