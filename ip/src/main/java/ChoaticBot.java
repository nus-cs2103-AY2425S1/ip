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
        String welcomeMsg = "____________________________________________________________\n"
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
            String[] processInput = userInput.split(" ");
            String action = processInput[0];
            String otherText = "";

            for (int i = 1; i < processInput.length; i++) {
                otherText = otherText.concat(processInput[i] + " ");
            }

            otherText = otherText.trim();

            if (Objects.equals(userInput, "bye")) {
                System.out.println(byeMsg);
                break;
            }

            // Listing out list
            if (action.equals("list")) {
                printLine();
                chatBot.tasklist.listTask();
                printLine();
            } else if (action.equals("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);
                printLine();
                chatBot.tasklist.markTask(index);
                printLine();
            } else if (action.equals("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);
                printLine();
                chatBot.tasklist.unmarkTask(index);
                printLine();
            } else if (action.equals("todo")) {
                printLine();
                ToDos newToDos = new ToDos(otherText);
                chatBot.tasklist.addTask(newToDos);
                printLine();
            } else if (action.equals("deadline")) {
                printLine();
                String[] processAgain = otherText.split("/");
                String taskName = processAgain[0];
                String deadline = processAgain[1];
                Deadlines newDeadline = new Deadlines(taskName, deadline);
                chatBot.tasklist.addTask(newDeadline);
                printLine();
            } else if (action.equals("event")) {
                printLine();
                String[] processAgain = otherText.split("/");
                String taskName = processAgain[0];
                String from = processAgain[1];
                String to = processAgain[2];
                Events newEvent = new Events(taskName, from, to);
                chatBot.tasklist.addTask(newEvent);
                printLine();
            }
        }
    }
}