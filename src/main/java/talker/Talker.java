package talker;

import talker.command.Command;
import talker.task.TaskList;

/**
 * Represents a chatbot object
 */
public class Talker {
    private static final String NAME = "Talker";
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = "./data/talker.txt";

    private Ui ui;
    private Storage storage;
    private TaskList list;
    private String commandType;

    /**
     * Constructor for new Talker object
     */
    public Talker() {
        ui = new Ui(NAME);
        storage = new Storage(DIRECTORY_PATH, FILE_PATH);
        list = new TaskList();
        try {
            list = storage.readFile();
        } catch (TalkerException e) {
            ui.printError(e);
        }
    }

    /**
     * Runs the chatbot processes
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parseInput(fullCommand);
                c.execute(list, ui, storage);
                isExit = c.isExit();
            } catch (TalkerException e) {
                ui.printError(e);
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Generates response for user's chat messages
     *
     * @param input user input into chatbot
     * @return String reponse depending on user's input
     */
    public String getResponse(String input) {
        String output;

        try {
            Command c = Parser.parseInput(input);
            output = c.execute(list, ui, storage);
            commandType = c.getClass().getSimpleName();
        } catch (TalkerException e) {
            return ui.printError(e);
        }
        return output;
    }

    /**
     * Returns the command type
     *
     * @return String prepresenting command type
     */
    public String getCommandType() {
        return commandType;
    }

    public static void main(String[] args) {
        new Talker().run();
    }
}

