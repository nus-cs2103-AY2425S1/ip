package zaibot;

import zaibot.command.Command;
import zaibot.exception.ZaibotException;

/**
 * This is the main class of the program.
 */
public class Zaibot {

    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage(taskList);

    /**
     * Runs the bot.
     */
    public void run() {
        Ui.printGreeting();
        boolean continueLoop = true;
        Ui.printSeparator();

        while (continueLoop) {
            try {
                String commandInput = Ui.readCommand();
                Command command = Parser.parse(commandInput);
                command.execute(taskList, storage);
                continueLoop = command.toContinue();
                Ui.printSeparator();
            }
            catch (ZaibotException e) {
                Ui.displayError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Zaibot().run();
    }
}
