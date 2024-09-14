package fred;

import fred.Exceptions.FredException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseInputTest() throws Exception {
        String[] expected = new String[]{"addToTaskList", "todo", "return book"};
        String[] actual = new Parser().parseInput("todo return book");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        expected = new String[]{"addToTaskList", "deadline", "return book /by 2024-06-02 12:30"};
        actual = new Parser().parseInput("deadline return book /by 2024-06-02 12:30");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        expected = new String[]{"addToTaskList", "event", "project meeting /from 2024-06-02 12:30 /to 2024-06-02 13:30"};
        actual = new Parser().parseInput("event project meeting /from 2024-06-02 12:30 /to 2024-06-02 13:30");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        expected = new String[]{"markTaskAsDone", "4"};
        actual = new Parser().parseInput("mark 5");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        expected = new String[]{"markTaskAsNotDone", "4"};
        actual = new Parser().parseInput("unmark 5");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        expected = new String[]{"deleteFromTaskList", "4"};
        actual = new Parser().parseInput("delete 5");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        expected = new String[]{"sayFarewell"};
        actual = new Parser().parseInput("bye");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
        expected = new String[]{"printTaskList"};
        actual = new Parser().parseInput("list");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void parseInputTest_exception() {
        try {
            assertEquals(null, new Parser().parseInput("todo"));
            fail();
        } catch (FredException e) {
            assertEquals("OOPS!!! The description of a task cannot be empty.", e.getMessage());
        }
        try {
            assertEquals(null, new Parser().parseInput("mark"));
            fail();
        } catch (FredException e) {
            assertEquals("OOPS!!! That's not a valid task number!", e.getMessage());
        }
        try {
            assertEquals(null, new Parser().parseInput("unmark"));
            fail();
        } catch (FredException e) {
            assertEquals("OOPS!!! That's not a valid task number!", e.getMessage());
        }
        try {
            assertEquals(null, new Parser().parseInput("lol"));
            fail();
        } catch (FredException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
