import exceptions.JarException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class Jar {
    //class fields for parser and UI
    private Parser parser;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Jar(String filePath) {
        parser = new Parser();
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage);
        } catch (IOException | JarException e) {
            ui.showResponse("Error loading tasks: " + e.getMessage());
            taskList = new TaskList();
        }
    }

    public void runBot() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            ui.showLine();
            try {
                isRunning = parser.handleCommand(userInput, taskList, ui);
            } catch (JarException e) {
                ui.showResponse(e.getMessage());
            }
            ui.showLine();
        }
        saveTasksBeforeExit();
    }

    private void saveTasksBeforeExit() {
        try {
            storage.save(taskList.getTasks());
            ui.showResponse("Tasks saved successfully.");
        } catch (IOException e) {
            ui.showResponse("Error saving tasks: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Jar jar = new Jar("./data/jar.txt");
        jar.runBot();
    }
}
