package choaticbot.inputs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import choaticbot.exceptions.ChoaticBotException;
import choaticbot.exceptions.NoInputException;


public class InputProcessorTest {
    private InputProcessor testInputProcessor = new InputProcessor();

    @Test
    public void processInput_todoInput_correctInput() throws NoInputException {
        String testInput = "todo todo1";
        ProcessedInput correctProcessInput = new ProcessedInput("todo", "todo1");

        assertTrue(correctProcessInput.isLike(testInputProcessor.processInput(testInput)));
    }

    @Test
    public void processInput_noInput_noInputExceptionThrown() {
        String testInput = "";
        try {
            testInputProcessor.processInput(testInput);

            //should not reach this line
            fail();
        } catch (ChoaticBotException e) {
            assertEquals("Please enter an input!!!", e.getMessage());
        }
    }
}
