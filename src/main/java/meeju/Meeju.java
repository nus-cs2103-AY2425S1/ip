package meeju;

/**
 * Main class of the program. It serves as the entry point to the program.
 */
public class Meeju {

    private static final String LINE_BREAK = "____________________________________________________________";

    Storage storage = new Storage();
    TaskList taskList = new TaskList(storage);
    Parser parser = new Parser();
    public static void main(String[] args) {

        String logo = " __  __ _____ _____    _ _   _\n"
                + "|  \\/  | ____| ____|  | | | | |\n"
                + "| |\\/| |  _| |  _| _  | | | | |\n"
                + "| |  | | |___| |__| |_| | |_| |\n"
                + "|_|  |_|_____|_____\\___/ \\___/";

    }

    public String getResponse(String instruction) {
        return parser.parse(taskList, instruction);
    }
}
