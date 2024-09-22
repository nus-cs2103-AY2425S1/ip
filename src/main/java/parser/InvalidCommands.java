package parser;

/**
 * Enum for invalid inputs.
 */
public enum InvalidCommands {
    INVALID_MARK_CMD("Are you acoustic? Give me a number! (mark/unmark [index])\n"),
    INVALID_DELETE_CMD("How can you not delete properly? (delete [index])\n"),
    INVALID_TASK_CMD("No grimace shake for you ([task_type] [task_name] [args])\n"),
    INVALID_INPUT("Sorry, I don't know that command\n"),
    INVALID_DEADLINE("What is the deadline?\n"),
    INVALID_EVENT("The event start/end time is not determined\n"),
    UNKNOWN_ERROR("I'm not sure what happened, but you messed up(You didn't hawk tuah and spit on that thang)\n");

    private final String errorMessage;

    InvalidCommands(String s) {
        errorMessage = s;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
