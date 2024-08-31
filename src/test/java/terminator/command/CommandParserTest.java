package terminator.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandParserTest {

    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void parse_validListPattern_success() throws TerminatorException {
        Command c = new CommandParser().parse("list");
        c.execute(new ArrayList<>());
        assertEquals("Listing current mission objectives:\n\n", outputStreamCaptor.toString());
    }

}
