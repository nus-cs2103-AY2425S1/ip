package count;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import count.exception.IncorrectFormatException;
import count.exception.InvalidCommandException;

public class ParserTest {
    private String rootFilePath = "./Count.txt";
    private String markIncorrectFormatString = "Use a number after mark/unmark/delete/remind to specify"
            + " the task targeted!\n Type 'help' to see correct formatting examples";
    private String deadlineIncorrectFormatString = "Invalid format for event or deadline!\n"
            + "Type 'help' to see correct formatting examples";

    @Test
    public void singleWordParser_parse() {
        Parser p = new Parser(new TaskList(), rootFilePath);
        assertThrows(InvalidCommandException.class, () -> p.parse("BLAAH"));
    }

    @Test
    public void multiWordParser_parse() {
        Parser p = new Parser(new TaskList(), rootFilePath);
        assertThrows(InvalidCommandException.class, () -> p.parse("BLAAH RAAAA"));
    }

    @Test
    public void multiWordParser_markFormat() {
        Parser p = new Parser(new TaskList(), rootFilePath);
        Exception exception = assertThrows(IncorrectFormatException.class, () -> p.parse("mark cheese"));
        assertEquals(markIncorrectFormatString, exception.getMessage());
    }

    @Test
    public void multiWordParser_deadlineFormat() {
        Parser p = new Parser(new TaskList(), rootFilePath);
        Exception exception = assertThrows(IncorrectFormatException.class, () ->
                p.parse("deadline homework /by 300/10/2000"));
        assertEquals(deadlineIncorrectFormatString, exception.getMessage());
    }
}
