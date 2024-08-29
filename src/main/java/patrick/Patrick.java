package patrick;

import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.tasklist.TaskList;
import patrick.ui.Ui;

public class Patrick {
    private final Ui ui;

    private static String FILE_PATH = "./data/tasks.txt";

    public Patrick(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (Parser.PatrickException e) {
            Ui.showErrorMsg(e.toString());
            tasks = new TaskList();
        } catch (Storage.StorageOperationException e) {
            Ui.showErrorMsg(e.toString());
        }
    }

    public static void main(String[] args) {
        new Patrick(FILE_PATH).run();
    }

    private void run() {
        ui.welcomeMessage();
        runTillBye();
        exit();
    }

    private void exit() {
        ui.showGoodbyeMsg();
        System.exit(0);
    }

    private void runTillBye() {
        Parser.Type input;
        do {
            String userInputMsg = ui.getUserCommand();
            input = new Parser().parseTask(userInputMsg);
        } while (!input.equals(Parser.Type.BYE));
    }
}
