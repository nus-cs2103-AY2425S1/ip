package darkpool.parser.validator;

import darkpool.DarkpoolException;

import java.util.Objects;

/**
 * ValidateTodo class is used to validate the user input for the todo command.
 */
public class ValidateTodo {
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("you missed the task description bruh");
        }
    }
}
