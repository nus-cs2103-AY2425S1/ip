import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alfred {
    private static List<Task> lis;

    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner in = new Scanner(System.in);

        // List to store tasks
        lis = new ArrayList<>();

        // Greet user
        greet();

        // Echo user input
        String input = in.nextLine();
        while (!input.equals("bye")) {
            String command = getCommand(input);

            if (input.equals("list")) {
                printList();
            } else if (command.equals("mark")) {
                markCommand(input);
            } else if (command.equals("unmark")) {
                unmarkCommand(input);
            } else if (Task.isTaskCommand(input)){
                Task task = Task.initialise(input);
                lis.add(task);
                printAddedTaskMessage(task);
            } else {
                invalidCommand();
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

    public static void invalidCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("Terribly sorry Sir, I have no idea what you are saying.");
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

    public static int getTaskNumberFromInput(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]);
    }

    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static void markCommand(String input) {
        String[] parts = input.split(" ");

        int taskNumber = getTaskNumberFromInput(input);
        Task task = lis.get(taskNumber - 1);
        task.markAsDone();

        System.out.println("____________________________________________________________");
        System.out.println("Indeed, Sir, the task has been duly completed:");
        System.out.println("    " + task);
        System.out.println("____________________________________________________________");
    }

    public static void unmarkCommand(String input) {
        int taskNumber = getTaskNumberFromInput(input);
        Task task = lis.get(taskNumber - 1);

        task.unmark();
        System.out.println("____________________________________________________________");
        System.out.println("Very well, Sir, the task remains outstanding:");
        System.out.println("    " + task);
        System.out.println("A reminder that even small tasks deserve attention.");
        System.out.println("____________________________________________________________");
    }

    public static void printAddedTaskMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + lis.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
