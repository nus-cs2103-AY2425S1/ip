package sigma;

import sigma.Storage;
import sigma.Ui;
import sigma.command.Commands;
import sigma.exception.SigmaException;

import java.io.File;
public class Sigma {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private File data;
    private Sigma(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.data = new File(filePath);
        tasks = new TaskList(storage.read(data));
    }

    public static void main(String[] args) {
        Sigma sigma = new Sigma("data.txt");
        sigma.run();
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand;
            try {
                fullCommand = ui.readCommand();
                Commands c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (SigmaException e) {
                ui.print(e.getMessage());
            } catch (NumberFormatException e) {
                ui.print("What the sigma? I need a number!");
            } finally {
            }
            storage.write(this.data, tasks.getTasks());
        }
    }
}







