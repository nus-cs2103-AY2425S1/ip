package yappingbot.stringconstants;

/**
 * String constants of messages that the bot can use to reply.
 */
public final class ReplyTextMessages {
    public static final String NOT_ENOUGH_ARGUMENTS = "Not enough Arguments!";
    // Text strings
    private static final String BOT_NAME = "YappingBot";
    public static final String GREETING_TEXT = String.format(
            "Hello! I'm %s\nWhat can I do for you?",
            BOT_NAME
    );
    public static final String HELP_TEXT = "Available commands: list, mark, unmark, todo, event, "
                                           + "deadline, find.";
    public static final String UNKNOWN_COMMAND_TEXT_1s = "I'm sorry, I do not understand what "
                                                         + "'%s' is!\n";
    public static final String LIST_TEXT = "Here are the tasks in your list:";
    public static final String ADDED_TEXT = "Got it. I've added this task:";
    public static final String RESET_TEXT = "Alright. Back to main view of whole list.";
    public static final String DELETED_TEXT = "Noted. I've deleted this task:";
    public static final String DELETE_USAGE =
            """
                    Here is the usage for the instruction 'delete':
        
                        delete TASK_NUMBER
        
                    where TASK_NUMBER is the task number in the task list to delete""";
    public static final String TODO_USAGE =
            """
                    Here is the usage for the instruction 'todo':

                        todo TASK_NAME

                    where TASK_NAME is the name of this todo task to add""";
    public static final String DEADLINE_USAGE =
            """
                    Here is the usage for the instruction 'deadline':

                        deadline TASK_NAME /by DEADLINE

                    where TASK_NAME is the name of this deadline task to add
                          DEADLINE  is the deadline for this task""";
    public static final String EVENT_USAGE =
            """
                    Here is the usage for the instruction 'event':

                        event TASK_NAME /from START_DATE /to END_DATE

                    where TASK_NAME  is the name of this event task to add
                          START_DATE is the start time/date for this event
                          END_DATE   is the end time/date for this event""";
    public static final String TASK_PRINT_TEXT_3s = "[%s][%s] %s";
    public static final String LIST_SUMMARY_TEXT_1d =
            "Now you have %d tasks in the list.";
    public static final String SELECT_TASK_NOT_INT_TEXT_1s =
            "I'm sorry, I do not understand which item '%s' refers to!";
    public static final String SELECT_TASK_MISSING_TEXT_1d =
            "I'm sorry, but task number %d does not exist!";
    public static final String MARKED_TASK_AS_DONE_TEXT =
            "Nice! I've marked this task as done:";
    public static final String MARK_INSTRUCTION_USAGE =
            """
                    Here is the usage for the instruction 'unmark':

                        mark TASK_NUMBER

                    where TASK_NUMBER is the task number in the task list""";
    public static final String UNMARKED_TASK_AS_DONE_TEXT =
            "OK, I've marked this task as not done:";
    public static final String UNMARK_INSTRUCTION_USAGE =
            """
                    Here is the usage for the instruction 'unmark':

                        unmark TASK_NUMBER

                    where TASK_NUMBER is the task number in the task list""";
    public static final String EXIT_TEXT = "Bye. Hope to see you again soon! (You may close this "
                                           + "window)";

    public static final String SAVE_FILE_NOT_FOUND_EXCEPTION = "Savefile not found...\nBeginning "
                                                               + "with blank list";
    public static final String INVALID_SAVE_FILE_EXCEPTION_1s = "Error Reading save file! The "
                                                                + "following error was "
                                                                + "encountered: %s";
    public static final String INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES = "Missing Data Values";
    public static final String INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s = "Unable to parse "
                                                                               + "value - %s";
    public static final String SAVEFILE_IO_EXCEPTION_1s = "Unable to save list to disk - %s";
    public static final String IO_EXCEPTION_1s = "Error dealing with input / output - %s";
    public static final String TIME_PARSE_HINT = "Time value must be given in any valid date-time"
                                                 + " format!";
    public static final String SAVE_FILE_ERROR_1s = "Error encountered while saving savefile:\n%s";
    public static final String LOAD_FILE_ERROR_1s = "Error encountered while loading savefile:\n%s";

    public static final String FIND_STRING_INIT_1s = "Searching for '%s'.";
    public static final String FIND_STRING_FAIL_1s = "Sorry, but I could not find '%s' in any "
                                                     + "tasks.";
    public static final String FIND_STRING_FOUND_1d_1s = "Found %d tasks with '%s' (Type reset "
                                                         + "to return to default view)";

}
