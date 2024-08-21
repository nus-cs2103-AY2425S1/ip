import java.util.Scanner;
public class Stan {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        String logo = "Stan";
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + logo);
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            String[] words = input.split(" ", 2);
            ;
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");
            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("_____________________________________________________________");
                } else if (words[0].equalsIgnoreCase("mark")) {
                    if (words.length < 2) {
                        throw new StanException("You need to specify the task number to mark.");
                    }
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= taskCount) {
                        throw new StanException("The task number is out of range.");
                    }
                    tasks[taskNumber].markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks[taskNumber]);
                    System.out.println("____________________________________________________________");
                } else if (words[0].equalsIgnoreCase("unmark")) {
                    if (words.length < 2) {
                        throw new StanException("You need to specify the task number to unmark.");
                    }
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= taskCount) {
                        throw new StanException("The task number is out of range.");
                    }
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[taskNumber]);
                    System.out.println("____________________________________________________________");
                } else if (words[0].equalsIgnoreCase("todo")) {
                    if (words.length < 2 || words[1].trim().isEmpty()) {
                        throw new StanException("The description of a todo cannot be empty.");
                    }
                    tasks[taskCount] = new Todo(words[1]);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (words[0].equalsIgnoreCase("deadline")) {
                    if (words.length < 2 || !words[1].contains("/by")) {
                        throw new StanException("The description of a deadline must include a '/by' clause.");
                    }
                    String[] parts = words[1].split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new StanException("The deadline description or time cannot be empty.");
                    }
                    tasks[taskCount] = new Deadline(parts[0], parts[1]);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (words[0].equalsIgnoreCase("event")) {
                    if (words.length < 2 || !words[1].contains("/from") || !words[1].contains("/to")) {
                        throw new StanException("The description of an event must include '/from' and '/to' clauses.");
                    }
                    String[] parts = words[1].split(" /from ");
                    String[] times = parts[1].split(" /to ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                        throw new StanException("The event description, start, or end time cannot be empty.");
                    }
                    tasks[taskCount] = new Event(parts[0], times[0], times[1]);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new StanException("I'm sorry, but I don't understand that command.");
                }
            } catch (StanException e) {
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println(" OOPS!!! The task number must be a valid integer.");
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
