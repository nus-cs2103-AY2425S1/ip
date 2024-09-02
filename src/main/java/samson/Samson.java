package samson;// Samson.Samson.java
import samson.command.Command;
import samson.task.TaskList;

import java.io.IOException;
public class Samson {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Samson(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTaskFromFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (SamException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SamException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error occurred while accessing the storage.");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Samson("data/samson.txt").run();
    }
}
