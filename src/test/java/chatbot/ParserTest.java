package chatbot;

import chatbot.task.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void  parseFileLine_validLine() {
        try {
            Parser.parseFileLine("T|0|Test");
            Parser.parseFileLine("D|0|Test|2020-10-12T15:15");
            Parser.parseFileLine("E|0|Test|2020-10-12T15:23|2021-10-12T16:24");
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void  parseFileLine_invalidTodo_exceptionThrown() {
        assertThrows(Exception.class,
                () -> {
                    Parser.parseFileLine("T|0");
                });
    }

    @Test
    public void  parseFileLine_invalidEvent_exceptionThrown() {
        assertThrows(Exception.class,
                () -> {
                    Parser.parseFileLine("E|0|Test|2020-10-12T15:23");
                });
    }

    @Test
    public void  parseFileLine_invalidDeadline_exceptionThrown() {
        assertThrows(Exception.class,
                () -> {
                    Parser.parseFileLine("D|0|Test");
                });
    }
}
