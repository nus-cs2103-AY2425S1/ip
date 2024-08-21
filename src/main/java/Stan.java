import java.util.ArrayList;
import java.util.Scanner;

public class Stan {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            String[] words = input.split(" ", 2);
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");

            try {
                CommandType commandType = getCommandType(words[0]);

                switch (commandType) {
                    case BYE:
                        System.out.println(" Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        return;

                    case LIST:
                        getList();
                        break;

                    case MARK:
                        handleMarkUnmark(words, true);
                        break;

                    case UNMARK:
                        handleMarkUnmark(words, false);
                        break;

                    case TODO:
                        handleTodoCommand(words);
                        break;

                    case DEADLINE:
                        handleDeadlineCommand(words);
                        break;

                    case EVENT:
                        handleEventCommand(words);
                        break;

                    case DELETE:
                        handleDeleteCommand(words);
                        break;

                    default:
                        throw new StanInvalidCommandException();
                }

            } catch (StanException e) {
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println(" OOPS!!! The task number must be a valid integer.");
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static void greet() {
        String logo = "Stan";
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + logo);
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");
    }

    private static CommandType getCommandType(String command) throws StanInvalidCommandException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StanInvalidCommandException();
        }
    }

    private static void getList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("_____________________________________________________________");
    }

    private static void handleMarkUnmark(String[] words, boolean isMark) throws StanException {
        if (words.length < 2) {
            throw new StanMissingArgumentException("You need to specify the task number to " + (isMark ? "mark" : "unmark") + ".");
        }
        int taskNumber = Integer.parseInt(words[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new StanInvalidArgumentException("The task number is out of range.");
        }
        Task task = tasks.get(taskNumber);
        if (isMark) {
            task.markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    private static void handleTodoCommand(String[] words) throws StanMissingArgumentException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new StanMissingArgumentException("The description of a todo cannot be empty.");
        }
        tasks.add(new Todo(words[1]));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleDeadlineCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2 || !words[1].contains("/by")) {
            throw new StanMissingArgumentException("The description of a deadline must include a '/by' clause.");
        }
        String[] parts = words[1].split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new StanInvalidArgumentException("The deadline description or time cannot be empty.");
        }
        tasks.add(new Deadline(parts[0], parts[1]));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleEventCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2 || !words[1].contains("/from") || !words[1].contains("/to")) {
            throw new StanMissingArgumentException("The description of an event must include '/from' and '/to' clauses.");
        }
        String[] parts = words[1].split(" /from ");
        String[] times = parts[1].split(" /to ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            throw new StanInvalidArgumentException("The event description, start, or end time cannot be empty.");
        }
        tasks.add(new Event(parts[0], times[0], times[1]));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleDeleteCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2) {
            throw new StanMissingArgumentException("You need to specify the task number to delete.");
        }
        int taskNumber = Integer.parseInt(words[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new StanInvalidArgumentException("The task number is out of range.");
        }
        Task removedTask = tasks.remove(taskNumber);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
