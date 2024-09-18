package tecna;

import tecna.task.Event;
import tecna.command.EventCommand;
import tecna.exception.WrongFormatException;
import tecna.util.DateTimeUtil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static tecna.util.DateTimeUtil.DATE_TIME_PATTERN;

public class EventCommandTest {
    @Test
    public void parseEventCommand_nothingOrBlankBehind_exceptionThrown() {
        EventCommand nothingBehind = new EventCommand("event");

        try {
            nothingBehind.parseEventCommand();
        } catch (WrongFormatException e) {
            assertEquals(new WrongFormatException("event",  "Event task should in the format of \"event [task name] /from [" + DATE_TIME_PATTERN + "] /to [" + DATE_TIME_PATTERN + "]").getMessage(), e.getMessage());
        }

        EventCommand blankBehind = new EventCommand("event      ");

        try {
            blankBehind.parseEventCommand();
        } catch (WrongFormatException e) {
            assertEquals(new WrongFormatException("event",  "Event task should in the format of \"event [task name] /from [" + DATE_TIME_PATTERN + "] /to [" + DATE_TIME_PATTERN + "]").getMessage(), e.getMessage());
        }
    }

    @Test
    public void parseEventCommand_missingTaskName_exceptionThrown() {
        EventCommand emptyTaskName = new EventCommand("event /from 2024-02-02 1200 /to 2024-02-02 1300");

        try {
            emptyTaskName.parseEventCommand();
        } catch (WrongFormatException e) {
            assertEquals(new WrongFormatException("event", "Event task should in the format of \"event [task name] /from [" + DATE_TIME_PATTERN + "] /to [" + DATE_TIME_PATTERN + "]").getMessage(), e.getMessage());
        }
    }

    @Test
    public void parseEventCommand_emptyTaskName_exceptionThrown() {
        EventCommand blankTaskName= new EventCommand("event        /from 2024-02-02 1200 /to 2024-02-02 1300");

        try {
            blankTaskName.parseEventCommand();
        } catch (WrongFormatException e) {
            assertEquals(new WrongFormatException("event", "Event task's [task name] must not be empty").getMessage(), e.getMessage());
        }
    }

    @Test
    public void parseEventCommand_wrongDateFormat_exceptionThrown() {
        EventCommand wrongDateFormat = new EventCommand("event hi /from 12-12-2023 1200 /to 12-12-2023 1300");

        try {
            wrongDateFormat.parseEventCommand();
        } catch (WrongFormatException e) {
            assertEquals(new WrongFormatException("event",  "Event task should in the format of \"event [task name] /from [" + DATE_TIME_PATTERN + "] /to [" + DATE_TIME_PATTERN + "]").getMessage(), e.getMessage());
        }
    }

    @Test
    public void parseEventCommand_spacesBetween_exceptionThrown() {
        EventCommand space1 = new EventCommand("event     hi /from 2023-12-12 1200 /to 2023-12-12 1300");
        EventCommand space2 = new EventCommand("event hi       /from 2023-12-12 1200 /to 2023-12-12 1300");
        EventCommand space3 = new EventCommand("event hi /from      2023-12-12 1200 /to 2023-12-12 1300");
        EventCommand space4 = new EventCommand("event hi /from 2023-12-12 1200          /to 2023-12-12 1300");
        EventCommand space5 = new EventCommand("event hi /from 2023-12-12 1200 /to 2023-12-12 1300        ");

        Event correct = new Event("hi", DateTimeUtil.parseDateTime("2023-12-12 1200"), DateTimeUtil.parseDateTime("2023-12-12 1300"));

        try {
            assertEquals(correct, space1.parseEventCommand());
            assertEquals(correct, space2.parseEventCommand());
            assertEquals(correct, space3.parseEventCommand());
            assertEquals(correct, space4.parseEventCommand());
            assertEquals(correct, space5.parseEventCommand());
        } catch (WrongFormatException e) {
            fail();
        }

    }
}
