package optimus;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Optimus {
    private static final String FILE_PATH = "./data/optimus.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Optimus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException | OptimusException e) { // used gpt
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        while (true) {
            String command = ui.readCommand();
            try {
                Task task = Parser.parseCommand(command, tasks, ui, storage);
                if (task != null) {
                    tasks.addTask(task);
                    ui.TaskAdded(task, tasks.size());
                }
            } catch (OptimusException | IOException e) { // used gpt
                ui.printError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Optimus(FILE_PATH).run();
    }
}
