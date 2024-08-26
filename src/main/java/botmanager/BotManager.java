package botmanager;

import java.io.FileNotFoundException;
import java.io.IOException;

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
       TaskList taskList = new TaskList();
       try {
           storage.loadTaskList(taskList);
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }

       while (true) {
           String input = ui.readUserInput();
           if (input.strip().equals("bye")) {
               break;
           }
           try {
               Action action = parser.parseInput(input);
               String output = action.execute(taskList);
               try {
                   storage.saveTaskList(taskList);
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
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
