package chatbot.util;

public class UserInterface {
    private KillJoy kj;

    private String welcomeString = "    Hello! I'm KillJoy\n    I'm GENIUS!!!\n    What can I do for you?\n" +
            "    ------------------------------------";
    private String exitString = "    ------------------------------------\n" +
            "    Bubyyeee & Don't Stwesszz. Time to hide now!!\n" +
            "    ------------------------------------";
    private String logoString = "    ------------------------------------\n" +
            "     _  ___ _ _      _\n" +
            "    | |/ (_) | |    | |\n" +
            "    | ' / _| | |    | | ___  _   _\n" +
            "    |  < | | | |_   | |/ _ \\| | | |\n" +
            "    | . \\| | | | |__| | (_) | |_| |\n" +
            "    |_|\\_\\_|_|_|\\____/ \\___/ \\__, |\n" +
            "                              __/ |\n" +
            "                             |___/\n" +
            "    ------------------------------------";
    private String markString = "Ayee Yooo! I've marked this task okaayyyyy:";
    private String unmarkString = "Yo big guy! I've unmarked this for you:";
    private String deleteString = "Okay! I'll remove this task:";

    public UserInterface(KillJoy kj) {
        this.kj = kj;
    }

    public String getMarkString() {
        return markString;
    }

    public String getUnmarkString() {
        return unmarkString;
    }

    public String getDeleteString() {
        return deleteString;
    }

    public String getExitString() {
        return exitString;
    }

    public String getWelcomeString() {
        return welcomeString;
    }

    public String getLogoString() {
        return logoString;
    }

    public void printLine() {
        System.out.println("    ------------------------------------");
    }

    public void printTaskList() {
        this.printLine();
        System.out.println("    Here are your tasks lah!! Don't die:");
        for (int i = 0; i < kj.getTaskCount(); i++) {
            System.out.println("    " + (i + 1) + ". " + kj.getTask(i));
        }
        this.printLine();
    }

    public void displayNoStringMessage() {
        this.printLine();
        System.out.println("    WHHHAHAHAHHA!! You didn't enter anything siaaa!!!");
        this.printLine();
    }

    public void displayEnterNumberMessage() {
        this.printLine();
        System.out.println("    MAOHWAHAWK !! Enter the task number too!!!");
        this.printLine();
    }

    public void displayInvalidCommandFormatMessage() {
        System.out.println("    DANGER !! Invalid command format");
    }

    public void displayTaskDoesNotExistMessage() {
        this.printLine();
        System.out.println("    DANGER !! Task does not exist");
        this.printLine();
    }

    public void displayUnknownCommandMessage() {
        this.printLine();
        System.out.println("    AGWHAHH!!! What'ya sayin' dawgg??");
        this.printLine();
    }


    public void displayAddedTaskMessage() {
        System.out.println("    Yo Dawgg!! Added this task:");
        System.out.println("    " + kj.getTask(kj.getTaskCount() - 1));
        if (kj.getTaskCount() == 1) {
            System.out.println("    Now you have " + kj.getTaskCount() + " task in the list.");
        } else {
            System.out.println("    Now you have " + kj.getTaskCount() + " tasks in the list.");
        }
        this.printLine();
    }

}
