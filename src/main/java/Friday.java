import java.util.Objects;
import java.util.Scanner;

public class Friday {
    private final Task[] items = new Task[100];
    private int index;
    private void horizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
    private void welcomeMessage() {
        horizontalLine();
        System.out.println("\tWelcome Back! I'm Friday");
        System.out.println("\tWhat can I do for you today?");
        horizontalLine();
    }
    private void readInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            horizontalLine();
            if (Objects.equals(input, "bye")) break;
            if (Objects.equals(input, "list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + items[i].toPrettyString());
                }
                horizontalLine();
                continue;
            } else if (input.startsWith("mark")) {
                try {
                    items[Integer.parseInt(input.substring(5)) - 1].markAsDone();
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
                horizontalLine();
                continue;
            } else if (input.startsWith("unmark")) {
                try {
                    items[Integer.parseInt(input.substring(7)) - 1].unmarkAsDone();
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
                horizontalLine();
                continue;
            }
            items[index++] = new Task(input);
            horizontalLine();
        }
    }
    private void exit() {
        System.out.println("\tGood Bye. Hope to see you again soon!");
        horizontalLine();
    }
    private void initialize() {
        welcomeMessage();
        readInput();
        exit();
    }
    public static void main(String[] args) {
        Friday bot = new Friday();
        bot.initialize();
    }
}
