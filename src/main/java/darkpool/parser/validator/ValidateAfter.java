package darkpool.parser.validator;

import darkpool.DarkpoolException;

import java.util.Objects;

public class ValidateAfter {
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("wheres the desc of the after, and whats the date??");
        }
    }

    public static void validateFrom(String text) throws DarkpoolException {
        if (!text.contains("/from")) {
            throw new DarkpoolException("bruh might as well use todo");
        }
    }

    public static void validateEmptyDesc(String desc) throws DarkpoolException {
        if (desc.isEmpty()) {
            throw new DarkpoolException("whats the task of the after??");
        }
    }

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
