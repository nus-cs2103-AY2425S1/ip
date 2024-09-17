package arona.AronaExceptions;

import arona.Command;

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
            return "Sensei, tell me a task number!";
        case TODO:
            return "Please input a task description, Sensei!";
        case DEADLINE:
            return "Please input description and by date, Sensei!";
        case EVENT:
            return "Please input description, from date, and to date, Sensei!";
        case FIND:
            return "Sensei, tell me a keyword to look for!";
        case ARCHIVE:
            return "Please input a file name shorter than 260 characters, Sensei!";
        default:
            return "";
        }
    }
}
