import java.util.Scanner;

public class SkibidiSigma {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static final String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine + "\nHello! I'm SkibidiSigma!" + "\nWhat can I do for you?\n" + horizontalLine);

        while (true) {
            String userInput = scanner.nextLine().trim();

            try {
                if ("bye".equalsIgnoreCase(userInput)) {
                    System.out.println(
                            horizontalLine +
                                    "\nCatch ya on the flip side, my dude! See ya soon!\n" +
                                    horizontalLine);
                    break;
                }

                if ("list".equalsIgnoreCase(userInput)) {
                    System.out.println(horizontalLine);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    System.out.println(horizontalLine);
                } else if (userInput.toLowerCase().startsWith("mark")) {
                    handleMarkCommand(userInput);
                } else if (userInput.toLowerCase().startsWith("unmark")) {
                    handleUnmarkCommand(userInput);
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    handleTodoCommand(userInput);
                } else if (userInput.toLowerCase().startsWith("deadline")) {
                    handleDeadlineCommand(userInput);
                } else if (userInput.toLowerCase().startsWith("event")) {
                    handleEventCommand(userInput);
                } else {
                    throw new SkibidiSigmaException("Unknown command. Please try again.");
                }
            } catch (SkibidiSigmaException e) {
                System.out.println(horizontalLine);
                System.out.println(e.getMessage());
                System.out.println(horizontalLine);
            } catch (Exception e) {
                System.out.println(horizontalLine);
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println(horizontalLine);
            }
        }

        scanner.close();
    }

    private static void handleMarkCommand(String userInput) throws SkibidiSigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > taskCount) {
                throw new SkibidiSigmaException("Task number out of range. Please enter a valid task number.");
            }
            System.out.println(horizontalLine);
            tasks[taskNumber - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber - 1]);
            System.out.println(horizontalLine);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SkibidiSigmaException("Invalid command syntax. Usage: mark <task_number>");
        } catch (NumberFormatException e) {
            throw new SkibidiSigmaException("Invalid task number. Please enter a numeric value.");
        }
    }

    private static void handleUnmarkCommand(String userInput) throws SkibidiSigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > taskCount) {
                throw new SkibidiSigmaException("Task number out of range. Please enter a valid task number.");
            }
            System.out.println(horizontalLine);
            tasks[taskNumber - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber - 1]);
            System.out.println(horizontalLine);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SkibidiSigmaException("Invalid command syntax. Usage: unmark <task_number>");
        } catch (NumberFormatException e) {
            throw new SkibidiSigmaException("Invalid task number. Please enter a numeric value.");
        }
    }

    private static void handleTodoCommand(String userInput) throws SkibidiSigmaException {
        try {
            String description = userInput.substring(5).trim();
            Task todo = new Todo(description);
            tasks[taskCount++] = todo;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + todo);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new SkibidiSigmaException("The description of a deadline cannot be empty. Usage: todo <description>");
        }
    }

    private static void handleDeadlineCommand(String userInput) throws SkibidiSigmaException {
        try {
            String[] parts = userInput.split(" /by ");
            if (parts.length < 2) {
                throw new SkibidiSigmaException("Invalid command syntax for deadline. Usage: deadline <description> /by <date>");
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            Task deadline = new Deadline(description, by);
            tasks[taskCount++] = deadline;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new SkibidiSigmaException("The description of a deadline cannot be empty. Usage: deadline <description> /by <date>");
        }
    }

    private static void handleEventCommand(String userInput) throws SkibidiSigmaException {
        try {
            String[] parts = userInput.split(" /from ");
            if (parts.length < 2) {
                throw new SkibidiSigmaException("Invalid command syntax for event. Usage: event <description> /from <start> /to <end>");
            }
            String description = parts[0].substring(6).trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new SkibidiSigmaException("Invalid command syntax for event. Usage: event <description> /from <start> /to <end>");
            }
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            if (description.isEmpty()) {
                throw new SkibidiSigmaException("The description of an event cannot be empty. Usage: event <description> /from <start> /to <end>\"");
            }
            Task event = new Event(description, from, to);
            tasks[taskCount++] = event;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new SkibidiSigmaException("The description of an event cannot be empty. Usage: event <description> /from <start> /to <end>\"");
        }
    }
}
