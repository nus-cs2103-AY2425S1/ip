import java.util.Scanner;

public class ShoAI {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm ShoAI");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            try {
                if (handleInput(input)) {
                    break;  // Exit the loop if the user says "bye"
                }
            } catch (ShoAIException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static boolean handleInput(String input) throws ShoAIException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                return true; // Return true to exit the loop
            case "list":
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
                break;
            case "mark":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to mark.");
                }
                int markIndex = Integer.parseInt(words[1]) - 1;
                if (markIndex < 0 || markIndex >= taskCount) {
                    throw new ShoAIException("Task number " + (markIndex + 1) + " does not exist.");
                }
                tasks[markIndex].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[markIndex]);
                System.out.println("____________________________________________________________");
                break;
            case "unmark":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to unmark.");
                }
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                if (unmarkIndex < 0 || unmarkIndex >= taskCount) {
                    throw new ShoAIException("Task number " + (unmarkIndex + 1) + " does not exist.");
                }
                tasks[unmarkIndex].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[unmarkIndex]);
                System.out.println("____________________________________________________________");
                break;
            case "todo":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new ShoAIException("The description of a todo cannot be empty.");
                }
                tasks[taskCount] = new Todo(words[1]);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
                break;
            case "deadline":
                if (words.length < 2) {
                    throw new ShoAIException("The description of a deadline cannot be empty.");
                }
                String[] deadlineParts = words[1].split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new ShoAIException("The deadline description or date/time cannot be empty.");
                }
                tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
                break;
            case "event":
                if (words.length < 2) {
                    throw new ShoAIException("The description of an event cannot be empty.");
                }
                String[] eventParts = words[1].split(" /from ");
                if (eventParts.length < 2) {
                    throw new ShoAIException("The event description or start time cannot be empty.");
                }
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2 || eventParts[0].trim().isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                    throw new ShoAIException("The event description, start time, or end time cannot be empty.");
                }
                tasks[taskCount] = new Event(eventParts[0], timeParts[0], timeParts[1]);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
                break;
            default:
                throw new ShoAIException("Sorry, I don't understand that command.");
        }

        return false; // Continue the loop for other commands
    }
}
