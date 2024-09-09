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
    public static final String MESSAGE_HELP = """
            Taskon Commands Guide:
             1. Add Task:
                - todo [description]
                - deadline [description] /by [date] (Format: yyyy-MM-dd HHmm)
                - event [description] /from [start date] /to [end date] (Format: yyyy-MM-dd HHmm)
             2. List Tasks: list
             3. Mark Task: mark [task number]
             4. Unmark Task: unmark [task number]
             5. Delete Task: delete [task number]
             6. Find Tasks: find [keyword]
             7. On Date: on [date] (Format: yyyy-MM-dd)
             8. Exit: bye
             Ensure task numbers and dates are correct.""";
}
