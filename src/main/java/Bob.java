import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bob {
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String GREETINGS = "Hello! I'm Bob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    public static void main(String[] args) {

        System.out.print(DIVIDER + GREETINGS + DIVIDER);

        chatBot();

        System.out.print(EXIT + DIVIDER);
    }

    public static void chatBot() {
        Scanner scanner = new Scanner(System.in);
        List<Task> myTasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            switch (input.split(" ")[0]) {
                case "bye":
                    System.out.print(DIVIDER);
                    return;
                case "list":
                    System.out.print(DIVIDER);
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 1; i < myTasks.size() + 1; i++) {
                        System.out.print(" " + i + "." + myTasks.get(i-1) + "\n");
                    }
                    System.out.print(DIVIDER);
                    break;
                case "mark":
                    myTasks.get(Integer.parseInt(input.split(" ")[1]) - 1).mark();
                    System.out.print(DIVIDER);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(" " + myTasks.get(Integer.parseInt(input.split(" ")[1]) - 1));
                    System.out.print(DIVIDER);
                    break;
                case "unmark":
                    myTasks.get(Integer.parseInt(input.split(" ")[1]) - 1).unmark();
                    System.out.print(DIVIDER);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(" " + myTasks.get(Integer.parseInt(input.split(" ")[1]) - 1));
                    System.out.print(DIVIDER);
                    break;
                default:
                    myTasks.add(new Task(input));
                    System.out.print(DIVIDER + " added: " + input + "\n" + DIVIDER);

            }
        }
    }
}
