package taskon.common;

/**
 * Contains all user-visible messages for the Taskon application.
 * The `Messages` class provides a central location for all the messages
 * that can be displayed to the user, ensuring consistency and making it
 * easier to update or modify the messages.
 */
public class Messages {
    public static final String MESSAGE_GREETING = "Hello! I'm Taskon\nWhat can I do for you?\n";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!\n";
    public static final String MESSAGE_MARK = "Woohoo! task.Task complete! \nI've marked this as done:\n";
    public static final String MESSAGE_UNMARK = "Got it! No rush, I've marked it as not done yet:\n";
    public static final String MESSAGE_NO_TASKS = "Hmm, it looks like you've got a free day!\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n";
    public static final String MESSAGE_DESCRIPTION_MISSING = "It looks like you forgot to add a description. "
            + "Please try again.\n";
    public static final String MESSAGE_DATE_MISSING = "It looks like you forgot to add a date. Please try again.\n";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date! Please use yyyy-MM-dd HHmm format!\n";
    public static final String MESSAGE_INVALID_INTEGER = "Invalid task number!\n";
    public static final String MESSAGE_EMPTY_FIND = "No matching tasks found!\n";
}
