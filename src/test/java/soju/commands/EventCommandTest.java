package soju.commands;

import org.junit.jupiter.api.Test;
import soju.SojuException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class EventCommandTest {
    @Test
    public void eventConstructor_emptyDescription_sojuExceptionThrown() {
        assertThrowsExactly(SojuException.class, () -> new EventCommand("event"));
    }
    @Test
    public void eventConstructor_invalidDateTimeFormat_sojuExceptionThrown() {
        assertThrowsExactly(SojuException.class,
                () -> new EventCommand("event this should fail /from 2022-03-02 /to 2022-03-03"));
    }
    @Test
    public void eventConstructor_normalInput_constructedCorrectly() throws SojuException {
        EventCommand eventCommand =
                new EventCommand("event this should pass /from 2022-03-02 0500 /to 2022-03-03 0600 ");
        assertNotNull(eventCommand);
    }
}
