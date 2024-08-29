package serenity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToString() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }

    @Test
    public void formatData() {
        assertEquals("T | 0 | read book", new Todo("read book").formatData());
    }

}
