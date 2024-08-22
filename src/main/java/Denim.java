import java.util.Scanner;

public class Denim {
    public static void main(String[] args) {

        String horizontalLine = "____________________________________________________________";
        String chatBotName = "Denim";
        String greetingMessage = String.format("%s%n Hello! I'm %s!%n What can I do for you? %n%s%n",
                horizontalLine, chatBotName, horizontalLine);

        String[] taskList = new String[100];
        int taskSize = 0;

        System.out.println(greetingMessage);

        // Scans User Input in the CLI
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Adds task or list tasks stated by user input until the command "bye" is given.
        while (!input.equals("bye")) {
            switch (input) {
            case "list":
                System.out.println(horizontalLine);
                for (int i = 0; i < taskSize; i++) {
                    System.out.printf("%d. %s\n", i + 1, taskList[i]);
                }
                System.out.println(horizontalLine);
                input = sc.nextLine();
                break;
            default:
                taskList[taskSize++] = input;
                String addMessage = String.format("%s%n added: %s%n%s", horizontalLine, input, horizontalLine);
                System.out.println(addMessage);
                input = sc.nextLine();
                break;
            }
        }
        String byeMessage = String.format("%s%n %s%n%s", horizontalLine, "Bye. Hope to see you again soon!",
                horizontalLine);
        System.out.println(byeMessage);
        sc.close();
    }
}


