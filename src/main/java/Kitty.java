import java.util.ArrayList;
import java.util.Scanner;


public class Kitty {
    private static final String name = "Kitty";
    private static final ArrayList<Task> list = new ArrayList<Task>(100);
    private static final String divisionLine = "--------------------------";
    public static void main(String[] args) {
//        String logo = """
//                 ____        -
//                |  _ \\ _   _| | _____\s
//                | | | | | | | |/ / _ \\
//                | |_| | |_| |   <  __/
//                |____/ \\__,_|_|\\_\\___|
//                """;
//        System.out.println("Hello from\n" + logo);
        Greet();
    }

    private static void Greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
        System.out.println(divisionLine);
        Echo();
    }

    private static void Echo() {
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
            } else {
                add(command);
            }
        }
    }

    private static void add(String item) {
        String[] aux = item.split(" ", 2);
        String type = aux[0];
        String name = aux[1];
        Task tmp = type.equals("todo")
                ? new Todo(name)
                : type.equals("deadline")
                ? createDeadline(name)
                : type.equals("event")
                ? createEvent(name)
                : null;
        list.add(tmp);
        System.out.println(divisionLine);
        System.out.println("Okie, I added it into the list:");
        System.out.println("  " + tmp);
        System.out.printf("Now you have %d tasks in the list.\n\n", list.size());
        System.out.println(divisionLine);
    }

    private static Task createDeadline(String str) {
        String[] parts = str.split("/by");
        return new Deadline(parts[0], parts[1]);
    }

    private static Task createEvent(String str) {
        String[] parts = str.split("/from|/to");
        return new Event(parts[0], parts[1], parts[2]);

    }

    private static void List() {
        int count = 1;
        Task[] tmp = new Task[0];
        System.out.println(divisionLine);
        System.out.println("Meow~ Here you are!");
        for (Task item: list.toArray(tmp)) {
            System.out.println(count++ + "." + item);
        }
        System.out.println("\n" + divisionLine);
    }

    private static void mark(int index) {
        Task tmp = list.get(index - 1);
        tmp.mark();
        System.out.println("Well done! You have completed this task!");
        System.out.println("  " + tmp);
        System.out.println("\n" + divisionLine);
    }

    private static void unmark(int index) {
        Task tmp = list.get(index - 1);
        tmp.unmark();
        System.out.println("Meow~ Okay we can continue this task!");
        System.out.println("  " + tmp);
        System.out.println("\n" + divisionLine);
    }

    private static int extractFirstNumber(String input) {
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

    private static void Exit() {
        System.out.println(divisionLine);
        System.out.println("Bye. Hope I can see you again soon!\nNext time bring me some cat food please!!!\n");
        System.out.println(divisionLine);
    }
}
