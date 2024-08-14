package sigma;

import org.junit.jupiter.api.Test;
import sigma.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTestOne() {
        Todo test = new Todo("Study CS2103T");
        assertEquals("[T][ ] Study CS2103T", test.toString(), "toString() ok");
        assertEquals("T | 0 | Study CS2103T", test.stringify(), "stringify() ok");

        test.markAsDone();
        assertEquals("[T][X] Study CS2103T", test.toString(), "toString() after markAsDone() ok");
        assertEquals("T | 1 | Study CS2103T", test.stringify(), "stringify() after markAsDone() ok");
    }

    @Test
    public void todoTestTwo() {
        Todo test = new Todo("Get A for CS2103T");
        test.markAsDone();
        assertEquals("[T][X] Get A for CS2103T", test.toString(), "toString() after markAsDone() ok");
        test.markAsNotDone();
        assertEquals("T | 0 | Get A for CS2103T", test.stringify(),
                "stringify() after markAsNotDone() ok");
    }
}
