package darkpool.parser.validator;

import java.util.Objects;

import darkpool.DarkpoolException;

/**
 * ValidateFind class is used to validate the user input for the find command.
 */
public class ValidateFind {

    /**
     * This method validates the user input for the find command.
     *
     * @param userInput The user input to be validated.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("bro what am i supposed to search for??");
        }
    }
}
