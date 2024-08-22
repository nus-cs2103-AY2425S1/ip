import java.util.Objects;
import java.util.Scanner;

public class ChoaticBot {
    public static void main(String[] args) {
        String welcomeMsg = "____________________________________________________________ \n"
                + "Hello! I'm ChoaticBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        String byeMsg = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";


        String lines = "____________________________________________________________\n";
        System.out.println(welcomeMsg);
        Scanner scanner = new Scanner(System.in);
        String[] taskList = new String[100];
        int numberOfTasks = 0;

        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println(byeMsg);
                break;
            }

            // Listing out list
            if (Objects.equals(userInput, "list")) {
                System.out.println(lines);
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println( (i + 1) + ". " + taskList[i]);
                }
                System.out.println(lines);
            } else {
                //Adding task to task list
                taskList[numberOfTasks] = userInput;
                numberOfTasks++;
                System.out.println(lines + "added: " + userInput + "\n" + lines);
            }
        }
    }
}
