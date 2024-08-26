package botmanager;

import action.Action;
import exception.BotException;
import task.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

/**
 * Entry point for the BotManager Chatbot
 *
 * @author dwsc37
 */
public class BotManager {
    private final Ui ui;
    private final Parser parser;
    private final Storage storage;

    public BotManager() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    public void run() {
       ui.start();
       TaskList taskList = storage.loadTaskList();

       while (true) {
           String input = ui.readUserInput();
           if (input.strip().equals("bye")) {
               break;
           }
           try {
               Action action = parser.parseInput(input);
               String output = action.execute(taskList);
               storage.saveTaskList(taskList);
               ui.printMessage(output);
           } catch (BotException e) {
                ui.printMessage(e.getMessage());
           }
       }

       ui.exit();
    }

    public static void main(String[] args) {
        new BotManager().run();
    }
}
