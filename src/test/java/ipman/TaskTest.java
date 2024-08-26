package ipman;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void toString_success() {
        assertEquals(
                "[ ] description",
                String.valueOf(new Task("description"))
        );
    }

    @Test
    public void checkMark_success() {
        assertEquals(
                "[X] description",
                String.valueOf(new Task("description").markStatus())
        );
    }

}
