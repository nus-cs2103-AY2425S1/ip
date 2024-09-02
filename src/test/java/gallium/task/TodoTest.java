package gallium.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo test = new Todo("todo test");
        assertEquals("[T][ ] test", test.toString());
    }

    @Test
    public void setIsDoneTest() {
        Todo test = new Todo("todo test");
        test.setIsDone(true);
        assertEquals("[T][X] test", test.toString());
    }
}
