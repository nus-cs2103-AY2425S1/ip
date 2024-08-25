package moimoi.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListCommandTest {

    Command command;

    @Test
    public void testisExit() {
        this.command = new ListCommand();
        assertFalse(this.command.isExit());
    }

}
