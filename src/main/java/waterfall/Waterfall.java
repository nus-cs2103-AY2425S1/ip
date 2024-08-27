package waterfall;

import waterfall.command.Command;
import waterfall.task.TaskList;

import java.io.IOException;

public class Waterfall {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Waterfall(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return ;
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (WaterfallException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (WaterfallException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Waterfall("data/Tasks.txt").run();
    }
}
