package spongebob;

import spongebob.exception.SpongebobException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parser_correctExitInput_success() throws SpongebobException {
        assertArrayEquals(new String[]{"exit"}, new Parser().parse("bye").getArgs());
    }

    @Test
    public void parser_correctDeadlineInput_success() {
        assertArrayEquals(new String[]{"deadline","test","10/10/1010"," "},
                new Parser().parse("deadline test /by 10/10/1010").getArgs());
    }

    @Test
    public void parser_correctEventInput_success() {
        assertArrayEquals(new String[]{"event","test","10/10/1010","11/11/1111"},
                new Parser().parse("event test /from 10/10/1010 /to 11/11/1111").getArgs());
    }

    @Test
    public void parser_correctTodoInput_success() {
        assertArrayEquals(new String[]{"todo", "test", " ", " "},
                new Parser().parse("todo test").getArgs());
    }

    @Test
    public void parser_wrongInput_success() {
        assertArrayEquals(new String[]{"event", "", "10", " "},
                new Parser().parse("event /by 10 ").getArgs());
    }
}
