import java.util.Scanner;
import java.util.ArrayList;

public class EchoBot {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm EchoBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        label:
        while (true) {
            String userInput = scanner.nextLine();
            String[] inputParts = userInput.split(" ", 2); // Splits input into command and the rest

            String command = inputParts[0];

            switch (command) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye! Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break label;
                case "list":
                    listTasks();
                    break;
                case "mark": {
                    handleMarkCommand(inputParts);
                    break;
                }
                case "unmark": {
                    handleUnmarkCommand(inputParts);
                    break;
                }
                case "todo": {
                    handleTodoCommand(inputParts);
                    break;
                }
                case "deadline": {
                    handleDeadlineCommand(inputParts);
                    break;
                }
                case "event": {
                    handleEventCommand(inputParts);
                    break;
                }
                case "delete":
                    handleDeleteCommand(inputParts);
                    break;
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println(" I'm sorry, I don't recognize that command.");
                    System.out.println("____________________________________________________________");
                    break;
            }
        }

        scanner.close();
    }
    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
    private static void handleMarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to mark as done.");
            System.out.println("____________________________________________________________");
        } else {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                tasks.get(taskNumber).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Great! I've marked this task as done:");
                System.out.println("   " + tasks.get(taskNumber));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }
    }
    private static void handleUnmarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to unmark.");
            System.out.println("____________________________________________________________");
        } else {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                tasks.get(taskNumber).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK! I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(taskNumber));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static void handleTodoCommand(String[] inputParts) {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of a todo cannot be empty.");
            System.out.println("____________________________________________________________");
        } else {
            tasks.add(new Todo(inputParts[1]));
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.get(tasks.size() - 1));
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void handleDeadlineCommand(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/by ")) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of a deadline must include a due date.");
            System.out.println("____________________________________________________________");
        } else {
            String[] details = inputParts[1].split(" /by ");
            if (details.length < 2 || details[0].trim().isEmpty()) {
                System.out.println("____________________________________________________________");
                System.out.println(" The description of a deadline cannot be empty.");
                System.out.println("____________________________________________________________");
            } else {
                tasks.add(new Deadline(details[0], details[1]));
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks.get(tasks.size() - 1));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static void handleEventCommand(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/from ") || !inputParts[1].contains("/to ")) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of an event must include start and end times.");
            System.out.println("____________________________________________________________");
        } else {
            String[] details = inputParts[1].split(" /from | /to ");
            if (details.length < 3 || details[0].trim().isEmpty()) {
                System.out.println("____________________________________________________________");
                System.out.println(" The description of an event cannot be empty.");
                System.out.println("____________________________________________________________");
            } else {
                tasks.add(new Event(details[0], details[1], details[2]));
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks.get(tasks.size() - 1));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
        }
    }
    private static void handleDeleteCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to delete.");
            System.out.println("____________________________________________________________");
        } else {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                Task removedTask = tasks.remove(taskNumber);
                System.out.println("____________________________________________________________");
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removedTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }
    }
}
