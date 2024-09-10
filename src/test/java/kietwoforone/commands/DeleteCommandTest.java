package kietwoforone.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    private int position = 2;

    @Test
    public void deleteCommandTest() {
        assertEquals("Task deleted", new DeleteCommand(2).toString());
    }
}
