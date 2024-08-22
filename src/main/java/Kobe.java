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
        System.out.println("Greetings! I am Kobe Bryant. \n" + logo);
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
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(taskNumber));
                    } else {
                        System.out.println("OOPS!!! The task number you provided is not valid.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! Please provide a valid task number.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(taskNumber));
                    } else {
                        System.out.println("OOPS!!! The task number you provided is not valid.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! Please provide a valid task number.");
                }
            } else if (userInput.startsWith("delete ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        Task removedTask = tasks.remove(taskNumber);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("OOPS!!! The task number you provided is not valid.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! Please provide a valid task number.");
                }
            } else if (userInput.startsWith("todo ")) {
                String name = userInput.substring(5).trim();
                if (name.isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Todo todo = new Todo(name);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split("/by ");
                if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    System.out.println("OOPS!!! The description or deadline of a task cannot be empty.");
                } else {
                    String name = parts[0].trim();
                    String by = parts[1].trim();
                    Deadline deadline = new Deadline(name, by);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split("/from |/to ");
                if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                    System.out.println("OOPS!!! The event description, start time, or end time cannot be empty.");
                } else {
                    String name = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    Event event = new Event(name, from, to);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else {
                System.out.println("Please Enter the right command, Sir.");
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}
