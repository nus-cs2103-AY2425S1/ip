import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String greeting = "   *        *        *        __o    *       *\n"
                + "*      *       *        *    /_| _     *\n"
                + "  FF  *    FF      *        O'_)/ \\  *    *\n"
                + "  o')____  o')____    __*   V   \\  ) __  *\n"
                + "   \\ ___ )--\\ ___ )--( (    (___|__)/ /*     *\n"
                + " *  |   |    |   |  * \\ \\____| |___/ /  *\n"
                + "    |*  |    |   |     \\____________/       *\n";
        String festiveMessage =
                "Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •\n" +
                        "•。★Christmas★ 。* 。\n" +
                        "° 。 ° ˛˚˛ * Π__。˚\n" +
                        "˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛\n" +
                        "˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚\n" +
                        "And a happy new year!";
        System.out.println(greeting + "Hello, I am Rudolf, Santa's trusty red-nose reindeer");
        System.out.println("Christmas is nearing, and I am here to help you with your christmas preparations.\nTell me the tasks that you need to complete.");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input = "";
        List<Task> tasks = new ArrayList<>();

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks to display.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task number.");
                    }
            } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task number.");
                    }
            } else if (input.startsWith("todo")) {
                String description = input.substring(5).trim();
                tasks.add(new ToDo(description));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                String[] substrings = input.split(" /by ");
                String description = substrings[0].substring(9).trim();
                String by = substrings[1].trim();
                tasks.add(new Deadline(description, by));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("event")) {
                String[] substrings = input.split(" /from ");
                String description = substrings[0].substring(6).trim();
                String[] fromTo = substrings[1].split(" /to ");
                String from = fromTo[0].trim();
                String to = fromTo[1].trim();
                tasks.add(new Event(description, from, to));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.equals("bye")) {
                System.out.println("Bye~ Hope to see you again soon! \n" + festiveMessage);
            } else {
                System.out.println("I'm sorry, I don't understand that command.");
            }
            System.out.println("____________________________________________________________");
        }
    }
}
