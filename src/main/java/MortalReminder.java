import java.util.Scanner;

public class MortalReminder {

    private final Scanner inputScanner = new Scanner(System.in);
    private TaskList taskList = new TaskList();
    private final Processor processor = new Processor();

    public static void main(String[] args) {
        MortalReminder mortalReminder = new MortalReminder();
        mortalReminder.welcome();
        mortalReminder.ProcessInputs();
        mortalReminder.goodbye();
    }

    private void ProcessInputs() {

        boolean continueScanning = true;
        while (continueScanning) {
            String input = inputScanner.nextLine();
            Command command = Parser.parseInputFromUser(input);
            continueScanning = this.processor.HandleCommand(command, this.taskList, continueScanning);
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
