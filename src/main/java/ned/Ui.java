package ned;

import java.util.Scanner;

public class Ui {
    public static final String INDENTATIONS = "    ";

    private Scanner scannerInstance;
    private final String logo = Ui.INDENTATIONS + " ____  _____              __  \n"
            + Ui.INDENTATIONS + "|_   \\|_   _|            |  ] \n"
            + Ui.INDENTATIONS + "  |   \\ | |  .---.   .--.| |  \n"
            + Ui.INDENTATIONS + "  | |\\ \\| | / /__\\\\/ /'`\\' |  \n"
            + Ui.INDENTATIONS + " _| |_\\   |_| \\__.,| \\__/  |  \n"
            + Ui.INDENTATIONS + "|_____|\\____|'.__.' '.__.;__]";
    public final static String COMMAND_MESSAGE = "\n"
            + Ui.INDENTATIONS + "Usage: \n"
            + Ui.INDENTATIONS + "list                             - Shows the list of all tasks\n"
            + Ui.INDENTATIONS + "<item-index>                - Marks the item at the specified index as done\n"
            + Ui.INDENTATIONS + "unmark <item-index>              - Marks the item at the specified index as undone\n"
            + Ui.INDENTATIONS + "delete <item-index>              - Removes the item at the specified index from the " +
            "list\n"
            + Ui.INDENTATIONS + "todo <description>               - Creates a new todo task and adds it to the list\n"
            + Ui.INDENTATIONS + "<description> /by <date> - Creates a new deadline task and adds it to the list"
            + Ui.INDENTATIONS + "<description> /from <date> /to <date> - Creates a new event task and adds it to the " +
            "list";

    /**
     * Is a class responsible for displaying output to users and to take in input from them as well.
     */
    public Ui() {
        this.scannerInstance = new Scanner(System.in);
    }

    /**
     * Adds 1 tab of indentation and prints out provided input lines.
     *
     * @param line - String to be printed with 1 tab indentation.
     */
    public void print(String line) {
        //adds indentation to any printed lines
        System.out.println(Ui.INDENTATIONS + line);
    }

    /**
     * Displays a greeting to the user upon initialization of Ned.
     */
    public void showWelcomeMessage() {
        print("Hello! I'm\n" + logo + "\n");
        print("Lord of Winterfell and Warden Of The North");
        print("What can I do for you?");
    }

    /**
     * Displays a farewell message to the user upon the issuing of the 'bye' command.
     */
    public void showByeMessage() {
        print("I wish you good fortune in the wars to come, m' lord");
    }

    /**
     * Displays a message to the user if the cached tasks file is unable to be found.
     */
    public void showLoadingError() {
        print("M'lord, do not be alarmed, but it appears that there was no previous saved task file. Not to " +
                "worry, we'll sort this out yet...");
    }

    /**
     * Displays an overview of all the available commands and the parameters needed to execute each command, when
     * users use a command wrongly
     *
     * @return An overview of all commands as a string.
     */
    public String getCommandMessage() {
        return COMMAND_MESSAGE;
    }

    /**
     * Uses the associated scanner instance to read the next line.
     *
     * @return The next line as a string.
     */
    public String readCommand() {
        return this.scannerInstance.nextLine();
    }


}
