package moimoi.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindCommandTest {

    @Test
    public void testisExit() {
        FindCommand findCommand = new FindCommand("dummy");
        assertFalse(findCommand.isExit());
    }

}
