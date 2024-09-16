package thebotfather.util;

/**
 * The Ui class handles all interactions with the user.
 * It provides methods to read user input, print output to the console, and display
 * various messages during the execution of the program.
 */
public class Ui {

    public static final String LINE = "—————————————————————————————————————";

    /**
     * Prints a message with a leading indentation of four spaces.
     *
     * <p>This method prepends four spaces to the given string and outputs it
     * to the console using {@code System.out.println}.</p>
     *
     * @param str the message to be printed, with an added indentation.
     */
    private void print(String str) {
        System.out.println("    " + str);
    }

    /**
     * Returns a greeting message to the user, which includes a decorative line.
     *
     * @return A string containing the greeting message and horse art.
     */
    public String getGreeting() {
        return LINE + "\nHello! I'm The BotFather\n"
                + "I’m gonna make you an offer you can’t refuse.\n"
                + LINE;
    }

    /**
     * Returns a goodbye message when the user exits the program.
     *
     * @return A string containing the goodbye message.
     */
    public String getGoodBye() {
        return "What are you worried about, if I wanted to kill you, you'd be dead already.\n";
    }

    /**
     * Prints an error message when there is an issue loading the tasks from storage.
     * This message is printed to the console using the {@code print} helper method.
     */
    public void showLoadingError() {
        print("There seems to have been some error loading the files, check properly");
    }

    /**
     * Returns a message for adding a "Todo" task.
     *
     * @return A string containing the message.
     */
    public String getTodoPrint() {
        return "Leave the gun, take the cannoli.\n";
    }

    /**
     * Returns a message for adding an "Event" task.
     *
     * @return A string containing the message.
     */
    public String getEventPrint() {
        return "That's my family, Kay, that's not me.\n";
    }

    /**
     * Returns a message for adding a "Deadline" task.
     *
     * @return A string containing the message.
     */
    public String getDeadlinePrint() {
        return "Look how they massacred my boy.\n";
    }

    /**
     * Prints an ASCII art representation of a horse.
     * This is used to add a unique visual element to the application.
     *
     * @return A string containing the ASCII art of the horse.
     */
    private String getHorse() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("                                 |\\    /|\n");
        stringBuilder.append("                              ___| \\,,/_/\n");
        stringBuilder.append("                           ---__/ \\/    \\\n");
        stringBuilder.append("                          __--/     (D)  \\\n");
        stringBuilder.append("                          _ -/    (_      \\\n");
        stringBuilder.append("                         // /       \\_ /  -\\\n");
        stringBuilder.append("   __-------_____--___--/           / \\_ O o)\n");
        stringBuilder.append("  /                                 /   \\__/\n");
        stringBuilder.append(" /                                 /\n");
        stringBuilder.append("||          )                   \\_/\\\n");
        stringBuilder.append("||         /              _      /  |\n");
        stringBuilder.append("| |      /--______      ___\\    /\\  :\n");
        stringBuilder.append("| /   __-  - _/   ------    |  |   \\ \\\n");
        stringBuilder.append(" |   -  -   /                | |     \\ )\n");
        stringBuilder.append(" |  |   -  |                 | )     | |\n");
        stringBuilder.append("  | |    | |                 | |    | |\n");
        stringBuilder.append("  | |    < |                 | |   |_/\n");
        stringBuilder.append("  < |    /__\\                <  \\\n");
        stringBuilder.append("  /__\\                       /___\\\n");
        return stringBuilder.toString();
    }
}
