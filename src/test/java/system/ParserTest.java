package system;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void testContainBye() {
        assertTrue(parser.containBye("bye"));
    }

    @Test
    public void testContainList() {
        assertTrue(parser.containList("list"));
    }

    @Test
    public void testContainMark() {
        assertTrue(parser.containMark("mark"));
    }

    @Test
    public void testContainUnmark() {
        assertTrue(parser.containUnmark("unmark"));
    }

    @Test
    public void testContainToDo() {
        assertTrue(parser.containToDo("todo"));
    }

    @Test
    public void testContainDeadline() {
        assertTrue(parser.containDeadline("deadline"));
    }

    @Test
    public void testContainEvent() {
        assertTrue(parser.containEvent("event"));
    }

    @Test
    public void testContainDelete() {
        assertTrue(parser.containDelete("delete"));
    }
}
