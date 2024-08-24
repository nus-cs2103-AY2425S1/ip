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
        this.taskList = Storage.loadTaskListFromFile(this.taskList);
        FormattedPrinting.welcome();
    }

    private void goodbye() {
        FormattedPrinting.goodbye();
    }
}
