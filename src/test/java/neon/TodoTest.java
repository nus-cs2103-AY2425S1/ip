package neon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toDo_toStringDone_success() {
        assertEquals("[T][X] borrow book", new Todo("borrow book", true).toString());
    }
}
