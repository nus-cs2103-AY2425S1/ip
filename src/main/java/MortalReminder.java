import java.util.Scanner;

public class MortalReminder {

    private TaskList taskList = new TaskList();

    public static void main(String[] args) {
        MortalReminder mortalReminder = new MortalReminder();
        mortalReminder.Welcome();
        mortalReminder.ProcessInputs();
        mortalReminder.GoodBye();
    }

    private String FormatMessages(String message) {
        String indentation = "   ";
        String separatorLine = indentation + "__________________________________";
        StringBuilder outputMessage = new StringBuilder(separatorLine);
        String[] messageLines = message.split("\n");
        for (String line : messageLines) {
            outputMessage.append("\n")
                    .append(indentation)
                    .append(line);
        }
        outputMessage.append("\n")
                .append(separatorLine);
        return outputMessage.toString();
    }

    private void Welcome() {
        String welcomeMessage = "Hello I'm Mortal Reminder!\n"
                + "What can I do for you?";
        welcomeMessage = FormatMessages(welcomeMessage);
        System.out.println(welcomeMessage);
    }

    private void GoodBye() {
        String goodByeMessage = "Bye. Hope to see you again soon!";
        goodByeMessage = FormatMessages(goodByeMessage);
        System.out.println(goodByeMessage);
    }

    private void ProcessInputs() {
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            String input = inputScanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            else {
                String outputMessage = taskList.processCommand(input);
                outputMessage = FormatMessages(outputMessage);
                System.out.println(outputMessage);
            }
        }
    }
}
