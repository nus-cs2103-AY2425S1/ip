package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KorolevDeadlineTest {
    @Test
    public void deadlineTest_markEvent_success() {
        KorolevDeadline expected = new KorolevDeadline("test", "2021-01-01T00:00");
        expected.markTask();
        assertEquals(expected.toString(), "[D][X] test (by 00:00 Jan 1 2021)  [tag:]");
        expected.unmarkTask();
        assertEquals(expected.toString(), "[D][ ] test (by 00:00 Jan 1 2021)  [tag:]");
    }
}
