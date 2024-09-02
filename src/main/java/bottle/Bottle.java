package bottle;

import bottle.task.*;

public class Bottle {
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private Ui ui;
    public Bottle(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        ui = new Ui();
        taskList = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.printWelcomeMsg();
        while (true) {
            String input = ui.getInput();
            parser.parseCommand(input, taskList, ui);
            storage.saveTasks(taskList.getTaskList());
        }
    }
    public static void main(String[] args) {
        new Bottle("./data/bottle.txt").run();

    }
}
