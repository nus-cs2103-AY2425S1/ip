import java.util.Scanner;

public class Lolo {

    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Lolo, your friendly task manager. 😊");
        System.out.println("What do you want to do today?\n");

        String userCommand;

        do {
            System.out.print("You: ");
            userCommand = scanner.nextLine();

            try {
                if (userCommand.equalsIgnoreCase("bye")) {
                    break;
                } else if (userCommand.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (userCommand.startsWith("todo ")) {
                    handleTodoCommand(userCommand);
                } else if (userCommand.startsWith("deadline ")) {
                    handleDeadlineCommand(userCommand);
                } else if (userCommand.startsWith("event ")) {
                    handleEventCommand(userCommand);
                } else if (userCommand.startsWith("mark ")) {
                    handleMarkCommand(userCommand);
                } else if (userCommand.startsWith("unmark ")) {
                    handleUnmarkCommand(userCommand);
                } else {
                    throw new LoloException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (LoloException e) {
                System.out.println("    OOPS!!! " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("    OOPS!!! Invalid task number format.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! Missing task description or date.");
            }

        } while (!userCommand.equalsIgnoreCase("bye"));

        System.out.println("Lolo: Bye. Hope to see you again soon! 👋");

        scanner.close();
    }

    public static void handleTodoCommand(String command) throws LoloException {
        String description = command.substring(5).trim();
        if (description.isEmpty()) {
            throw new LoloException("The description of a todo cannot be empty.");
        }
        addTask(new ToDo(description));
    }

    public static void handleDeadlineCommand(String command) throws LoloException {
        String[] parts = command.split(" /by ");
        if (parts.length != 2) {
            throw new LoloException("Invalid deadline format. Use 'deadline [description] /by [date]'.");
        }
        addTask(new Deadline(parts[0].substring(9).trim(), parts[1].trim()));
    }

    public static void handleEventCommand(String command) throws LoloException {
        String[] parts = command.split(" /from ");
        if (parts.length != 2) {
            throw new LoloException("Invalid event format. Use 'event [description] /from [start] /to [end]'.");
        }
        String[] fromTo = parts[1].split(" /to ");
        if (fromTo.length != 2) {
            throw new LoloException("Invalid event time format. Use '/from [start] /to [end]'.");
        }
        addTask(new Event(parts[0].substring(6).trim(), fromTo[0].trim(), fromTo[1].trim()));
    }

    public static void handleMarkCommand(String command) throws LoloException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]);
        markTaskAsDone(taskNumber);
    }

    public static void handleUnmarkCommand(String command) throws LoloException {
        int taskNumber = Integer.parseInt(command.split(" ")[1]);
        markTaskAsNotDone(taskNumber);
    }

    public static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + task);
            System.out.println("    Now you have " + taskCount + " task(s) in the list.");
        } else {
            System.out.println("    Task list is full! Cannot add more tasks.");
        }
    }

    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("    " + (i + 1) + "." + tasks[i]);
            }
        }
    }

    public static void markTaskAsDone(int taskNumber) throws LoloException {
        if (taskNumber > 0 && taskNumber <= taskCount) {
            tasks[taskNumber - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber - 1]);
        } else {
            throw new LoloException("Invalid task number.");
        }
    }

    public static void markTaskAsNotDone(int taskNumber) throws LoloException {
        if (taskNumber > 0 && taskNumber <= taskCount) {
            tasks[taskNumber - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber - 1]);
        } else {
            throw new LoloException("Invalid task number.");
        }
    }
}
