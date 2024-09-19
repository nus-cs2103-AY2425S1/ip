package darkpool.parser.validator;

import java.util.Objects;

import darkpool.DarkpoolException;



/**
 * ValidateTodo class is used to validate the user input for the todo command.
 */
public class ValidateTodo {

    /**
     * validate method is used to validate the user input for the todo command.
     *
     * @param userInput The user input to be validated.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("you missed the task description bruh");
        }
    }
}
