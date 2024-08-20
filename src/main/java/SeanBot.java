import java.util.Scanner;

public class SeanBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm SeanBot\n" + "What can I do for you?");
        Task[] tasks = new Task[100];
        int counter = 0;

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
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                } else if (first.equals("mark")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The task number of mark cannot be empty");
                    }
                    int second = Integer.parseInt(part[1]) - 1;
                    if (second < 0 || second >= counter) {
                        throw new SeanBotException("The task number must be valid");
                    }
                    tasks[second].Done();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[second]);
                } else if (first.equals("unmark")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The task number of unmark cannot be empty");
                    }
                    int second = Integer.parseInt(part[1]) - 1;
                    if (second < 0 || second >= counter) {
                        throw new SeanBotException("The task number must be valid");
                    }
                    tasks[second].Undone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[second]);
                } else if (first.equals("todo")) {
                    if (part.length < 2) {
                        throw new SeanBotException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(part[1]);
                    tasks[counter] = todo;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                } else if (first.equals("deadline")) {
                    String[] specifications = part[1].split(" /by ");
                    if (specifications.length < 2) {
                        throw new SeanBotException("The description or a deadline cannot be empty.");
                    }
                    Task deadline = new Deadline(specifications[0], specifications[1]);
                    tasks[counter] = deadline;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + counter + " tasks in the list.");
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
                    tasks[counter] = event;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + counter + " tasks in the list.");
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
