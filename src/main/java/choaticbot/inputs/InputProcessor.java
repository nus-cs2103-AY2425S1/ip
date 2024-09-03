package choaticbot.inputs;

import choaticbot.exceptions.NoInputException;

public class InputProcessor {

    public InputProcessor() {};

    public ProcessedInput processInput(String input) throws NoInputException {
        if (input.isBlank()) {
            throw new NoInputException("Please enter an input!!!");
        }
        String[] inputArray = input.split(" ", 2);
        String action = inputArray[0];
        String details = inputArray.length > 1 ? inputArray[1].trim() : "";

        return new ProcessedInput(action, details);
    }
}
