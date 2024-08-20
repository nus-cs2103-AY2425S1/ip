import java.util.Scanner;
import java.util.ArrayList;
public class SeanBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm SeanBot\n" + "What can I do for you?");
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] part = userInput.split(" ", 2);
                String first = part[0];

                if (first.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (first.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (first.equals("mark")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The task number of mark cannot be empty");
                    }
                    int second = Integer.parseInt(part[1]) - 1;
                    if (second < 0 || second >= tasks.size()) {
                        throw new SeanBotException("The task number must be valid");
                    }
                    tasks.get(second).Done();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(second));
                } else if (first.equals("unmark")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The task number of unmark cannot be empty");
                    }
                    int second = Integer.parseInt(part[1]) - 1;
                    if (second < 0 || second >= tasks.size()) {
                        throw new SeanBotException("The task number must be valid");
                    }
                    tasks.get(second).Undone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(second));
                } else if (first.equals("todo")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(part[1]);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (first.equals("deadline")) {
                    String[] specifications = part[1].split(" /by ");
                    if (specifications.length < 2) {
                        throw new SeanBotException("The description or a deadline cannot be empty.");
                    }
                    Task deadline = new Deadline(specifications[0], specifications[1]);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (first.equals("event")) {
                    String[] firstPart = part[1].split(" /from ", 2);
                    if (firstPart.length < 2) {
                        throw new SeanBotException("The description of an event cannot be empty.");
                    }
                    String[] secondPart = firstPart[1].split(" /to ", 2);
                    if (secondPart.length < 2) {
                        throw new SeanBotException("The end time of an event cannot be empty.");
                    }
                    Task event = new Event(firstPart[0].trim(), secondPart[0].trim(), secondPart[1].trim());
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (first.equals("delete")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The task number to delete must be valid.");
                    }
                    int second = Integer.parseInt(part[1]) - 1;
                    if (second < 0 || second >= tasks.size()) {
                        throw new SeanBotException("The task number to delete must be valid.");
                    }
                    Task remove = tasks.remove(second);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + remove);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new SeanBotException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (SeanBotException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } 
        }
        scanner.close();
    }
}
