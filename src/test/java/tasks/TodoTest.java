package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void stringTest() {
        Todo temp = new Todo("work");
        assertEquals("[T][ ] work", temp.toString());
    }

    @Test
    public void dataTest() {
        Todo temp = new Todo("work");
        assertEquals("T0work", temp.toData());
    }
}
