package moimoi.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListCommandTest {

    @Test
    public void testisExit() {
        ListCommand listCommand = new ListCommand();
        assertFalse(listCommand.isExit());
    }

}
