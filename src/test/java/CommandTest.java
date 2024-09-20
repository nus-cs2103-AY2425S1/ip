import exceptions.InvalidDateException;
import task.Task;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {

    /**
     * Tests that creating a task with no description throws NoTaskDescriptionException.
     */
    @Test
    public void createTodoWithoutDescription() {
        assertThrows(NoTaskDescriptionException.class, () -> Task.createTask("todo"));
    }

    /**
     * Tests that creating an event task without a description throws NoTaskDescriptionException.
     */
    @Test
    public void createEventWithoutDescription() {
        assertThrows(NoTaskDescriptionException.class, () -> Task.createTask("event /from 10-10-2019 /to 11-10-2019"));
    }

    /**
     * Tests that creating an event task with an invalid date format throws InvalidDateException.
     */
    @Test
    public void createEventWithInvalidDate() {
        assertThrows(InvalidDateException.class, () -> Task.createTask("event wedding /from 10-10-2019 /to 11-10-2019"));
    }

    /**
     * Tests that creating a task with an invalid task type throws InvalidTaskException.
     */
    @Test
    public void createTaskWithInvalidTaskType() {
        assertThrows(InvalidTaskException.class, () -> Task.createTask("invalidTask wedding /from 10-10-2019 /to 11-10-2019"));
    }

    @Test
    public void createTaskNoTagsNoTime() throws NoTaskDescriptionException, InvalidTaskException, InvalidDateException {
        Task newTask = Task.createTask("event wedding /from 10/10/2019 /to 10/10/2019");
        assertEquals("[E][ ] wedding (from: Oct 10 2019  to: Oct 10 2019) tags: no tags", newTask.toString());
    }

    @Test
    public void createTaskNoTags() throws NoTaskDescriptionException, InvalidTaskException, InvalidDateException {
        Task newTask = Task.createTask("event wedding /from 10/10/2019 1800 /to 10/10/2019 2300");
        assertEquals("[E][ ] wedding (from: Oct 10 2019 18:00 to: Oct 10 2019 23:00) tags: no tags", newTask.toString());
    }

    @Test
    public void createTaskWithTags() throws NoTaskDescriptionException, InvalidTaskException, InvalidDateException {
        Task newTask = Task.createTask("event wedding /from 10/10/2019 1800 /to 10/10/2019 2300 -t important " +
                "leisure");
        assertEquals("[E][ ] wedding (from: Oct 10 2019 18:00 to: Oct 10 2019 23:00) tags: important leisure ",
                newTask.toString());
    }

}