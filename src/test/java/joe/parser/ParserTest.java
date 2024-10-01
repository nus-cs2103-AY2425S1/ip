package joe.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import joe.ControllerStub;
import joe.ui.Ui;

public class ParserTest {
    private final ControllerStub controller = new ControllerStub();
    private final Parser parser = new Parser(controller, new Ui("Test"));

    private final String todoFormat = "todo %s";
    private final String deadlineFormat = "deadline %s /by %s";
    private final String eventFormat = "event %s /from %s /to %s";

    @BeforeEach
    public void setUp() {
        controller.reset();
    }

    @Test
    public void testParseTodo() {
        String task = "read book";
        String input = String.format(todoFormat, task);
        parser.parse(input);
        assertEquals(1, controller.getParams().size());
        assertEquals(task, controller.getParams().get(0));
    }

    @Test
    public void testParseDeadline() {
        String task = "return book";
        String by = "2021-08-24";
        String input = String.format(deadlineFormat, task, by);
        parser.parse(input);
        assertEquals(2, controller.getParams().size());
        assertEquals(task, controller.getParams().get(0));
        assertEquals(by, controller.getParams().get(1));
    }

    @Test
    public void testParseEvent() {
        String task = "project meeting";
        String from = "2021-08-24";
        String to = "2021-08-25";
        String input = String.format(eventFormat, task, from, to);
        parser.parse(input);
        assertEquals(3, controller.getParams().size());
        assertEquals(task, controller.getParams().get(0));
        assertEquals(from, controller.getParams().get(1));
        assertEquals(to, controller.getParams().get(2));
    }
}
