package mortal_reminder;

import mortal_reminder.backend.Processor;
import mortal_reminder.backend.Storage;
import mortal_reminder.backend.TaskList;
import mortal_reminder.commands.Command;
import mortal_reminder.io.FormattedPrinting;
import mortal_reminder.io.Parser;

import java.util.Scanner;

public class MortalReminder {

    private TaskList taskList = new TaskList();

    public static void main(String[] args) {
        MortalReminder mortalReminder = new MortalReminder();
        mortalReminder.welcome();
        mortalReminder.ProcessInputs();
        mortalReminder.goodbye();
    }

    private void ProcessInputs() {
        Scanner inputScanner = new Scanner(System.in);
        Processor processor = new Processor();

        boolean shouldContinue = true;
        while (shouldContinue) {
            String input = inputScanner.nextLine();
            Command command = Parser.parseInputFromUser(input);
            shouldContinue = processor.HandleCommand(command, this.taskList, shouldContinue);
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
