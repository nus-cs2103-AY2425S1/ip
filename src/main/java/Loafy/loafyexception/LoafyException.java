package loafy.loafyexception;

/**
 * Represents an exception specific to the Loafy program.
 */
public class LoafyException extends Exception {

    /**
     * Constructs a Loafy exception containing the specified error message.
     *
     * @param message Error message (for later retrieval by the Throwable.getMessage() method).
     */
    LoafyException(String message) {
        super(message);
    }

    /**
     * Returns a Loafy Exception for empty input from user.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofEmptyInput() {
        return new LoafyException(
                "Did you want to say something?");
    }

    /**
     * Returns a Loafy Exception for unspecified/invalid task id in 'mark', 'unmark' or 'delete' commands.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofInvalidAction() {
        return new LoafyException(
                "Commands 'mark', 'unmark', and 'delete' must be followed by a valid task number\n" +
                "eg. mark 1");

    }

    /**
     * Returns a Loafy Exception for unspecified task in 'todo' command.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofEmptyTodo() {
        return new LoafyException(
                "'todo' should be followed by a task\n" +
                "eg. todo wash dishes");
    }

    /**
     * Returns a Loafy Exception for unspecified task or end date in 'deadline' command.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofNoDeadline() {
        return new LoafyException(
                "It seems you did not specify the task or end date for your deadline\n" +
                "eg. deadline submit homework /by 30/8/2024 4pm");
    }

    /**
     * Returns a Loafy Exception for invalid format for 'event' command.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofNoEventDates() {
        return new LoafyException(
                "It seems you did not specify the name, start date, or end date for your event\n" +
                "eg. CS2103 lecture /from 30/8/2024 4pm /to 30/8/2024 6pm");
    }

    /**
     * Returns a Loafy Exception for an invalid command in user input.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofInvalidCommand() {
        return new LoafyException(
                "That is not in my vocabulary yet. ?_?\n" +
                "These are the list of commands I can understand:\n" +
                "todo [TASK NAME]\n" +
                "deadline [TASK NAME] /by [DEADLINE]\n" +
                "event [TASK NAME] /from [START DATE] /to [END DATE]\n" +
                "mark [TASK NUMBER]\n" +
                "unmark [TASK NUMBER}\n" +
                "list");
    }

    /**
     * Returns a Loafy Exception for invalid formatting of dates.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofWrongDateFormat() {
        return new LoafyException(
                "Date format should be written as D/M/YYYY HHMM\n" +
                "eg. 1/9/2024 2359");
    }

    /**
     * Returns a Loafy Exception for errors in loading tasks from previous program runs.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofLoadingError() {
        return new LoafyException(
                "Error: Cannot load storage file :(\n" +
                "Temporary task list created...\n" +
                "** Tasks will not be saved to hard drive **");
    }

    /**
     * Returns a Loafy Exception for unspecified keyword in 'find' command.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofEmptyFind() {
        return new LoafyException(
                "'find' should be followed by a keyword\n" +
                "eg. find dishes");
    }
    
    /**
     * Returns a Loafy Exception for data file contents not being in the expected format.
     *
     * @return A new Loafy Exception.
     */
    public static LoafyException ofCorruptedList() {
        return new LoafyException(
                "Error: Corrupted storage file :(\n" +
                "New task list created...");
    }
}
