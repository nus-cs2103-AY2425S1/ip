package moimoi.util.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ListCommandTest {

    @Test
    public void testIsExit() {
        ListCommand listCommand = new ListCommand();
        assertFalse(listCommand.isExit());
    }

}
