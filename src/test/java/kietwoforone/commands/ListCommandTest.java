package kietwoforone.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    @Test
    public void listCommandTest() {
        assertEquals("Tasks listed", new ListCommand().toString());
    }

}
