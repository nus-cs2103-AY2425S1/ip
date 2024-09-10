package kietwoforone.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkCommandTest {

    private int position = 2;

    @Test
    public void unmarkCommandTest() {
        assertEquals("Task unmarked", new UnmarkCommand(position).toString());
    }
}
