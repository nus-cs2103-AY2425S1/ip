package killjoy.main;

/**
 * Represents the UserInterface class of the KillJoy application.
 * Contains methods to interact with the user.
 */
public class UserInterface {
    private static final String WELCOME_STRING = "Hello! I'm KillJoy\nI'm GENIUS!!!\n" + "What can I do for you?";
    private static final String EXIT_STRING = "Bubyyeee & Don't Stwesszz. Time to hide now!!";

    private static final String MARK_STRING = "Ayee Yooo! I've marked this task okaayyyyy:";
    private static final String UNMARK_STRING = "Yo big guy! I've unmarked this for you:";
    private static final String DELETE_STRING = "Okay! I'll remove this task:";
    private KillJoy killJoy;

    public UserInterface(KillJoy killJoy) {
        this.killJoy = killJoy;
    }

    public static String getMarkString() {
        return MARK_STRING;
    }

    public static String getUnmarkString() {
        return UNMARK_STRING;
    }

    public static String getDeleteString() {
        return DELETE_STRING;
    }

    public static String getExitString() {
        return EXIT_STRING;
    }

    public static String getWelcomeString() {
        return WELCOME_STRING;
    }

    public static String displayTaskArchivedMessage() {
        return "Task has been archived!";
    }

    public static String displayTaskLoadedMessage() {
        return "Tasks have been loaded!";
    }

    public static String displayFileNotFoundMessage() {
        return "File not found!!";
    }

    /**
     * Prints the task list.
     */
    public String printTaskList() {
        if (killJoy.getTaskCount() == 0) {
            return "You have no tasks lah!!";
        }
        String str = "Here are your tasks lah!! Don't die:\n";
        for (int i = 0; i < killJoy.getTaskCount(); i++) {
            str += (i + 1) + ". " + killJoy.getTask(i) + "\n";
        }
        return str;
    }

    /**
     * Displays the message when the user does not enter anything.
     */
    public static String displayNoStringMessage() {
        return ("WHHHAHAHAHHA!! You didn't enter anything siaaa!!!");
    }
    /**
     * Displays the message when the user enters an invalid date format.
     */
    public static String displayEnterNumberMessage() {
        return ("MAOHWAHAWK !! Enter the task number too!!!");
    }

    /**
     * Displays the message when the user enters an invalid command format.
     */
    public static String displayInvalidCommandFormatMessage() {
        return ("DANGER !! Invalid command format");
    }

    /**
     * Displays the message when the user enters an invalid date format.
     */
    public static String displayTaskDoesNotExistMessage() {
        return ("DANGER !! Task does not exist");
    }
    /**
     * Displays the message when the user enters an invalid date format.
     */
    public static String displayUnknownCommandMessage() {
        return ("AGWHAHH!!! What'ya sayin' dawgg??");
    }

    /**
     * Displays the message when a task is added.
     */
    public String displayAddedTaskMessage() {
        String str = "Yo Dawgg!! Added this task:\n";
        str += killJoy.getTask(killJoy.getTaskCount() - 1) + "\n";
        if (killJoy.getTaskCount() == 1) {
            str += "Now you have " + killJoy.getTaskCount() + " task in the list.";
        } else {
            str += "Now you have " + killJoy.getTaskCount() + " tasks in the list.";
        }
        return str;
    }

}
