package darkpool.parser.validator;

import darkpool.DarkpoolException;

import java.util.Objects;

public class ValidateDeadline {
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("wheres the deadline of the task, and whats the task of the deadline??");
        }
    }

    public static void validateBy(String text) throws DarkpoolException {
        if (!text.contains("/by")) {
            throw new DarkpoolException("bruh might as well use todo");
        }
    }

    public static void validateEmptyDesc(String desc) throws DarkpoolException {
        if (desc.isEmpty()) {
            throw new DarkpoolException("whats the task of the deadline??");
        }
    }

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
