import java.util.Scanner;

public class Zaibot {

    public static void printMessage(String message) {
        String line = "---------------------------------------\n";
        System.out.println(line + message + line);
    }

    public static void respond (String command) {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        switch (command) {
            case "bye":
                printMessage(goodbyeMessage);
                break;
            default:
                printMessage(command + "\n");
        }
    }

    public static void main(String[] args) {
        String greetingMessage = "Hello! I'm zAIbot.\nWhat can I do for you?\n";
        printMessage(greetingMessage);

        String currentCommand = "";

        while (!currentCommand.equals("bye")) {
            Scanner input = new Scanner(System.in);
            currentCommand = input.nextLine();
            respond(currentCommand);
        }

    }
}
