import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            } else if (command.equals("mark") || command.equals("unmark")) {
                processMarkCommand(input, command.equals("mark"));
            } else if (Task.isTaskCommand(input)){
                try {
                    Task task = Task.initialise(input);
                    lis.add(task);
                    printAddedTaskMessage(task);
                } catch (AlfredException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Unexpected error: " + e.getMessage());
                }
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

    public static void processMarkCommand(String input, boolean mark) {
        String action = mark ? "mark" : "unmark";

        String regex = "^" + action + " \\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            System.out.println("____________________________________________________________");
            System.out.println("Invalid " + action + " command Sir.");
            System.out.println("____________________________________________________________");
            return;
        }

        int taskNumber = getTaskNumberFromInput(input);
        if (taskNumber < 0 || taskNumber > lis.size()) {
            System.out.println("____________________________________________________________");
            System.out.println("Invalid task number Sir.");
            System.out.println("You only have " + lis.size() + " items in the list.");
            System.out.println("____________________________________________________________");
            return;
        }

        Task task = lis.get(taskNumber - 1);
        if (mark) {
            task.markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println("Indeed, Sir, the task has been duly completed:");
            System.out.println("    " + task);
            System.out.println("____________________________________________________________");
        } else {
            task.unmark();
            System.out.println("____________________________________________________________");
            System.out.println("Very well, Sir, the task remains outstanding:");
            System.out.println("    " + task);
            System.out.println("A reminder that even small tasks deserve attention.");
            System.out.println("____________________________________________________________");
        }
    }

    public static void printAddedTaskMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + lis.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
