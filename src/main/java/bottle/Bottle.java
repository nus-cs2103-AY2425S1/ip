package bottle;

import bottle.command.Command;
import bottle.exception.BottleException;
import bottle.task.TaskList;



/**
 * The type Bottle.
 */
public class Bottle {
    /**
     * The Storage.
     */
    private Storage storage;
    /**
     * The Parser.
     */
    private Parser parser;
    /**
     * The Task list.
     */
    private TaskList taskList;
    /**
     * The Ui.
     */
    private Ui ui;

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
     * Run.
     */
    public void run() {
        ui.printWelcomeMsg();
        while (true) {
            try {
                String input = ui.getInput();
                Command command = parser.parseCommand(input);
                assert command != null : "command cannot be null";
                command.execute(taskList, ui, storage);
            } catch (BottleException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            assert command != null : "command cannot be null";
            return command.execute(taskList, ui, storage);
        } catch (BottleException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Bottle("./data/bottle.txt").run();
    }
}
