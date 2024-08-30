package evan.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void encodeAsString_validInputs_success() {
        Todo t1 = new Todo("Todo description");
        assertEquals("T | 0 | Todo description", t1.encodeAsString());
        t1.markAsDone();
        assertEquals("T | 1 | Todo description", t1.encodeAsString());
    }
}
