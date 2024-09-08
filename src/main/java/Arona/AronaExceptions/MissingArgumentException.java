package Arona.AronaExceptions;

import Arona.Command;

public class MissingArgumentException extends AronaException {
    /**
     * Constructor for the exceptions class of Arona, encapsulates all possible user errors that are non-fatal and can
     * be handled by simply ignoring the user input and sending an error message
     */
    public MissingArgumentException(Command type) {
        super(process(type));
    }

    private static String process(Command type) {
        switch (type) {
        case DELETE, MARK:
            return "Error! Please input a number.";
        case TODO:
            return "Error! Please input a task description.";
        case DEADLINE:
            return "Error! Please input description and by date.";
        case EVENT:
            return "Error! Please input description, from date, and to date.";
        case FIND:
            return "Error! Please input a keyword";
        case ARCHIVE:
            return "Error! Please input a file name shorter than 260 characters";
        default:
            return "";
        }
    }
}
