package dumpling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dumpling.command.Command;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

public class ParserTest {
    @Test
    public void deadline_missingBySeparator_exceptionThrown() {
        Storage storage = new Storage("data/dumplingDataTest.txt");
        Ui ui = new Ui();
        TaskList tasks = new TaskList(storage.load());
        String[] fullCommands = {
            "deadline read book by 2024-08-28",
            "deadline read book /By 2024-08-28",
            "deadline read book 2024-08-28"
        };
        for (String fullCommand : fullCommands) {
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                fail();
            } catch (DumplingException e) {
                assertEquals(
                        "Intending to extract information out of a given input, but nothing to extract.",
                        e.getMessage()
                );
            }
        }
    }

    @Test
    public void deadline_missingDate_exceptionThrown() {
        Storage storage = new Storage("data/dumplingDataTest.txt");
        Ui ui = new Ui();
        TaskList tasks = new TaskList(storage.load());
        String fullCommand = "deadline read book";
        try {
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            fail();
        } catch (DumplingException e) {
            assertEquals(
                    "Intending to extract information out of a given input, but nothing to extract.",
                    e.getMessage()
            );
        }
    }

    @Test
    public void deadline_invalidDate_exceptionThrown() {
        Storage storage = new Storage("data/dumplingDataTest.txt");
        Ui ui = new Ui();
        TaskList tasks = new TaskList(storage.load());
        String[] fullCommands = {
            "deadline read book /by 28 Aug 2024",
            "deadline read book /by Aug 28 2024",
            "deadline read book /by 28 Aug",
            "deadline read book /by 4pm",
        };
        for (String fullCommand : fullCommands) {
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                fail();
            } catch (DumplingException e) {
                assertEquals(
                        "Please enter the date in the correct format of YYYY-MM-DD.",
                        e.getMessage()
                );
            }
        }
    }

    @Test
    public void add_emptyDescription_exceptionThrown() {
        Storage storage = new Storage("data/dumplingDataTest.txt");
        Ui ui = new Ui();
        TaskList tasks = new TaskList(storage.load());
        String[] fullCommands = {
            "event",
            "todo"
        };
        for (String fullCommand : fullCommands) {
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                fail();
            } catch (DumplingException e) {
                assertEquals(
                        "Intending to extract information out of a given input, but nothing to extract.",
                        e.getMessage()
                );
            }
        }
    }

    @Test
    public void load_correctFile_success() {
        Storage storage = new Storage("data/dumplingDataTest.txt");
        Ui ui = new Ui();
        TaskList tasks = new TaskList(storage.load());
        assertEquals(
                "     Here are the tasks in your list:\n"
                        + "     1.[T][ ] read book\n"
                        + "     2.[D][X] project meeting (by: Aug 29 2024)",
                tasks.list()
        );
    }
}
