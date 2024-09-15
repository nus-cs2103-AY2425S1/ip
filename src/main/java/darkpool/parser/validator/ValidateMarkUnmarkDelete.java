package darkpool.parser.validator;

import darkpool.DarkpoolException;

import java.util.Objects;

public class ValidateMarkUnmarkDelete {
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("bro wheres the number 🗿");
        }
    }

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
