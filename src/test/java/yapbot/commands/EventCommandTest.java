package yapbot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;

public class EventCommandTest {
    @Test
    public void newEventcommand_withoutDetails_exceptionThrown() {
        try {

            EventCommand command = new EventCommand("");
            fail();
        } catch (YapBotException e) {
            assertEquals("Error, Automated Task Suggestion module offline."
                    + "\nTask details must be manually entered.", e.getMessage());
        }
    }

    @Test
    public void execute_withFromandToinAnyOrder_success() {
        try {
            EventCommand command = new EventCommand("Find nemo /from 9pm  /to 10am");
            assertTrue(command.execute(new TaskList(), new Storage("tasks.txt")));

            EventCommand command2 = new EventCommand("Find nemo /from 9pm  /to 10am");
            assertTrue(command2.execute(new TaskList(), new Storage("tasks.txt")));
        } catch (YapBotException e) {
            fail();
        }
    }

    @Test
    public void execute_withIrrelevantWhitespace_success() {
        try {
            EventCommand command = new EventCommand("Find nemo /from 9pm          /to          10am");
            TaskList tasks = new TaskList();

            assertTrue(command.execute(tasks, new Storage("tasks.txt")));
            assertEquals("  1.[E][ ] Find nemo (From: 9PM 03 Sep 2024 To: 10AM 03 Sep 2024)", tasks.listTasks());
        } catch (YapBotException e) {
            fail();
        }
    }

    @Test
    public void execute_withoutFromAndTo_exceptionThrown() {
        try {
            EventCommand command = new EventCommand("Find nemo /from   /to ");

            command.execute(new TaskList(), new Storage("tasks.txt"));
            fail();
        } catch (YapBotException e) {
            assertEquals("Error, start and end times not detected.\nUse command \"todo\" for tasks "
                    + "without deadlines.", e.getMessage());
        }
    }

    @Test
    public void execute_invalidDateandTimeFormat_exceptionThrown() {
        try {
            EventCommand command = new EventCommand("Find nemo /from 1 march  /to 16 feb");

            command.execute(new TaskList(), new Storage("tasks.txt"));
            fail();
        } catch (DateTimeParseException | YapBotException e) {
            assertEquals("Text '1 MARCH' could not be parsed at index 0", e.getMessage());
        }
    }
}
