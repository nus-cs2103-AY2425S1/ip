package trackie.storage;

import org.junit.jupiter.api.Test;
import trackie.commands.Command;
import trackie.parsing.Parser;
import trackie.tasks.Task;
import trackie.ui.TrackieException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addDeadlineTask_emptyDescription_exceptionThrown() {
        try {

            String[] buffer = {"deadline", "/by"};
            new TaskList().addDeadlineTask(buffer);
            fail();
        } catch (TrackieException e) {
            assertEquals("Description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_emptyDeadline_exceptionThrown() {
        try {
            String[] buffer = {"deadline", "test", "/by"};
            new TaskList().addDeadlineTask(buffer);
            fail();
        } catch (TrackieException e) {
            assertEquals("Deadline cannot be empty!", e.getMessage());
        }
    }
}
