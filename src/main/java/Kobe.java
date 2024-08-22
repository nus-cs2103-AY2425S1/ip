import java.util.ArrayList;
import java.util.Scanner;

public class Kobe {

    public static void main(String[] args) {

        String logo = " K   K   OOOOO   BBBBB   EEEEE \n"
                + " K  K   O     O  B    B  E     \n"
                + " KKK    O     O  BBBBB   EEEE  \n"
                + " K  K   O     O  B    B  E     \n"
                + " K   K   OOOOO   BBBBB   EEEEE \n";

        System.out.println("____________________________________________________________");
        System.out.println("Greetings! I am Kobe Bryant \n" + logo);
        System.out.println("How can I help you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (userInput.equals("bye")) {
                System.out.println("Goodbye! My man.");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskNumber));
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskNumber));
            } else if (userInput.startsWith("todo ")) {
                String name = userInput.substring(5).trim();
                Todo todo = new Todo(name);
                tasks.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split("/by ");
                String name = parts[0].trim();
                String by = parts[1].trim();
                Deadline deadline = new Deadline(name, by);
                tasks.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split("/from |/to ");
                String name = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                Event event = new Event(name, from, to);
                tasks.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Enter the right command, Sir.");
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}
