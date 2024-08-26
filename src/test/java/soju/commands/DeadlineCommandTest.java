package soju.commands;

import org.junit.jupiter.api.Test;
import soju.SojuException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DeadlineCommandTest {
    @Test
    public void eventConstructor_emptyDescription_sojuExceptionThrown() {
        assertThrowsExactly(SojuException.class, () -> new DeadlineCommand("deadline"));
    }
    @Test
    public void eventConstructor_invalidDateTimeFormat_sojuExceptionThrown() {
        assertThrowsExactly(SojuException.class,
                () -> new DeadlineCommand("deadline this should fail /by 0500"));
    }
    @Test
    public void eventConstructor_normalInput_constructedCorrectly() throws SojuException {
        DeadlineCommand deadlineCommand =
                new DeadlineCommand("deadline this should pass /by 2022-03-02");
        assertNotNull(deadlineCommand);
    }
}
