package hypebot.common;

/**
 * Represents all custom text used to communicate to user on user interface.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Messages {
    public static final String LOGO = """
            ┏┓┏┓╋╋╋╋╋┏┓╋╋┏┓
            ┃┗┛┣┳┳━┳━┫┗┳━┫┗┓
            ┃┏┓┃┃┃╋┃┻┫╋┃╋┃┏┫
            ┗┛┗╋┓┃┏┻━┻━┻━┻━┛
            ╋╋╋┗━┻┛
            """;
    public static final String MESSAGE_HELP = """
            YOU CAN LEAN ON ME! Lemme show you around the ropes bro:
            \s
            • Type '{todo} {Enter name here}' to add a TERRIFIC todo with no date or time associated with it!
            • Type '{deadline} {Enter name here} /{Enter deadline in yyyy-MM-dd format here}' \
            to add a DELIGHTFUL deadline task with a due date!
            • Type 'event {Enter name here} /{Enter start time in yyyy-MM-dd HHmm format here} \
            /{Enter end time in yyyy-MM-dd HHmm format here}'
              to add an EXCITING event with start and end times!
            • Type 'list' to see ALL YOUR SWEET TASKS in ADDED ORDER (earliest to latest)!
            • Type 'mark {Enter task number here}' to mark a task as CONQUERED!
            • Type 'unmark {Enter task number here}' to unmark a task and TAKE IT ON AGAIN!
            • Type 'delete {Enter task number here}' to ANNIHILATE a task from your list!
            • Type 'deleteall' to ANNIHILATE ALL tasks from your list!
            • Type 'happening /{Enter date in yyyy-MM-dd format here}' to search any deadlines or events \
            happening on that day!
            • Type 'find {Enter keywords here}' to find any tasks with any of the keywords!
            • Type 'bye' if you're all set!
            """;
    public static final String MESSAGE_GREET_INTRO = "AYO WHAT'S UP IT'S ME YOUR\n\n";
    public static final String MESSAGE_GREET_OUTRO = "\nWhat can I do for you, my wonderful homie?\n";
    public static final String MESSAGE_LOADING_TASKLIST = "LOADING YOUR TASKS from your local computer...\n";
    public static final String MESSAGE_DELETING_PAST_DEADLINE = "Deleting past deadline...\n";
    public static final String MESSAGE_DELETING_PAST_EVENT = "Deleting past event...\n";
    public static final String MESSAGE_SAVING_TASKLIST = "Alright homie, saving your tasks to your drive...\n";
    public static final String MESSAGE_LIST = "ALRIGHT, Here's that list!\n";
    public static final String MESSAGE_HAPPENING = "ALRIGHT, Here's everything that's going down on ";
    public static final String MESSAGE_FIND_INTRO = "Searching every valley low and every mountain high for ";
    public static final String MESSAGE_ADDED_TASK = "HECK YEAH, ADDED:\n  ";
    public static final String MESSAGE_DELETED_TASK = "Say no more, BABY BYE BYE BYE to this task:\n ";
    public static final String MESSAGE_DELETED_ALL_TASKS = "Say no more, BABY BYE BYE BYE to all your tasks!\n";
    public static final String MESSAGE_MARKED_TASK = "AIGHT, ABSOLUTELY CONQUERED THIS TASK:\n  ";
    public static final String MESSAGE_UNMARKED_TASK = "AIGHT, LET'S GET READY TO CONQUER THIS TASK:\n  ";
    public static final String MESSAGE_TASKS_LEFT_INTRO = "!\nYOU'VE NOW GOT ";
    public static final String MESSAGE_TASKS_LEFT_OUTRO = " TASKS TO GO!\n";
    public static final String MESSAGE_UNKNOWN_INTRO = "but I don't think we're vibing when you say '";
    public static final String MESSAGE_UNKNOWN_OUTRO = "'.\nMind if I ask you for anything else, homie?\n";
    public static final String MESSAGE_EXIT = """
            Alright homie, it's been a BLAST hanging out with you. \
            Have a wonderful day, and catch you soon again you ABSOLUTE BALLER!
            """;
    public static final String ERROR_INTRO = "I might be tripping bro, my bad, my bad - \n";
    public static final String ERROR_LOCATING_TASKLIST = "but I couldn't find the file with your saved tasks: \n";
    public static final String ERROR_LOAD_TASK = "but I couldn't decode a task from your file!\n\n";
    public static final String ERROR_FIX_CORRUPTED_TASK = """
            \n
            You can try closing this application first, locating your save file for your tasks \
            and fixing the format accordingly.
            """;
    public static final String ERROR_INVALID_TASK_TYPE = "I couldn't tell what task type this stands for: ";
    public static final String ERROR_TASK_MARK_INVALID = "The save file should indicate with 0 or 1 in the second "
            + "column to indicate the task as incomplete or complete. I found: ";
    public static final String ERROR_SAVE_TASKLIST = "but I couldn't find the file to save your tasks.\n";
    public static final String ERROR_TASK_NAME_EMPTY = "drop the name of the task, bro I gotta know!\n";
    public static final String ERROR_MARK_TASK_INDEX_MISSING = "try indicating the index of the task you wanna "
            + "mark CONQUERED as a number!\n";
    public static final String ERROR_MARK_TASK_INDEX_OUT_OF_BOUNDS = "try indicating the index of an existing task "
            + "you wanna mark CONQUERED!\n";
    public static final String ERROR_UNMARK_TASK_INDEX_MISSING = "try indicating the index of the task you wanna "
            + "TAKE ON AGAIN as a number!\n";
    public static final String ERROR_UNMARK_TASK_INDEX_OUT_OF_BOUNDS = "try indicating the index of an existing "
            + "task you wanna TAKE ON AGAIN!\n";
    public static final String ERROR_DELETE_TASK_INDEX_MISSING = "try indicating the index of the task you wanna "
            + "delete as a number!\n";
    public static final String ERROR_DELETE_TASK_INDEX_OUT_OF_BOUNDS = "try indicating the index of an existing "
            + "task you wanna delete!\n";
    public static final String ERROR_DEADLINE_DATE_MISSING = """
            make sure you got the due date for that SWAGGIN' \
            deadline you got!
            Put your due date after a '/' to indicate that you're inputting a due date!
            """;
    public static final String ERROR_DEADLINE_DATE_WRONG_FORMAT = """
            but I couldn't catch that due date.
            Try formatting your date in this format: yyyy-MM-dd
            """;
    public static final String ERROR_DATE_PASSED = "but this deadline or event is GONE WITH THE WIND!\n";
    public static final String ERROR_EVENT_TIME_MISSING = """
            make sure you got that start time AND end time for \
            that AWESOME event you got!
            Put a '/' before your start time and end time to indicate you're inputting a time!
            """;
    public static final String ERROR_EVENT_TIME_WRONG_FORMAT = """
            but I couldn't catch the dates you put.
            Try formatting your dates in this format: yyyy-MM-dd HHmm
            """;
    public static final String ERROR_EVENT_TIMES_INORDERED = "but your start time and end time aren't adding up!\n";
    public static final String ERROR_HAPPENING_DATE_MISSING = """
            make sure you got the date you're searching for!
            Put your due date after a '/' to indicate that you're inputting a search date!
            """;
    public static final String ERROR_HAPPENING_DATE_WRONG_FORMAT = """
            but I couldn't catch the search date that you put.
            Try formatting your due date in this format: yyyy-MM-dd
            """;
    public static final String ERROR_SEARCH_QUERY_EMPTY = "make sure to enter in some keywords in the name "
            + "of the tasks you're searching for!\n";
    public static final String ERROR_DUPLICATE_TASK = "but that task has already been hanging around with us! "
            + "See task number ";
}
