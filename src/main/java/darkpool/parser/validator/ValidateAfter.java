package darkpool.parser.validator;

import java.util.Objects;

import darkpool.DarkpoolException;

/**
 * ValidateAfter class is used to validate the user input for the after command.
 */
public class ValidateAfter {
    /**
     * This method validates the user input for the after command.
     *
     * @param userInput The user input to be validated.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("wheres the desc of the after, and whats the date??");
        }
    }

    /**
     * This method validates the user input for the after command.
     *
     * @param text The user input to be validated.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static void validateFrom(String text) throws DarkpoolException {
        if (!text.contains("/from")) {
            throw new DarkpoolException("bruh might as well use todo");
        }
    }

    /**
     * This method validates the user input for the after command.
     *
     * @param desc The user input to be validated.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static void validateEmptyDesc(String desc) throws DarkpoolException {
        if (desc.isEmpty()) {
            throw new DarkpoolException("whats the task of the after??");
        }
    }

    /**
     * This method validates the user input for the after command.
     *
     * @param array The user input to be validated.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static void validateArrayLength(String[] array) throws DarkpoolException {
        if (array.length > 2) {
            throw new DarkpoolException("everything good? there cant be multiple from");
        } else if (array.length == 0) {
            throw new DarkpoolException("wheres the from and task of after?");
        } else if (array.length == 1) {
            throw new DarkpoolException("so close! you forgot to enter the after date");
        }
    }


}
