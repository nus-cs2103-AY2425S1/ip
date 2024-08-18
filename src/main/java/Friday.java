import java.util.Objects;
import java.util.Scanner;

public class Friday {
    private final Task[] items = new Task[100];
    private int index;
    private void horizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
    private void welcomeMessage() { // Level 0
        horizontalLine();
        System.out.println("\tWelcome Back! I'm Friday");
        System.out.println("\tWhat can I do for you today?");
        horizontalLine();
    }
    private void readInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        String[] inputs;
        Task currTask;
        while (true) {
            input = sc.nextLine();
            horizontalLine();
            if (Objects.equals(input, "bye")) break; // Level 1
            if (Objects.equals(input, "list")) { // Level 2
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + items[i].toPrettyString());
                }
                horizontalLine();
            } else {
                inputs = input.split(" ");
                if (Objects.equals(inputs[0], "mark")) { // Level 3
                    items[Integer.parseInt(inputs[1]) - 1].markAsDone();
                    horizontalLine();
                    continue;
                } else if (Objects.equals(inputs[0], "unmark")) { // Level 3
                    items[Integer.parseInt(inputs[1]) - 1].unmarkAsDone();
                    horizontalLine();
                    continue;
                } else if (Objects.equals(inputs[0], "todo")) { // Level 4
                    currTask = new Todo(input.substring(5));
                } else if (Objects.equals(inputs[0], "deadline")) { // Level 4
                    inputs = input.substring(9).split(" /by ");
                    currTask = new Deadline(inputs[0], inputs[1]);
                } else if (Objects.equals(inputs[0], "event")) { // Level 4
                    inputs = input.substring(6).split(" /from | /to ");
                    currTask = new Event(inputs[0], inputs[1], inputs[2]);
                } else { // Level 2
                    currTask = new Task(input);
                    System.out.println("\tadded: " + input);
                }
                items[index++] = currTask;
                if (currTask.getClass() == Task.class) {
                    horizontalLine();
                    continue;
                }
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t  " + currTask);
                System.out.println("\tNow you have " + index + " tasks in the list.");
                horizontalLine();
            }
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
