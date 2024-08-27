package Default;

public class Ui {
    public static final String INDENTATIONS = "    ";
    private final String logo = Ui.INDENTATIONS + " ____  _____              __  \n"
            + Ui.INDENTATIONS + "|_   \\|_   _|            |  ] \n"
            + Ui.INDENTATIONS + "  |   \\ | |  .---.   .--.| |  \n"
            + Ui.INDENTATIONS + "  | |\\ \\| | / /__\\\\/ /'`\\' |  \n"
            + Ui.INDENTATIONS + " _| |_\\   |_| \\__.,| \\__/  |  \n"
            + Ui.INDENTATIONS + "|_____|\\____|'.__.' '.__.;__]";
    public final static String COMMAND_MESSAGE = """
            Usage: 
                list                             - Shows the list of all tasks 
                mark <item-index>                - Marks the item at the specified index as done
                unmark <item-index>              - Marks the item at the specified index as undone 
                delete <item-index>              - Removes the item at the specified index from the list            
                todo <description>               - Creates a new todo task and adds it to the list
                deadline <description> /by <date> - Creates a new deadline task and adds it to the list
                event <description> /from <date> /to <date> - Creates a new event task and adds it to the list""";

    public Ui() {}

    public static void print(String line) {
        //adds indentation to any printed lines
        System.out.println(Ui.INDENTATIONS + line);
    }
    public void showWelcomeMessage() {
        print("Hello! I'm\n" + logo + "\n");
        print("Lord of Winterfell and Warden Of The North");
        print("What can I do for you?");
    }
    public void showByeMessage() {
        print("I wish you good fortune in the wars to come, m' lord");
    }

    public void showLoadingError() {
        print("M'lord, do not be alarmed, but it appears that there was no previous saved task file. Not to " +
                "worry, we'll sort this out yet...");
    }

    public static void showCommandMessage() {
        //refactor this to instance method later
        print(COMMAND_MESSAGE);
    }

}
