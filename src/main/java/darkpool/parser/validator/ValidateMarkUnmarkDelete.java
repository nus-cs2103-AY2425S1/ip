package darkpool.parser.validator;

import darkpool.DarkpoolException;

import java.util.Objects;

/**
 * Validates the user input for the mark, unmark and delete commands.
 */
public class ValidateMarkUnmarkDelete {

    /**
     * Validates the user input for the mark, unmark and delete commands.
     *
     * @param userInput The user input.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("bro wheres the number 🗿");
        }
    }

    /**
     * Validates the number in the user input for the mark, unmark and delete commands.
     *
     * @param userInput The user input.
     * @return The number in the user input.
     * @throws DarkpoolException If the number in the user input is invalid.
     */
    public static int validateNumber(String[] userInput) throws DarkpoolException {
        int num;
        try {
            num = Integer.parseInt(userInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DarkpoolException("do you know what a number is?");
        }
        return num;
    }
}
