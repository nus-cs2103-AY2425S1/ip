package bobby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bobby.exception.EmptyDescriptionException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void validDeadlineTask() throws EmptyDescriptionException {
        String input = "deadline Watch Lecture /by 2024-09-01";
        Deadline deadlineTask = Deadline.createDeadline(input);
        assertEquals("Watch Lecture", deadlineTask.getDescription());
        assertEquals("D", deadlineTask.getTaskType());
    }

    @Test public void validDeadlineStringFormat() throws EmptyDescriptionException {
        String input = "deadline Watch Lecture /by 2024-09-01";
        Deadline deadlineTask = Deadline.createDeadline(input);
        assertEquals("[D][ ] Watch Lecture (by: Sep 1 2024)", deadlineTask.toString());
    }

    @Test
    public void validDeadlineTaskFormatInFile() throws EmptyDescriptionException {
        String input = "deadline Watch Lecture /by 2024-09-01";
        Deadline deadlineTask = Deadline.createDeadline(input);
        assertEquals("D |  | Watch Lecture | /by 2024-09-01", deadlineTask.taskInFile());
    }

    @Test
    public void invalidTodoTask() throws EmptyDescriptionException {
        String input = "deadline";
        EmptyDescriptionException e = assertThrows(EmptyDescriptionException.class, () -> Deadline.createDeadline(input));
    }
}
