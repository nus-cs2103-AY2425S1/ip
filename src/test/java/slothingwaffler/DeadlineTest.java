package slothingwaffler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline task = new Deadline("sample", "2023-12-11");
        String expected = "[D][ ] sample (by: Dec 11 2023)";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testToFileFormat() {
        Deadline task = new Deadline("sample", "2023-12-11");
        String expected = "D | 0 | sample | 2023-12-11";
        assertEquals(expected, task.toFileFormat());
    }
}
