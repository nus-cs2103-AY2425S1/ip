package bottle;

import bottle.command.Command;
import bottle.task.TaskList;

/**
 * The type Bottle.
 */
public class Bottle {
    /**
     * The Storage.
     */
    private final Storage storage;
    /**
     * The Parser.
     */
    private final Parser parser;
    /**
     * The Task list.
     */
    private final TaskList taskList;
    /**
     * The Ui.
     */
    private final Ui ui;

    /**
     * Instantiates a new Bottle.
     *
     * @param filePath the file path
     */
    public Bottle(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        ui = new Ui();
        taskList = new TaskList(storage.loadTasks());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Bottle("./data/bottle.txt").run();
    }

    /**
     * Run.
     */
    public void run() {
        ui.printWelcomeMsg();
        while (true) {
            String input = ui.getInput();
            Command command = parser.parseCommand(input);
            command.execute(taskList, ui, storage);
        }
    }
}
