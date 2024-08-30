package bill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
