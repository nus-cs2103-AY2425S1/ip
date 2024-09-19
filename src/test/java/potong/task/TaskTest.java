package potong.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testMark() throws Exception {
        assertEquals("Nice! I've marked this task as done:\n"
                + " [X][ ] submit work", new Task("submit work").mark());
    }
    @Test
    public void tag_nonEmpty_success() throws Exception {
        assertEquals("OK, I've tagged this task as fun\n "
                + "[ ][#fun] play games", new Task("play games").tag("fun"));
    }
    @Test
    public void tag_empty_exceptionThrown() {
        try {
            assertEquals("OK, I've tagged this task as fun\n "
                    + "[ ][ ] play games", new Task("play games").tag(""));
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!! meow have a wrong input!\n"
                    + "We cannot have an empty tag!", e.getMessage());
        }
    }
    @Test
    public void testUntag() throws Exception {
        assertEquals("OK, I've untagged this task\n "
                + "[ ][ ] play games", new Task("play games", false, "fun").untag());
    }
    @Test
    public void testFindKeyword_true() throws Exception {
        assertTrue(new Task("watch youtube").findKeyword("watch"));
    }
    @Test
    public void testFindKeyword_false() throws Exception {
        assertFalse(new Task("watch youtube").findKeyword("play"));
    }
}
