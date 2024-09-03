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

    public static void main(String[] args) {
        new Talker().run();
    }
}

