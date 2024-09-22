package panorama;

/**
 * The {@code Message} class contains constant strings that represent various messages and command formats used.
 */
public class Message {

    /**
     * The {@code MyException} class contains constant error messages used
     * throughout the application to describe various exceptions.
     */
    public class MyException {
        public static final String EMPTY_DESCRIPTION = "Description cannot be empty.";
        public static final String INVALID_DATE = "Invalid date specified.";
        public static final String UNKNOWN_COMMAND = "Unknown command.";
        public static final String NON_INTEGER = "The ID you provided is not an integer, or it is empty.";
        public static final String ID_OUT_OF_BOUNDS = "The ID you provided is does not belong to a task.";
        public static final String EMPTY_KEYWORD = "Search keyword cannot be empty.";
        public static final String INVALID_USAGE = "Invalid command usage.";
    }

    /**
     * The {@code CommandFormat} class contains constant strings that define
     * the format for various commands accepted by the application.
     */
    public class CommandFormat {
        // task listing
        public static final String LIST = "[l]ist";
        public static final String FIND = "[f]ind (description)";

        public static final String BYE = "[b]ye";
        public static final String HELP = "[h]elp";

        // task creation
        public static final String TODO = "[t]odo (description)";
        public static final String DEADLINE = "[d]eadline (description) /by (date_string)";
        public static final String EVENT = "[e]vent (description) /from (date_string) /to (date_string)";

        // task amendment
        public static final String MARK = "[m]ark (task_id)";
        public static final String UNMARK = "[u]nmark (task_id)";
        public static final String DELETE = "[del]ete (task_id)";
    }
}
