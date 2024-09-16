package sigma.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import sigma.utils.TaskList;




public class DeadlineCommandTest {

    @Test
    public void deadlineCommand_normalDeadline_normalResponse() {
        DeadlineCommand dc = new DeadlineCommand(new String[]{"deadline", "return book /by 2024-12-12 1800"});
        TaskList tl = new TaskList(new ArrayList<>());
        String response = null;
        try {
            response = dc.execute(tl, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("Wow! Keeping yourself busy! Added: \n"
                + "[D][ ] return book (by: Dec 12 2024, 18:00)\n"
                + "Now you have 1 tasks in the list!", response);
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
