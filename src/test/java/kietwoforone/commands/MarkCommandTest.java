package kietwoforone.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {

    private int position = 2;

    @Test
    public void markCommandTest() {
        assertEquals("Test marked", new MarkCommand(position).toString());
    }
}
