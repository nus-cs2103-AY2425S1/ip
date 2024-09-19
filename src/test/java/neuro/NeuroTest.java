package neuro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NeuroTest {
    private Neuro neuro;

    @BeforeEach
    public void setUp() {
        neuro = new Neuro("data/Neuro.txt");
    }

    @Test
    public void getResponse_invalidCommand_errorMessageReturned() {
        String invalidInput = "This is an invalid input";
        String expectedResult = "UH OH! I'm sorry but I don't recognise that command. Try the following:\n"
                + "'todo', 'deadline', 'event', 'list', 'find', 'tag', 'mark', 'unmark', 'delete', 'bye'.";

        assertEquals(expectedResult, neuro.getResponse(invalidInput));
    }

    @Test
    public void getResponse_emptyString_assertionErrorThrown() {
        String emptyInput = "";

        assertThrows(AssertionError.class, () -> neuro.getResponse(emptyInput));
    }

    @Test
    public void getResponse_validCommand_noErrorMessage() {
        String validInput = "list";
        String errorMessage = "UH OH! I'm sorry but I don't recognise that command. Try the following:\n"
                + "'todo', 'deadline', 'event', 'list', 'find', 'tag', 'mark', 'unmark', 'delete', 'bye'.";

        assertNotEquals(errorMessage, neuro.getResponse(validInput));
    }
}
