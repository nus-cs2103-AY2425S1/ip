package Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void save_test() {
        assertEquals("T | 0 | read book", new Todo("read book").save());
    }

}