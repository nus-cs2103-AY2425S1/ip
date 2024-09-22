package nah.gui;

/**
 * This class is to generate to response for Help Window
 */
public class HelpWindowResponse {
    private static final String BYE_RESPONSE = " That command is for exiting the chatBot Nah";
    private static final String CLEAN_RESPONSE = " That command is for cleaning the storage of Nah";
    private static final String LIST_RESPONSE = " That command is for listing all the tasks in Nah's storage";
    private static final String UNKNOWN_RESPONSE =
            " Nah do not recognise that. Please type 'help' for more instructions";
    private static final String FORMAT_OPENING = " The format for this command is:\n";
    private static final String FIND_FORMAT = FORMAT_OPENING
            + " Find 'one or more words')";
    private static final String MARK_FORMAT = FORMAT_OPENING
            + " Mark 'ordinal number of the task'";
    private static final String UNMARK_FORMAT = FORMAT_OPENING
            + " Unmark 'ordinal number of the task'";
    private static final String DELETE_FORMAT = FORMAT_OPENING
            + " Delete 'ordinal number of the task'";
    private static final String DUE_ON_FORMAT = FORMAT_OPENING
            + " DueOn yyyy-mm-dd HHmm";
    private static final String TODO_FORMAT = FORMAT_OPENING
            + " Todo 'description'";
    private static final String DEADLINE_FORMAT = FORMAT_OPENING
            + " Deadline 'description' /by yyyy-mm-dd HHmm";
    private static final String EVENT_FORMAT = FORMAT_OPENING
            + "Event 'description' /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm";
    private static final String HELP_LINE = " > These are Nah's single word command:\n"
            + " 1.Bye : to exit the program\n"
            + " 2.List : to list the tasks in the storage\n"
            + " 3.Clean : to clean the storage\n"
            + " > Or type one of these key words to get the corresponding command format:\n"
            + " 1.Find : to find the matching tasks\n"
            + " 2.DueOn : to find the uncompleted tasks that before due\n"
            + " 3.Mark : to mark the corresponding task as done\n"
            + " 4.Unmark : to mark the corresponding task as not done\n"
            + " 5.Delete : to delete the task\n"
            + " 6.Todo : to add a todo task\n"
            + " 7.Deadline : to add a deadline task\n"
            + " 8.Event : to add an event task\n"
            + " > Or type 'exit' to close Help Window ^:\n";



    /**
     * creates response for the HelpWindow of chatBot from the input
     * @param s the input
     * @return the response
     */
    public static String responseTo(String s) {

        switch (s.toLowerCase()) {
        case "exit": {
            return "exit";
        }
        case "bye": {
            return BYE_RESPONSE;
        }
        case "list": {
            return LIST_RESPONSE;
        }
        case "clean": {
            return CLEAN_RESPONSE;
        }
        case "help": {
            return HELP_LINE;
        }
        case "find": {
            return FIND_FORMAT;
        }
        case "mark": {
            return MARK_FORMAT;
        }
        case "unmark": {
            return UNMARK_FORMAT;
        }
        case "delete": {
            return DELETE_FORMAT;
        }
        case "dueone": {
            return DUE_ON_FORMAT;
        }
        case "todo": {
            return TODO_FORMAT;
        }
        case "deadline": {
            return DEADLINE_FORMAT;
        }
        case "event": {
            return EVENT_FORMAT;
        }
        default: {
            return UNKNOWN_RESPONSE;
        }
        }
    }
}
