package janet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void unknownInputTest() {
        JanetException exception = assertThrows(JanetException.class,
                () -> Parser.checkInaccurateCommand(new String[]{"blah"}));

        assertEquals(
                exception.getMessage(), "WHOOPS! I'm only a chatbot, so I don't know what that means..."
        );
    }

    @Test
    public void noTaskSpecifiedTest() {
        JanetException exception = assertThrows(JanetException.class,
                () -> Parser.checkInaccurateCommand(new String[]{"mark"}));

        assertEquals(
                exception.getMessage(), "WHOOPS! I don't know which task you are referring to..."
        );
    }

    @Test
    public void cannotParseTaskNumberTest() {
        JanetException exception = assertThrows(JanetException.class,
                () -> Parser.validateCommand(new String[]{"mark", "SOMETHING"}, 3));

        assertEquals(
                exception.getMessage(), "WHOOPS! Please provide an integer value task number!"
        );
    }

    @Test
    public void taskNumberOutOfRangeTest() {
        JanetException exception = assertThrows(JanetException.class,
                () -> Parser.validateCommand(new String[]{"mark", "4"}, 3));

        assertEquals(
                exception.getMessage(), "WHOOPS! You don't have a task of this number!"
        );
    }
}
