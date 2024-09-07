package noisy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest() {
        assertEquals(new Todo("test", true).formatTask(), "T | test | 1");
    }
}
