package michael;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void Test1() {
        Storage s = new Storage("./data/save.txt");
        Event e = new Event("Test", "Mon 2pm", "4pm");
        assertEquals("E | 0 | Test | Mon 2pm | 4pm\n", s.convertTaskToString(e));
    }

    @Test
    public void Test2() {
        Storage s = new Storage("./data/save.txt");
        String save = "D | 0 | Test | 2024-09-09";
        Task d = s.load(save);
        assertEquals("[D][ ] Test (by: 9 Sep 2024)",d.toString());
    }
}
