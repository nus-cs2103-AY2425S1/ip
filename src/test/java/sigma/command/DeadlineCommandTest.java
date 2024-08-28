package sigma.command;

import org.junit.jupiter.api.Test;
import sigma.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {

    @Test
    public void deadlineCommand_normalDeadline_normalResponse() {
        DeadlineCommand dc = new DeadlineCommand(new String[]{"deadline", "return book /by 2024-08-30 1800"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            dc.execute(tl, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("[D][ ] return book (by: Aug 30 2024, 18:00)", tl.get(0).toString());
    }

    @Test
    public void deadlineCommand_invalidInput_exceptionThrown() {
        DeadlineCommand dc = new DeadlineCommand(new String[]{"deadline", "return book"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            dc.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals("What the sigma? You're missing the deadline! Write \"deadline <task> /by <deadline>\"!",
                    e.getMessage());
        }
    }

    @Test
    public void deadlineCommand_invalidFormat_exceptionThrown() {
        DeadlineCommand dc = new DeadlineCommand(new String[]{"deadline", "return book /by"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            dc.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals("What the sigma? You're missing the deadline! Write \"deadline <task> /by <deadline>\"!",
                    e.getMessage());
        }
    }

    @Test
    public void deadlineCommand_emptyInput_exceptionThrown() {
        DeadlineCommand dc = new DeadlineCommand(new String[]{"deadline"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            dc.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals("What the sigma? You're missing the task! Write \"deadline <task> /by <deadline>\"!",
                    e.getMessage());
        }
    }

    @Test
    public void deadlineCommand_invalidDateTimeFormat_exceptionThrown() {
        DeadlineCommand dc = new DeadlineCommand(new String[]{"deadline", "return book /by 2024-08-30"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            dc.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals("What the sigma? Invalid date format! Write the date in the format YYYY-MM-DD HHmm!",
                    e.getMessage());
        }
    }

    @Test
    public void deadlineCommand_invalidDateTime_exceptionThrown() {
        DeadlineCommand dc = new DeadlineCommand(new String[]{"deadline", "return book /by 2024123-08-308127 1800"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            dc.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals("What the sigma? Invalid date format! Write the date in the format YYYY-MM-DD HHmm!",
                    e.getMessage());
        }
    }
}
