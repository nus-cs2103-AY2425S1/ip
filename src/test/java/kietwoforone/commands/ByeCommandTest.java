package kietwoforone.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {

    @Test
    public void byeCommandTest() {
        assertEquals("Bye", new ByeCommand().toString());
    }

}
