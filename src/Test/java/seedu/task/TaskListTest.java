package seedu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addToDo_validInput_expectedOutcome() throws Exception {
        String description = "task description";
        TaskList tl = new TaskList();
        String result = tl.addToDo(description);

        assertNotNull(result);

        String expected = "Got it. I've added this task: \n" + "[T][ ] task description"
                + "\nNow you have 1 tasks in the list.";
        assertEquals(expected, result);
    }

    @Test
    public void addToDo_duplicateInput_unexpectedOutcome() throws Exception {
        String description = "blah blah";
        try {
            TaskList tl = new TaskList();
            tl.addToDo(description);
            tl.addToDo(description);
            fail();
        } catch (Exception e) {
            assertEquals("Duplicate task found!!", e.getMessage());
        }
    }
}
