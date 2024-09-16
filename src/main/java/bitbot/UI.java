package bitbot;

/**
 * This UI class is to store the intro and the conclusions for the user interface.
 */
public class UI {
    /**
     * This is the introduction paragraph that the user sees
     * when the code is first started.
     */
    private final String intro = "          ____________________________________\n          Hello! I'm BitBot\n"
            + "          What can I do for you?\n          ____________________________________\n"
            + "          Please key in only one of these keywords:\n          "
            + "\n          "
            + "mark \n          "
            + "unmark \n          "
            + "todo \n          "
            + "deadline \n          "
            + "event\n          "
            + "list\n          "
            + "tag\n          "
            + "untag\n          "
            + "delete\n          "
            + "bye\n          "
            + "\n          "
            + "Please key any one of the keywords above in this format:\n          "
            + "todo ... / deadline ...\n          "
            + "\n          "
            + "When keying in the date / time, do so in one of these formats:\n          "
            + "dd-MM-yyyy HH:mm or just dd-MM-yyyy or just HH:mm\n          "
            + "\n          "
            + "For tag, key it in this format: tag <number> #<tag name>\n          "
            + "For untag, key it in this format: untag <number>\n          "
            + "\n          "
            + "For new users, please type in one of the keywords to begin :)\n          "
            + "For existing users, you can view your list and start completing your assignments!\n          "
            + "____________________________________";

    private final String conclusion = "          ____________________________________\n"
            + "          Sad to see you go :( Thank you for using BitBot and hope you enjoyed my service!\n"
            + "          Don't worry, your tasks are saved and are safe with me :)"
            + "          The next time you come, you can complete your tasks!!!!."
            + "          Hope to see you again soon and goodbye!\n"
            + "          ____________________________________\n";

    public UI() {
    }

    public String getIntro() {
        return intro;
    }

    public String getConclusion() {
        return conclusion;
    }
}
