package bottle;

import bottle.command.Command;
import bottle.exception.BottleException;
import bottle.task.*;

import java.io.ByteArrayOutputStream;

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
            try {
                String input = ui.getInput();
                Command command = parser.parseCommand(input);
                command.execute(taskList, ui, storage);
            } catch (BottleException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Bottle("./data/bottle.txt").run();
    }
}
