package bill;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markTest_mark_showsX() {
        Task juggle = new Task("juggle");
        juggle.mark();
        // Juggle task mark toString() is [X] juggle
        assertEquals("[X] juggle", juggle.toString());
    }

    @Test
    public void unMarkTest_unmark_noX() {
        Task juggle = new Task("juggle");
        juggle.unmark();
        // Juggle task unmark toString() is [ ] juggle
        assertEquals("[ ] juggle", juggle.toString());
    }

    @Test
    public void markUnmarkTest_markUnmark_noX() {
        Task juggle = new Task("juggle");
        juggle.mark();
        juggle.unmark();
        // Juggle task unmark toString() is [ ] juggle
        assertEquals("[ ] juggle", juggle.toString());
    }

    @Test
    public void statusIconMarkTest_markValue_withX() {
        Task juggle = new Task("juggle");
        juggle.mark();
        // Juggle task getStatusIcon is "X" when marked
        assertEquals("X", juggle.getStatusIcon());
    }

    @Test
    public void statusIconNoMarkTest_noMarkValue_blank() {
        Task juggle = new Task("juggle");
        juggle.unmark();
        // Juggle task getStatusIcon is " " when unmarked
        assertEquals(" ", juggle.getStatusIcon());
    }

    @Test
    public void descriptionTest_genericDescriptor_matching() {
        Task juggle = new Task("juggle");
        // Juggle task getDescription is "juggle"
        assertEquals("juggle", juggle.getDescription());
    }

    @Test
    public void defaultConstructorTest_genericConstructor_success() {
        Task juggle = new Task("juggle");
        // Juggle task when constructed, getStatusIcon is " " and getDescription is "juggle"
        assertEquals(" ", juggle.getStatusIcon());
        assertEquals("juggle", juggle.getDescription());
    }

    @Test
    public void toStringTest_descriptor_matching() {
        Task juggle = new Task("juggle");
        // Juggle task toString is "[ ] juggle" as default is unmarked
        assertEquals("[ ] juggle", juggle.toString());
    }
}
