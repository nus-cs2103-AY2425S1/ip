import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alfred {
    private static List<Task> lis;

    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner in = new Scanner(System.in);

        // List to store text entered by used
        lis = new ArrayList<>();

        // Greet user
        greet();

        // Echo user input
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else if (isMarkCommand(input)) {
                int taskNumber = getTaskNumberFromInput(input);
                Task task = lis.get(taskNumber - 1);

                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Indeed, Sir, the task has been duly completed:");
                System.out.println("    " + task);
                System.out.println("____________________________________________________________");

            } else if (isUnmarkCommand(input)) {
                int taskNumber = getTaskNumberFromInput(input);
                Task task = lis.get(taskNumber - 1);

                task.unmark();
                System.out.println("____________________________________________________________");
                System.out.println("Very well, Sir, the task remains outstanding:");
                System.out.println("    " + task);
                System.out.println("A reminder that even small tasks deserve attention.");
                System.out.println("____________________________________________________________");

            } else {
                Task task = new Task(input);
                lis.add(task);

                printAddedMessage(input);
            }
            input = in.nextLine();
        }
        farewell();
    }

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Good day Sir. I am Alfred, your English butler.");
        System.out.println("Might I offer you some tea, or perhaps something stronger to suit the occasion?");
        System.out.println("____________________________________________________________");
    }

    public static void farewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Farewell Sir. Should you need anything, you know where to find me.");
        System.out.println("____________________________________________________________");
    }

    public static void printAddedMessage(String input) {
        System.out.println("____________________________________________________________");
        System.out.println("added: " + input);
        System.out.println("____________________________________________________________");
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        int counter = 1;
        for (Task task : lis) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        System.out.println("____________________________________________________________");
    }

    public static boolean isMarkCommand(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            return false;
        }
        return parts[0].equals("mark");
    }

    public static boolean isUnmarkCommand(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            return false;
        }
        return parts[0].equals("unmark");
    }

    public static int getTaskNumberFromInput(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]);
    }
}
