package moimoi.util.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class FindCommandTest {

    @Test
    public void testIsExit() {
        FindCommand findCommand = new FindCommand("dummy");
        assertFalse(findCommand.isExit());
    }

}
