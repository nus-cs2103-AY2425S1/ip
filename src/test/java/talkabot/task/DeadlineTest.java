package talkabot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineGetDayTest() {
        assertEquals("Saturday",
                new Deadline(new String[] {"Complete quiz", "2024-09-07"}).getDay());
    }
}
