import java.util.ArrayList;
import java.util.Scanner;

public class Thanos {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void customPrint(String s) {
        System.out.print(s);
        System.out.println("-".repeat(50));
    }

    private static String[] parseInput(String input) throws InvalidCommandException {
        if (input.trim().isEmpty()) {
            throw new InvalidCommandException("No input provided. Please enter a command.");
        }

        String[] inputArr = input.trim().split(" ", 2);
        String command = inputArr[0].toLowerCase();
        if (inputArr.length == 1) {
            return new String[] { command, "" };
        }

        String argument = inputArr[1].trim();
        return new String[] { command, argument };
    }

    private static void listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(String.format("%d.%s\n", i + 1, task));
        }
        customPrint(sb.toString());
    }

    private static void markTask(String argument) throws InvalidCommandException{
        if (argument.isEmpty()) {
            throw new InvalidCommandException(
                "No task index provided. Please use the correct format: 'mark [task index]'"
            );
        }

        if (argument.split(" ").length != 1) {
            throw new InvalidCommandException(
                "Invalid input format. Please use the correct format: 'mark [task index]'"
            );
        }

        try {
            int index = Integer.parseInt(argument) - 1;
            Task task = tasks.get(index);
            task.setIsDone(true);
            customPrint(String.format("Nice! I've marked this task as done:\n  %s\n", task));
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }

    private static void unmarkTask(String argument) throws InvalidCommandException{
        if (argument.isEmpty()) {
            throw new InvalidCommandException(
                "No task index provided. Please use the correct format: 'unmark [task index]'"
            );
        }

        if (argument.split(" ").length != 1) {
            throw new InvalidCommandException(
                "Invalid input format. Please use the correct format: 'unmark [task index]'"
            );
        }

        try {
            int index = Integer.parseInt(argument) - 1;
            Task task = tasks.get(index);
            task.setIsDone(false);
            customPrint(String.format("OK, I've marked this task as not done yet:\n  %s\n", task));
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }

    private static void printTaskAdded(Task task) {
        customPrint(String.format(
            "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", task, tasks.size()
        ));
    }

    private static void addTodo(String argument) throws InvalidCommandException{
        if (argument.isEmpty()) {
            throw new InvalidCommandException(
                "No task description provided. Please use the correct format: 'todo [task]'"
            );
        }
        Todo todo = new Todo(argument);
        tasks.add(todo);
        printTaskAdded(todo);
    }

    private static void addDeadline(String argument) throws InvalidCommandException{
        String[] detailsArr = argument.split(" /by ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException(
                "Invalid input format. Please use the correct format: 'deadline [task] /by [due date]'"
            );
        }
        Deadline deadline = new Deadline(detailsArr[0], detailsArr[1]);
        tasks.add(deadline);
        printTaskAdded(deadline);
    }

    private static void addEvent(String argument) throws InvalidCommandException {
        String[] detailsArr = argument.split(" /from ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException(
                "Invalid input format. Please use the correct format: 'event [task] /from [start time] /to [end time]'"
            );
        }

        String description = detailsArr[0];
        String[] fromToArr = detailsArr[1].split(" /to ");
        if (fromToArr.length != 2) {
            throw new InvalidCommandException(
                "Invalid input format. Please ensure both start and end times are provided."
            );
        }

        Event event = new Event(description, fromToArr[0], fromToArr[1]);
        tasks.add(event);
        printTaskAdded(event);
    }

    private static void deleteTask(String argument) throws InvalidCommandException{
        if (argument.isEmpty()) {
            throw new InvalidCommandException(
                "No task index provided. Please use the correct format: 'delete [task index]'"
            );
        }

        if (argument.split(" ").length != 1) {
            throw new InvalidCommandException(
                "Invalid input format. Please use the correct format: 'delete [task index]'"
            );
        }

        try {
            int index = Integer.parseInt(argument) - 1;
            Task task = tasks.remove(index);
            customPrint(String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n", task, tasks.size())
            );
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }

    public static void main(String[] args) {
        customPrint("Hello! I'm Thanos!\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] inputArr = parseInput(userInput);
                String command = inputArr[0];
                String argument = inputArr[1];
                switch (command) {
                    case "bye":
                        customPrint("Bye. Hope to see you again soon!\n");
                        return;
                    case "list":
                        listTasks();
                        break;
                    case "mark":
                        markTask(argument);
                        break;
                    case "unmark":
                        unmarkTask(argument);
                        break;
                    case "todo":
                        addTodo(argument);
                        break;
                    case "deadline":
                        addDeadline(argument);
                        break;
                    case "event":
                        addEvent(argument);
                        break;
                    case "delete":
                        deleteTask(argument);
                        break;
                    default:
                        throw new InvalidCommandException(
                            "Oops! I don't recognise the command you entered. Please enter a valid command."
                        );
                }
            } catch (InvalidCommandException e) {
                customPrint(e.getMessage() + "\n");
            }
        }
    }
}
