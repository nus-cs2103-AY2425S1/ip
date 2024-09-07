package command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.ElliotException;

public class EventCommandTest {

    @Test
    public void invalidUnparsedArguments_exceptionThrown() {
        String[] argumentInputs = {"", "a/from/to", "a/to/from", "/from/to",
            "a /from 02-02-2002 1300 /to ", "a /from 31-31-3100 1300 /to"};
        for (String s : argumentInputs) {
            assertThrows(ElliotException.class, () -> {
                new EventCommand().parseArguments(s);
            });
        }
    }
}
