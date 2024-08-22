import java.util.Scanner;

public class Yapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Yapper");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] userInputParts = userInput.split(" ", 2);
                String command = userInputParts[0];

                switch (command) {
                    case "bye":
                        System.out.println("____________________________________________________________");
                        System.out.println(" Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        return;

                    case "list":
                        System.out.println("____________________________________________________________");
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println(" " + (i + 1) + "." + tasks[i]);
                        }
                        System.out.println("____________________________________________________________");
                        break;

                    case "mark":
                        if (userInputParts.length < 2) {
                            throw new Exception("OOPS!!! The description of a mark command cannot be empty.");
                        }
                        int taskNumber = Integer.parseInt(userInputParts[1]) - 1;
                        tasks[taskNumber].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                        break;

                    case "unmark":
                        if (userInputParts.length < 2) {
                            throw new Exception("OOPS!!! The description of an unmark command cannot be empty.");
                        }
                        taskNumber = Integer.parseInt(userInputParts[1]) - 1;
                        tasks[taskNumber].markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                        break;

                    case "todo":
                        if (userInputParts.length < 2) {
                            throw new Exception("OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task task = new Todo(userInputParts[1]);
                        tasks[taskCount] = task;
                        taskCount++;
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + task);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    case "deadline":
                        if (userInputParts.length < 2) {
                            throw new Exception("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] details = userInputParts[1].split(" /by ");
                        if (details.length < 2) {
                            throw new Exception("OOPS!!! The deadline format should be: deadline [task] /by [date/time]");
                        }
                        task = new Deadline(details[0], details[1]);
                        tasks[taskCount] = task;
                        taskCount++;
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + task);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    case "event":
                        if (userInputParts.length < 2) {
                            throw new Exception("OOPS!!! The description of an event cannot be empty.");
                        }
                        details = userInputParts[1].split(" /from ");
                        if (details.length < 2) {
                            throw new Exception("OOPS!!! The event format should be: event [task] /from [start time] /to [end time]");
                        }
                        String[] fromTo = details[1].split(" /to ");
                        if (fromTo.length < 2) {
                            throw new Exception("OOPS!!! The event format should be: event [task] /from [start time] /to [end time]");
                        }
                        task = new Event(details[0], fromTo[0], fromTo[1]);
                        tasks[taskCount] = task;
                        taskCount++;
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + task);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    default:
                        throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }
}
