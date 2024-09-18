package command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.ElliotException;

public class FindCommandTest {

    @Test
    public void invalidInputArgumentsHandling_exceptionThrown() {
        String[] argumentInputs = {"*", "\\*\\", "\\"};
        for (String s : argumentInputs) {
            assertThrows(ElliotException.class, () -> {
                new EventCommand().parseArguments(s);
            });
        }
    }
}
