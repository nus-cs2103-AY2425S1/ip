package talkabot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventFileStringTest() {
        assertEquals("0 | Study for exams | 2024-11-18 | 2024-11-24",
                new Event(new String[] {"Study for exams", "2024-11-18", "2024-11-24"})
                        .fileString());
    }
}
