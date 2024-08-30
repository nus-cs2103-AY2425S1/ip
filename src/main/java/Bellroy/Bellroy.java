package Bellroy;

import java.io.IOException;

public class Bellroy {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public Bellroy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isRunning = true;
        while(isRunning) {
            String userInput = ui.getInput();
            parser.parse(userInput,taskList,ui,storage);
        }
    }
    public static void main(String[] args) {
        new Bellroy("Bellroy.txt").run();
    }
}
