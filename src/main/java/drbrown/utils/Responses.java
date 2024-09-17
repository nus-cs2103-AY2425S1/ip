package drbrown.utils;

/**
 * A utility class that provides predefined response messages for various exceptions and errors
 * that might occur while using the application. These messages are inspired by famous quotes
 * and themes from "Back to the Future."
 */
public class Responses {

    /** Response message for an invalid input exceeding expected length. */
    private static String byeException = "Whoa, hold on! You've written more letters than necessary! "
            + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!";

    /** Response message for missing a description in a to-do command. */
    private static String todoExceptionNoDescription = "Great Scott! You can't add a to-do without a "
            + "description!\n\nUse the format: todo {description} /priority {priority}";

    /** Response message for missing priority in a to-do command. */
    private static String todoExceptionNoPriority = "Whoa, this priority is heavy! Set it to 1, 2, or 3 to "
            + "keep the timeline intact! Use the format: todo {description} /priority {priority}";

    /** Response message for other to-do related errors. */
    private static String todoExceptionOthers = "Great Scott! You can't add a to-do without a description!"
            + "\n\nUse the format: todo {description} /priority {priority}";

    /** Response message for missing description or date in a deadline command. */
    private static String deadlineExceptionNoDescription = "Great Scott! You can't add a deadline without a "
            + "description and date!\nUse the format: deadline {description} /by "
            + "{date} /priority {priority}";

    /** Response message for missing date in a deadline command. */
    private static String deadlineExceptionNoDate = "Hello? Hello? Anybody home? Looks like something's missing "
            + "here!\nUse the format: deadline {description} /by {date} /priority {priority}";

    /** Response message for other deadline related errors. */
    private static String deadlineExceptionOthers = "Looks like your Uncle Joey didn't make parole again... "
            + "and you missed the date! Let's fix that deadline!\nUse the format: deadline "
            + "{description} /by {MMM dd yyyy HH:mm} /priority {priority}";

    /** Response message for missing index in a delete command. */
    private static String deleteExceptionNoIndex = "You can't erase something from history without a count!\n"
            + "Use the format: delete {count}";

    /** Response message for missing description or date in an event command. */
    private static String eventExceptionNoDescription = "Great Scott! You can't add an event without a description "
            + "and from and to date!\nUse the format: "
            + "event {description} /from {date} /to {date} /priority {priority}";

    /** Response message for missing date in an event command. */
    private static String eventExceptionNoDate = "Looks like your Uncle Joey didn't make parole again... "
            + "and you missed the date! Let's fix that event!\nUse the format: "
            + "event {description} /from {date} /to {date} /priority {priority}";

    /** Response message for other event related errors. */
    private static String eventExceptionOthers = "Looks like your Uncle Joey didn't make parole again... "
            + "and you missed the date! Let's fix that event!\nUse the format: "
            + "event {description} /from {MMM dd yyyy HH:mm} /to {MMM dd yyyy HH:mm} /priority {priority}";

    /** Response message for an invalid non-numeric input. */
    private static String exceptionNotNumber = "That's not a number! Without the right input, we're never going to get "
            + "this DeLorean off the ground!";

    /** Response message for an input that is too short. */
    private static String findException = "Whoa, hold on! You've written too few letters than necessary! "
            + "It's like trying to fit a flux capacitor into a toaster – it just doesn't belong!";

    /** Response message for an input that is too long. */
    private static String listException = "Whoa, hold on! You've written more letters than necessary! "
            + "It's like trying to fit a flux capacitor into a toaster - it just doesn't belong!";

    /** Response message for missing index in a mark command. */
    private static String markException = "Great Scott! You can't complete a task without a count!\n"
            + "Use the format: mark {count}";

    /** Response message for missing index in an unmark command. */
    private static String unmarkException = "Great Scott! You can't go back in time without a count!\n"
            + "Use the format: unmark {count}";

    /**
     * Returns the response message for an input that is too long.
     *
     * @return The bye exception message.
     */
    public static String getByeException() {
        return byeException;
    }

    /**
     * Returns the response message for missing a description in a to-do command.
     *
     * @return The to-do exception message for no description.
     */
    public static String getTodoExceptionNoDescription() {
        return todoExceptionNoDescription;
    }

    /**
     * Returns the response message for missing a priority in a to-do command.
     *
     * @return The to-do exception message for no priority.
     */
    public static String getTodoExceptionNoPriority() {
        return todoExceptionNoPriority;
    }

    /**
     * Returns the response message for other to-do related errors.
     *
     * @return The to-do exception message for other errors.
     */
    public static String getTodoExceptionOthers() {
        return todoExceptionOthers;
    }

    /**
     * Returns the response message for missing a description or date in a deadline command.
     *
     * @return The deadline exception message for no description.
     */
    public static String getDeadlineExceptionNoDescription() {
        return deadlineExceptionNoDescription;
    }

    /**
     * Returns the response message for missing a date in a deadline command.
     *
     * @return The deadline exception message for no date.
     */
    public static String getDeadlineExceptionNoDate() {
        return deadlineExceptionNoDate;
    }

    /**
     * Returns the response message for other deadline related errors.
     *
     * @return The deadline exception message for other errors.
     */
    public static String getDeadlineExceptionOthers() {
        return deadlineExceptionOthers;
    }

    /**
     * Returns the response message for missing an index in a delete command.
     *
     * @return The delete exception message for no index.
     */
    public static String getDeleteExceptionNoIndex() {
        return deleteExceptionNoIndex;
    }

    /**
     * Returns the response message for missing a description or date in an event command.
     *
     * @return The event exception message for no description.
     */
    public static String getEventExceptionNoDescription() {
        return eventExceptionNoDescription;
    }

    /**
     * Returns the response message for missing a date in an event command.
     *
     * @return The event exception message for no date.
     */
    public static String getEventExceptionNoDate() {
        return eventExceptionNoDate;
    }

    /**
     * Returns the response message for other event related errors.
     *
     * @return The event exception message for other errors.
     */
    public static String getEventExceptionOthers() {
        return eventExceptionOthers;
    }

    /**
     * Returns the response message for an invalid non-numeric input.
     *
     * @return The exception message for not a number.
     */
    public static String getExceptionNotNumber() {
        return exceptionNotNumber;
    }

    /**
     * Returns the response message for an input that is too short.
     *
     * @return The find exception message.
     */
    public static String getFindException() {
        return findException;
    }

    /**
     * Returns the response message for an input that is too long.
     *
     * @return The list exception message.
     */
    public static String getListException() {
        return listException;
    }

    /**
     * Returns the response message for missing an index in a mark command.
     *
     * @return The mark exception message.
     */
    public static String getMarkException() {
        return markException;
    }

    /**
     * Returns the response message for missing an index in an unmark command.
     *
     * @return The unmark exception message.
     */
    public static String getUnmarkException() {
        return unmarkException;
    }
}
