import java.util.Scanner;

import static utility.Printer.printWithDivider;

import commands.CommandType;
import commands.InvalidCommandException;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

public class Thanos {
    private static final Storage storage = new Storage("data.txt");
    private static final TaskList tasks = new TaskList(storage);

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
        tasks.listTasks();
    }

    private static void markTask(String argument) throws InvalidCommandException {
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
            tasks.mark(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }

    private static void unmarkTask(String argument) throws InvalidCommandException {
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
            tasks.unmark(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }

    private static void addTodo(String argument) throws InvalidCommandException {
        if (argument.isEmpty()) {
            throw new InvalidCommandException(
                    "No task description provided. Please use the correct format: 'todo [task]'"
            );
        }
        Todo todo = new Todo(argument);
        tasks.add(todo);
    }

    private static void addDeadline(String argument) throws InvalidCommandException {
        String[] detailsArr = argument.split(" /by ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException(
                    "Invalid input format. Please use the correct format: 'deadline [task] /by [due date]'"
            );
        }
        Deadline deadline = new Deadline(detailsArr[0], detailsArr[1]);
        tasks.add(deadline);
    }

    private static void addEvent(String argument) throws InvalidCommandException {
        String[] detailsArr = argument.split(" /from ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException("Invalid input format." +
                    "Please use the correct format: 'event [task] /from [start time] /to [end time]'");
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
    }

    private static void deleteTask(String argument) throws InvalidCommandException {
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
            tasks.remove(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }

    public static void main(String[] args) {
        printWithDivider("Hello! I'm Thanos!\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] inputArr = parseInput(userInput);
                CommandType commandType = CommandType.getCommandType(inputArr[0]);
                String argument = inputArr[1];
                switch (commandType) {
                case BYE:
                    printWithDivider("Bye. Hope to see you again soon!\n");
                    return;
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    markTask(argument);
                    break;
                case UNMARK:
                    unmarkTask(argument);
                    break;
                case TODO:
                    addTodo(argument);
                    break;
                case DEADLINE:
                    addDeadline(argument);
                    break;
                case EVENT:
                    addEvent(argument);
                    break;
                case DELETE:
                    deleteTask(argument);
                    break;
                default:
                    throw new InvalidCommandException(
                            "Oops! I don't recognise the command you entered. Please enter a valid command."
                    );
                }
            } catch (InvalidCommandException e) {
                printWithDivider(e.getMessage() + "\n");
            }
        }
    }
}
