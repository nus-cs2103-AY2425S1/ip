import java.util.Objects;
import java.util.Scanner;

public class ChoaticBot {
    private TaskList tasklist;

    public ChoaticBot() {
        this.tasklist = new TaskList();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) {
        ChoaticBot chatBot = new ChoaticBot();
        String welcomeMsg = "____________________________________________________________ \n"
                + "Hello! I'm ChoaticBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        String byeMsg = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        System.out.println(welcomeMsg);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println(byeMsg);
                break;
            }

            // Listing out list
            if (Objects.equals(userInput, "list")) {
                printLine();
                chatBot.tasklist.listTask();
                printLine();
            } else if (Objects.equals((userInput.split(" ")[0]), "mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);
                printLine();
                chatBot.tasklist.markTask(index);
                printLine();
            } else if (Objects.equals((userInput.split(" ")[0]), "unmark"))  {
                int index = Integer.parseInt(userInput.split(" ")[1]);
                printLine();
                chatBot.tasklist.unmarkTask(index);
                printLine();
            } else {
                //Adding task to task list
                printLine();
                chatBot.tasklist.addTask(userInput);
                printLine();
            }
        }
    }
}