package yapbot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;

public class EventCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setupTaskListandStorage() {
        tasks = new TaskList();
        storage = new Storage("test.txt");
    }

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
            EventCommand command = new EventCommand("Find nemo /from 9pm 2024/09/18 /to 10am 2024/09/18");
            assertEquals("Adding Task...\nSuccess"
                    + "\nTask added to database:\n  "
                    + "[E][ ] Find nemo (From: 9PM 18 Sep 2024 To: 10AM 18 Sep 2024)"
                    + "\nTotal tasks: 1", command.execute(tasks, storage));

            EventCommand command2 = new EventCommand("Find nemo /to 10am 2024/09/18 /from 9pm 2024/09/18");
            assertEquals("Adding Task...\nSuccess"
                    + "\nTask added to database:\n  "
                    + "[E][ ] Find nemo (From: 9PM 18 Sep 2024 To: 10AM 18 Sep 2024)"
                    + "\nTotal tasks: 2", command2.execute(tasks, storage));
        } catch (YapBotException e) {
            fail();
        }
    }

    @Test
    public void execute_withIrrelevantWhitespace_success() {
        try {
            EventCommand command = new EventCommand("Find nemo     "
                    + "/from   9pm 2024/09/18 "
                    + "     /to   10am 2024/09/18");

            assertEquals("Adding Task...\nSuccess"
                    + "\nTask added to database:\n  "
                    + "[E][ ] Find nemo (From: 9PM 18 Sep 2024 To: 10AM 18 Sep 2024)"
                    + "\nTotal tasks: 1", command.execute(tasks, storage));
            assertEquals("  1.[E][ ] Find nemo (From: 9PM 18 Sep 2024 To: 10AM 18 Sep 2024)",
                    tasks.listTasks());
        } catch (YapBotException e) {
            fail();
        }
    }

    @Test
    public void execute_withoutFromAndTo_exceptionThrown() {
        try {
            EventCommand command = new EventCommand("Find nemo /from   /to ");

            command.execute(tasks, storage);
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

            command.execute(tasks, storage);
            fail();
        } catch (DateTimeParseException | YapBotException e) {
            assertEquals("Text '1 MARCH' could not be parsed at index 0", e.getMessage());
        }
    }
}
