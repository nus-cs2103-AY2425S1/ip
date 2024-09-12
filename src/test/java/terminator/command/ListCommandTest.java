package terminator.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import terminator.task.Task;

public class ListCommandTest {

    private static final String ERR_MSG = """
            List command takes no arguments.\n
            Usage: list""";

    @Test
    public void execute_validListPattern_success() throws TerminatorException {
        ListCommand lc = new ListCommand(null);
        assertEquals("Listing current mission objectives:\n", lc.execute(new ArrayList<Task>()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"list 12345", "list ", "list    ", "list abcde"})
    public void execute_invalidListPatterns_exceptionThrown(String input) {
        ListCommand lc = new ListCommand(input);
        TerminatorException de = assertThrows(TerminatorException.class, () -> lc.execute(new ArrayList<>()));
        assertEquals(ERR_MSG, de.getMessage());
    }
}
