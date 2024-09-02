package conversage;

import conversage.command.Command;
import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;

public class ConverSage {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ConverSage(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (ConverSageException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ConverSageException e) {
                ui.showLine();
                ui.showError(e.getMessage());
            } 
                    
        }
    }
    public static void main(String[] args) {
        new ConverSage("data/tasks.txt").run();
    }



}