package screwllum;

import screwllum.exception.ScrewllumException;
import screwllum.tasks.TaskManager;
import screwllum.utils.Parser;
import screwllum.utils.Storage;
import screwllum.utils.Ui;

import java.util.List;
import java.io.IOException;


public class Screwllum {
    
    private Storage storage;
    private TaskManager taskManager;
    private Ui ui;

    public Screwllum(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager(storage.load());
        } catch (IOException e) {
            ui.showError("File does not exist, creating new file");
            taskManager = new TaskManager();
        } catch (ScrewllumException e) {
            ui.showError(e.getMessage());
        }
    }
    
    public void run() {
        ui.showWelcome();
        
        while (true) {
            try {
                String userInput = ui.getInput();
                List<String> tokens = Parser.parseUserInput(userInput);
                taskManager.execute(tokens, ui);
                storage.writeToFile(taskManager.getTaskList());
            } catch (ScrewllumException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        new Screwllum("Save.txt").run();
    }
}