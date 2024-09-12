package choaticbot.inputs;

import choaticbot.exceptions.NoInputException;

/**
 * The {@code InputProcessor} class is responsible for processing raw user input
 * and extracting the action and details from it. If the input is blank, it throws
 * a {@link NoInputException}.
 */
public class InputProcessor {

    /**
     * Constructs an {@code InputProcessor}.
     */
    public InputProcessor() {};

    /**
     * Processes the raw user input string and extracts the action and details.
     * The input is split into the action (first word) and the details (rest of the input).
     *
     * @param input The raw input string from the user.
     * @return A {@link ProcessedInput} object containing the extracted action and details.
     * @throws NoInputException If the input string is blank or empty.
     */
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
