package darkpool.parser.validator;

import darkpool.DarkpoolException;

import java.util.Objects;

/**
 * ValidateDeadline class is used to validate the deadline command.
 * It checks if the deadline command is valid and if the deadline is present.
 */
public class ValidateDeadline {

    /**
     * validate method checks if the deadline command is valid.
     * @param userInput The user input command.
     * @throws DarkpoolException If the deadline command is invalid.
     */
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("wheres the deadline of the task, and whats the task of the deadline??");
        }
    }

    /**
     * validateBy method checks if the deadline command contains the keyword "/by".
     * @param text The user input command.
     * @throws DarkpoolException If the deadline command does not contain the keyword "/by".
     */
    public static void validateBy(String text) throws DarkpoolException {
        if (!text.contains("/by")) {
            throw new DarkpoolException("bruh might as well use todo");
        }
    }

    /**
     * validateEmptyDesc method checks if the deadline description is empty.
     * @param desc The deadline description.
     * @throws DarkpoolException If the deadline description is empty.
     */
    public static void validateEmptyDesc(String desc) throws DarkpoolException {
        if (desc.isEmpty()) {
            throw new DarkpoolException("whats the task of the deadline??");
        }
    }

    /**
     * validateArrayLength method checks if the deadline command contains multiple deadlines.
     * @param array The user input command.
     * @throws DarkpoolException If the deadline command contains multiple deadlines.
     */
    public static void validateArrayLength(String[] array) throws DarkpoolException {
        if (array.length > 2) {
            throw new DarkpoolException("everything good? there cant be multiple deadlines");
        } else if (array.length == 0) {
            throw new DarkpoolException("wheres the deadline of the task, and whats the task of the deadline??");
        } else if (array.length == 1) {
            throw new DarkpoolException("so close! you forgot to enter the deadline");
        }
    }


}
