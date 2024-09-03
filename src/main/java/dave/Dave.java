package dave;

import dave.command.Command;
import dave.exceptions.InvalidCommandException;
import dave.parser.Parser;
import dave.storage.Storage;
import dave.task.TaskList;
import dave.ui.Ui;

import java.io.IOException;

public class Dave {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Dave(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath, tasks);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                if (c == null) { continue; }
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showLine();
                System.out.println("An error occurred while trying to write to the file: " + e.getMessage());
                e.printStackTrace();
            }
            finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Dave("C:\\Users\\thamy\\OneDrive\\data\\daveData.txt").run();
    }
}
