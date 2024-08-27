package Main;

import Commands.Command;
import Commands.ExitCommand;
import Storage.Storage;
import UI.Ui;
import Tasks.Task;

import java.util.List;

public class ProYapper {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public ProYapper(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        List<Task> tasks = storage.loadTasks();
        this.taskList = new TaskList(tasks);
    }

    public static void main(String[] args) {
        ProYapper proYapper = new ProYapper("./data/ProYapper.txt");
        proYapper.run();
    }

    public void run() {
        ui.showWelcome();  // Display welcome message
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();  // Read user input
            Command command = this.parser.parseCommand(userInput);  // Parse input to get command
            command.execute(taskList, ui, storage);  // Execute the command
            isExit = (command instanceof ExitCommand);  // Check if the command is to exit
        }
        ui.showGoodbye();
    }
}