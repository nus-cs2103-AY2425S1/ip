import java.util.Scanner;

public class MortalReminder {

    public static void main(String[] args) {
        MortalReminder mortalReminder = new MortalReminder();
        FormattedPrinting.Welcome();
        mortalReminder.ProcessInputs();
        FormattedPrinting.GoodBye();
    }

    private void ProcessInputs() {

        Scanner inputScanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        CommandExecutor commandExecutor = new CommandExecutor();

        boolean continueScanning = true;
        while (continueScanning) {
            String input = inputScanner.nextLine();
            Command command = parser.parseInputFromUser(input);
            continueScanning = commandExecutor.HandleCommand(command, taskList, continueScanning);
        }
    }
}
