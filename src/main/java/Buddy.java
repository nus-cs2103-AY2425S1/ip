import command.Command;
import exceptions.BuddyException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Buddy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Buddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BuddyException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BuddyException e) {
                ui.displayError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Buddy("data/buddy.txt").run();
    }
}