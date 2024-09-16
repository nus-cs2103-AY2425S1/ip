package sigma.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import sigma.utils.TaskList;

public class EventCommandTest {

    @Test
    public void eventCommand_normalEvent_normalResponse() {
        EventCommand ec = new EventCommand(
                new String[]{"event", "go library /from 2024-12-12 1800 /to 2024-12-12 2000"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            ec.execute(tl, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("[E][ ] go library (from: Dec 12 2024, 18:00 to: Dec 12 2024, 20:00)", tl.get(0).toString());
    }

    @Test
    public void eventCommand_missingToParameter_exceptionThrown() {
        EventCommand ec = new EventCommand(new String[]{"event", "go library /from 2024-08-30 1800"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            ec.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals(
                    "What the sigma? You're missing the end timing! "
                            + "Write \"event <task> /from <start time> /to "
                            + "<end time>\"!", e.getMessage());
        }
    }

    @Test
    public void eventCommand_missingFromParameter_exceptionThrown() {
        EventCommand ec = new EventCommand(new String[]{"event", "go library /to 2024-08-30 2000"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            ec.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals(
                    "What the sigma? You're missing the timing! Write \"event <task> /from <start time> /to <end"
                    + " time>\"!", e.getMessage());
        }
    }

    @Test
    public void eventCommand_emptyInput_exceptionThrown() {
        EventCommand ec = new EventCommand(new String[]{"event"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            ec.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals("What the sigma? You're missing the task! Write \"event <task> /from <start time> /to <end "
                            + "time>\"!",
                    e.getMessage());
        }
    }

    @Test
    public void eventCommand_invalidDateTimeFormat_exceptionThrown() {
        EventCommand ec = new EventCommand(new String[]{"event", "go library /from 2024-08-30 /to 2024-08-30 2000"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            ec.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals("What the sigma? Invalid date format! Write the date in the format YYYY-MM-DD HHmm!",
                    e.getMessage());
        }
    }

    @Test
    public void eventCommand_invalidDateTime_exceptionThrown() {
        EventCommand ec = new EventCommand(new String[]{"event", "go library /from 2024-08-30 1800 /to "
                + "2024123-08-308127 2000"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            ec.execute(tl, null, null);
        } catch (Exception e) {
            assertEquals("What the sigma? Invalid date format! Write the date in the format YYYY-MM-DD HHmm!",
                    e.getMessage());
        }
    }
}
