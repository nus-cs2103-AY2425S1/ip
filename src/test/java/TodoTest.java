import org.junit.jupiter.api.Test;
import task.ToDos;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void testFileStringConversion() {
        assertEquals("T | 0 | eat", new ToDos("eat").toFileString());
    }

}