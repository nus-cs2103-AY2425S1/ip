package taskon.common;

/**
 * Contains all user-visible messages for the Taskon application.
 * The `Messages` class provides a central location for all the messages
 * that can be displayed to the user, ensuring consistency and making it
 * easier to update or modify the messages.
 */
public class Messages {
    public static final String MESSAGE_GREETING = "Ahoy there, matey!\nI'm ready! I'm ready! I'm ready!\n"
            + "What can I do for ya?\n";
    public static final String MESSAGE_EXIT = "Aww, barnacles! Bye, and don't forget to have a super-duper day!\n";
    public static final String MESSAGE_MARK = "Whoo-hoo! Task complete! Time to do the victory screech! "
            + "I've marked this one as done:\n";
    public static final String MESSAGE_UNMARK = "Got it! No rush, I've marked it as not done yet:\n";
    public static final String MESSAGE_NO_TASKS = "Hmm, it looks like you've got a free day! Time for jellyfishing!\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Tartar sauce! That command didn't quite work. "
            + "Try again, partner!\n\n";
    public static final String MESSAGE_DESCRIPTION_MISSING = "It looks like you forgot to add a description. "
            + "Try again, just like making the perfect Krabby Patty!\n";
    public static final String MESSAGE_DATE_MISSING = "Oh no! It looks like you forgot to add a date. "
            + "Make sure you add it in, or else we're gonna be late for jellyfishing!\n";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Huh? That date's all kelpy! "
            + "Use the format yyyy-MM-dd HHmm. Let’s try again!\n";
    public static final String MESSAGE_INVALID_INTEGER = "Whoa there! That task number looks fishy. "
            + "Make sure it's right!\n";
    public static final String MESSAGE_EMPTY_FIND = "No tasks found... like a jellyfish escaping my net! "
            + "Let's search again!\n";
    public static final String MESSAGE_HELP = """
            Taskon Commands Guide (Arr matey, here's how ye do it!):
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
             8. Edit Tasks: edit [task number] [description/deadline/start/end] [new value]
             9. Exit: bye
              Make sure your task numbers and dates are shipshape!""";
    public static final String MESSAGE_INVALID_EDIT = "Yikes, that edit didn't work out!\nPlease use:\n"
            + "edit [task number] [description/deadline/start/end] [new value] format.";
    public static final String MESSAGE_SUCCESSFUL_EDIT = "Aye aye, captain! Task successfully edited!\n";
}
