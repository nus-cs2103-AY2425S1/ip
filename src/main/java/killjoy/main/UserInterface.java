package killjoy.main;

/**
 * Represents the UserInterface class of the KillJoy application.
 * Contains methods to interact with the user.
 */
public class UserInterface {
    private KillJoy killJoy;

    private static final String WELCOME_STRING = "    Hello! I'm KillJoy\n    I'm GENIUS!!!\n    What can I do for you?\n" +
            "    ------------------------------------";
    private static final String EXIT_STRING = "    ------------------------------------\n" +
            "    Bubyyeee & Don't Stwesszz. Time to hide now!!\n" +
            "    ------------------------------------";
    private static final String LOGO_STRING = "    ------------------------------------\n" +
            "     _  ___ _ _      _\n" +
            "    | |/ (_) | |    | |\n" +
            "    | ' / _| | |    | | ___  _   _\n" +
            "    |  < | | | |_   | |/ _ \\| | | |\n" +
            "    | . \\| | | | |__| | (_) | |_| |\n" +
            "    |_|\\_\\_|_|_|\\____/ \\___/ \\__, |\n" +
            "                              __/ |\n" +
            "                             |___/\n" +
            "    ------------------------------------";
    private static final String MARK_STRING = "Ayee Yooo! I've marked this task okaayyyyy:";
    private static final String UNMARK_STRING = "Yo big guy! I've unmarked this for you:";
    private static final String DELETE_STRING = "Okay! I'll remove this task:";

    public UserInterface(KillJoy killJoy) {
        this.killJoy = killJoy;
    }

    public String getMarkString() {
        return MARK_STRING;
    }

    public String getUnmarkString() {
        return UNMARK_STRING;
    }

    public String getDeleteString() {
        return DELETE_STRING;
    }

    public String getExitString() {
        return EXIT_STRING;
    }

    public String getWelcomeString() {
        return WELCOME_STRING;
    }

    public String getLogoString() {
        return LOGO_STRING;
    }

    public void printLine() {
        System.out.println("    ------------------------------------");
    }

    public void printTaskList() {
        printLine();
        System.out.println("    Here are your tasks lah!! Don't die:");
        for (int i = 0; i < killJoy.getTaskCount(); i++) {
            System.out.println("    " + (i + 1) + ". " + killJoy.getTask(i));
        }
        printLine();
    }

    public void displayNoStringMessage() {
        printLine();
        System.out.println("    WHHHAHAHAHHA!! You didn't enter anything siaaa!!!");
        printLine();
    }

    public void displayEnterNumberMessage() {
        printLine();
        System.out.println("    MAOHWAHAWK !! Enter the task number too!!!");
        printLine();
    }

    public void displayInvalidCommandFormatMessage() {
        System.out.println("    DANGER !! Invalid command format");
    }

    public void displayTaskDoesNotExistMessage() {
        printLine();
        System.out.println("    DANGER !! Task does not exist");
        printLine();
    }

    public void displayUnknownCommandMessage() {
        printLine();
        System.out.println("    AGWHAHH!!! What'ya sayin' dawgg??");
        printLine();
    }

    public void displayAddedTaskMessage() {
        System.out.println("    Yo Dawgg!! Added this task:");
        System.out.println("    " + killJoy.getTask(killJoy.getTaskCount() - 1));
        if (killJoy.getTaskCount() == 1) {
            System.out.println("    Now you have " + killJoy.getTaskCount() + " task in the list.");
        } else {
            System.out.println("    Now you have " + killJoy.getTaskCount() + " tasks in the list.");
        }
        printLine();
    }

}
