package darkpool.parser.validator;

import darkpool.DarkpoolException;

import java.util.Objects;

/**
 * ValidateFind class is used to validate the user input for the find command.
 */
public class ValidateFind {
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("bro what am i supposed to search for??");
        }
    }
}
