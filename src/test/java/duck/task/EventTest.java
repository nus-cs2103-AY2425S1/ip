package duck.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testFileStringConversion(){
        assertEquals("E | 0 | desc | 16 August 2021 2pm | 4pm",
                new Event("desc", "16 August 2021 2pm", "4pm").toFileString());
    }
}
