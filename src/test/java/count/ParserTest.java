package count;

import count.exception.CountException;
import count.exception.IncorrectFormatException;
import count.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    String rootFilePath = "./Count.txt";
    String markIncorrectFormatString = "Use a number after mark/unmark/delete to specify the task targeted!\n" +
            "Type 'help' to see correct formatting examples";
    String deadlineIncorrectFormatString = "Invalid format for event or deadline!\n" +
            "Type 'help' to see correct formatting examples";

    @Test
    public void singleWordParser_invalidCommandExceptionThrown() {
        Parser p = new Parser(new TaskList(), rootFilePath);
        assertThrows(InvalidCommandException.class, () -> p.parse("BLAAH"));
    }

    @Test
    public void multiWordParser_invalidCommandExceptionThrown() {
        Parser p = new Parser(new TaskList(), rootFilePath);
        assertThrows(InvalidCommandException.class, () -> p.parse("BLAAH RAAAA"));
    }

    @Test
    public void multiWordParser_mark_incorrectFormatExceptionThrown() {
        Parser p = new Parser(new TaskList(), rootFilePath);
        Exception exception = assertThrows(IncorrectFormatException.class, () -> p.parse("mark cheese"));
        assertEquals(markIncorrectFormatString, exception.getMessage());
    }

    @Test
    public void multiWordParser_deadline_incorrectFormatExceptionThrown() {
        Parser p = new Parser(new TaskList(), rootFilePath);
        Exception exception = assertThrows(IncorrectFormatException.class, () -> p.parse("deadline homework /by 300/10/2000"));
        assertEquals(deadlineIncorrectFormatString, exception.getMessage());
    }
}