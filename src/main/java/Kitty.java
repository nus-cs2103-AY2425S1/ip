import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kitty {
    private static final String name = "Kitty";
    private static final ArrayList<Task> list = new ArrayList<Task>(100);
    private static final String divisionLine = "--------------------------";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        // TODO: Level 0.Rename, Greet, Exit
        Greet();
    }

    public static void Greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
        System.out.println(divisionLine);
        Echo();
    }

    public static void Echo() {
        String command = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            command = sc.nextLine();
            if (command.contains("bye")) {
                Exit();
                return;
            } else if (command.contains("list")) {
                List();
            } else if (command.contains("unmark")) {
                unmark(extractFirstNumber(command));
            } else if (command.contains("mark")) {
                mark(extractFirstNumber(command));
            } else
                add(command);
        }
    }

    public static void add(String item) {
        Task tmp = new Task(item);
        list.add(tmp);
        System.out.println(divisionLine);
        System.out.println("added: " + item + "\n");
        System.out.println(divisionLine);
    }

    public static void List() {
        int count = 1;
        Task[] tmp = new Task[0];
        System.out.println(divisionLine);
        System.out.println("Meow~ Here you are!");
        for (Task item: list.toArray(tmp)) {
            System.out.println(count++ + "." + item);
        }
        System.out.println("\n" + divisionLine);
    }

    public static void mark(int index) {
        Task tmp = list.get(index - 1);
        tmp.mark();
        System.out.println("Well done! You have completed this task!");
        System.out.println("  " + tmp);
        System.out.println("\n" + divisionLine);
    }

    public static void unmark(int index) {
        Task tmp = list.get(index - 1);
        tmp.unmark();
        System.out.println("Meow~ Okay we can continue this task!");
        System.out.println("  " + tmp);
        System.out.println("\n" + divisionLine);
    }

    public static int extractFirstNumber(String input) {
        // Replace all non-digit characters with spaces
        String cleanedInput = input.replaceAll("\\D+", " ");

        // Split the cleaned string by spaces
        String[] parts = cleanedInput.trim().split("\\s+");

        // Check if there are any parts and parse the first one
        if (parts.length > 0) {
            try {
                return Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails
                return -1;
            }
        }

        // Return null if no number is found
        return -1;
    }

    public static void Exit() {
        System.out.println(divisionLine);
        System.out.println("Bye. Hope I can see you again soon!\nNext time bring me some cat food please!!!\n");
        System.out.println(divisionLine);
    }
}
