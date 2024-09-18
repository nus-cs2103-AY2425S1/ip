/*package Kira;

import Exceptions.EmptyException;
import Exceptions.InvalidTaskException;
import Exceptions.UnreadableException;
import Tasks.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    Parser parser = new Parser(new List());
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
    }
    @Test
    public void parse_todo() throws EmptyException, UnreadableException, InvalidTaskException {
        parser.parse("todo a");
        assertEquals("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "[T][ ] a\n" +
                "Now you have 1 task in the list.\n" +
                "____________________________________________________________", outputStreamCaptor.toString().trim());
    }

    @Test
    public void parse_event() throws EmptyException, UnreadableException, InvalidTaskException {
        parser.parse("event b /from 1/2/2023 1900 /to 2/2/2023 2000");
        assertEquals("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "[E][ ] b (from: Feb 1 2023 19:00 to: Feb 2 2023 20:00)\n" +
                "Now you have 1 task in the list.\n" +
                "____________________________________________________________", outputStreamCaptor.toString().trim());
    }

    @Test
    public void parse_deadline()  throws EmptyException, UnreadableException, InvalidTaskException {
        parser.parse("deadline c /by 1/2/2023 1900");
        assertEquals("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "[D][ ] c (by: Feb 1 2023 19:00)\n" +
                "Now you have 1 task in the list.\n" +
                "____________________________________________________________", outputStreamCaptor.toString().trim());
    }
}
*/