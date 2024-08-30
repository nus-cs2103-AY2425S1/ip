package WindeBot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseTest() {
        Parser parser = null;
        Reminder reminder = new Reminder();
        Ui ui = new Ui();
        try {
            assertTrue(parser.parse("todo something").execute("todo something", reminder, ui));
            assertTrue(parser.parse("todo").execute("todo", reminder, ui));
            assertTrue(parser.parse("deadline something /by 23/04/9000 23:59")
                    .execute("deadline something /by 23/04/9000 23:59", reminder, ui));
            assertTrue(parser.parse("event something /from 23/04/9000 23:59 /to 23/12/9000 23:59")
                    .execute("event something /from 23/04/9000 23:59 /to 23/12/9000 23:59", reminder, ui));
            assertTrue(parser.parse("hello").execute("hello", reminder, ui));
            assertFalse(parser.parse("bye").execute("bye", reminder, ui));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
