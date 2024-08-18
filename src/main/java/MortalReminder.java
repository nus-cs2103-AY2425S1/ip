import java.util.Scanner;

public class MortalReminder {

    public static void main(String[] args) {
        MortalReminder mortalReminder = new MortalReminder();
        mortalReminder.Welcome();
        mortalReminder.ProcessInputs();
        mortalReminder.GoodBye();
    }

    private void Welcome() {
        String welcomeMessage = "Hello I'm Mortal Reminder!\n"
                + "What can I do for you?";
        FormattedPrinting.FormatPrint(welcomeMessage);

    }

    private void GoodBye() {
        String goodByeMessage = "Bye. Hope to see you again soon!";
        FormattedPrinting.FormatPrint(goodByeMessage);
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
