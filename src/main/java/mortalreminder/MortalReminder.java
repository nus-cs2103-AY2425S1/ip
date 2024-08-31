package mortalreminder;

import java.util.Scanner;

import mortalreminder.backend.Processor;
import mortalreminder.backend.Storage;
import mortalreminder.backend.TaskList;
import mortalreminder.commands.Command;
import mortalreminder.io.FormattedPrinting;
import mortalreminder.io.Parser;

/**
 * Main class of the MortalReminder App.
 */
public class MortalReminder {

    private TaskList taskList = new TaskList();

    /**
     * Starts the MortalReminder App.
     * If the full app with its dependent classes is not available, the app will not start.
     *
     * @param args Command Line parameters passed into the program (not used)
     */
    public static void main(String[] args) {
        MortalReminder mortalReminder = new MortalReminder();
        mortalReminder.welcome();
        mortalReminder.processInputs();
        mortalReminder.goodbye();
    }

    private void processInputs() {
        Scanner inputScanner = new Scanner(System.in);
        Processor processor = new Processor();

        boolean shouldContinue = true;
        while (shouldContinue) {
            String input = inputScanner.nextLine();
            Command command = Parser.parseInputFromUser(input);
            shouldContinue = processor.handleCommand(command, this.taskList, shouldContinue);
        }
    }

    private void welcome() {
        this.taskList = Storage.loadTaskListFromFile();
        FormattedPrinting.welcome();
    }

    private void goodbye() {
        FormattedPrinting.goodbye();
    }
}
