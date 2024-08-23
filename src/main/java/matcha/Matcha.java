package matcha;
import matcha.command.Command;
import matcha.exception.MatchaException;
import matcha.parser.Parser;
import matcha.storage.Storage;
import matcha.tasklist.TaskList;
import matcha.ui.Ui;

public class Matcha {
    private static final String FILE_PATH = "./data/matcha.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Matcha(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (MatchaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //greet users
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                //read user input
                String input = ui.readInput();
                ui.printDivider();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MatchaException e) {
                System.out.println(e);
            } finally {
                ui.printDivider();
            }
        }
        //once user has exited program, close scanner
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Matcha(FILE_PATH).run();
    }
}