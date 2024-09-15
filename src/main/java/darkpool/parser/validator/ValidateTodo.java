package darkpool.parser.validator;

import darkpool.DarkpoolException;

import java.util.Objects;

public class ValidateTodo {
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("you missed the task description bruh");
        }
    }
}
