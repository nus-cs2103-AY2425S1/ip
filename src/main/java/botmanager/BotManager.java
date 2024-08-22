package botmanager;

import action.Action;
import util.Parser;
import util.Ui;

/**
 * Entry point for the botmanager.BotManager Chatbot
 *
 * @author dwsc37
 */
public class BotManager {
    private final Ui ui;
    private final Parser parser;

    public BotManager() {
        ui = new Ui();
        parser = new Parser();
    }

    public void run() {
       ui.start();

       while (true) {
           String input = ui.readUserInput();
           if (input.equals("bye")) {
               break;
           }
           Action action = parser.parseInput(input);
           String output = action.execute();
           ui.printMessage(output);
       }

       ui.exit();
    }

    public static void main(String[] args) {
        new BotManager().run();
    }
}
