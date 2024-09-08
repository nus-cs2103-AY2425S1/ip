package qwerty.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.stubs.UiStub;

public class DeadlineCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setOut() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void setOriginalOut() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_emptyMap_exceptionThrown() {
        HashMap<String, String> args = new HashMap<>();
        Command command = new DeadlineCommand(args);
        assertThrows(QwertyException.class, () -> command.execute(new TaskList(), new UiStub(), new Storage("")));
    }

    @Test
    public void execute_mapWithNull_exceptionThrown() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", null);
        Command command = new DeadlineCommand(args);
        assertThrows(QwertyException.class, () -> command.execute(new TaskList(), new UiStub(), new Storage("")));
    }

    @Test
    public void execute_missingDateTime_exceptionThrown() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "deadline");
        Command command = new DeadlineCommand(args);
        assertThrows(QwertyException.class, () -> command.execute(new TaskList(), new UiStub(), new Storage("")),
                "You didn't tell me when your deadline is.");
    }

    @Test
    public void execute_missingDateTime2_exceptionThrown() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "deadline");
        args.put("by", null);
        Command command = new DeadlineCommand(args);
        assertThrows(QwertyException.class, () -> command.execute(new TaskList(), new UiStub(), new Storage("")),
                "You didn't tell me when your deadline is.");
    }

    @Test
    public void execute_badDateTime_noExceptionThrown() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "deadline");
        args.put("by", "108r91itn");
        Command command = new DeadlineCommand(args);
        assertDoesNotThrow(() -> command.execute(new TaskList(), new UiStub(), new Storage("")));
    }

    @Test
    public void execute_badDateTime_errorPrinted() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "deadline");
        args.put("by", "108r91itn");
        Command command = new DeadlineCommand(args);
        assertDoesNotThrow(() -> command.execute(new TaskList(), new UiStub(), new Storage("")));
        String expected = """
                Well done! An error has occurred:
                I don't like the way you write dates.
                Use this format: dd/MM/yyyy HHmm""";
        assertEquals(expected, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void execute_properInputs_noErrorPrinted() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "deadline");
        args.put("by", "12/08/2036 0000");
        Command command = new DeadlineCommand(args);
        assertDoesNotThrow(() -> command.execute(new TaskList(), new UiStub(), new Storage("")));
        String expected = """
                Added this task:
                [D][ ] deadline (by: Aug 12 2036 0000)
                Now you have 1 task in the list.
                Better get to it.""";
        assertEquals(expected, outContent.toString().trim().replace("\r", ""));
    }
}
