package terminator.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import terminator.task.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandTest {

    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static final String ERR_MSG = """
            List command takes no arguments.
            
            Usage: list""";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void execute_validListPattern_success() throws TerminatorException {
        ListCommand lc = new ListCommand(null);
        lc.execute(new ArrayList<Task>());
        assertEquals("Listing current mission objectives:\n\n", outputStreamCaptor.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"list 12345", "list ", "list    ", "list abcde"})
    public void execute_invalidListPatterns_exceptionThrown(String input) {
        ListCommand lc = new ListCommand(input);
        TerminatorException de = assertThrows(TerminatorException.class, () -> lc.execute(new ArrayList<>()));
        assertEquals(ERR_MSG, de.getMessage());
    }
}
