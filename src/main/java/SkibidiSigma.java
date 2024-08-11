import commands.CommandType;

import java.util.ArrayList;
import java.util.Scanner;

import exception.SkibidiSigmaException;
import exception.SkibidiSigmaInvalidArgException;
import exception.SkibidiSigmaInvalidTaskException;
import exception.SkibidiSigmaMissingArgException;
import exception.SkibidiSigmaNaNException;
import exception.SkibidiSigmaUnknownCommandException;

public class SkibidiSigma {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static final String horizontalLine = "____________________________________________________________";

    private static void handleMarkCommand(String userInput) throws SkibidiSigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new SkibidiSigmaInvalidTaskException(taskNumber);
            }
            System.out.println(horizontalLine);
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(taskNumber - 1));
            System.out.println(horizontalLine);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SkibidiSigmaInvalidArgException(CommandType.MARK);
        } catch (NumberFormatException e) {
            throw new SkibidiSigmaNaNException();
        }
    }

    private static void handleUnmarkCommand(String userInput) throws SkibidiSigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new SkibidiSigmaInvalidTaskException(taskNumber);
            }
            System.out.println(horizontalLine);
            tasks.get(taskNumber - 1).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(taskNumber - 1));
            System.out.println(horizontalLine);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SkibidiSigmaInvalidArgException(CommandType.UNMARK);
        } catch (NumberFormatException e) {
            throw new SkibidiSigmaNaNException();
        }
    }

    private static void handleTodoCommand(String userInput) throws SkibidiSigmaException {
        try {
            String description = userInput.substring(5).trim();
            Task todo = new Todo(description);
            tasks.add(todo);
            System.out.println(horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + todo);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(horizontalLine);
        } catch (StringIndexOutOfBoundsException e) {
            throw new SkibidiSigmaMissingArgException(CommandType.TODO);
        }
    }

    private static void handleDeadlineCommand(String userInput) throws SkibidiSigmaException {
        try {
            String[] parts = userInput.split(" /by ");
            if (parts.length < 2) {
                throw new SkibidiSigmaMissingArgException(CommandType.DEADLINE);
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            Task deadline = new Deadline(description, by);
            tasks.add(deadline);
            System.out.println(horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(horizontalLine);
        } catch (StringIndexOutOfBoundsException e) {
            throw new SkibidiSigmaInvalidArgException(CommandType.DEADLINE);
        }
    }

    private static void handleEventCommand(String userInput) throws SkibidiSigmaException {
        try {
            String[] parts = userInput.split(" /from ");
            if (parts.length < 2) {
                throw new SkibidiSigmaMissingArgException(CommandType.EVENT);
            }
            String description = parts[0].substring(6).trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new SkibidiSigmaMissingArgException(CommandType.EVENT);
            }
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            if (description.isEmpty()) {
                throw new SkibidiSigmaMissingArgException(CommandType.EVENT);
            }
            Task event = new Event(description, from, to);
            tasks.add(event);
            System.out.println(horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(horizontalLine);
        } catch (StringIndexOutOfBoundsException e) {
            throw new SkibidiSigmaInvalidArgException(CommandType.EVENT);
        }
    }

    private static void handleDeleteCommand(String userInput) throws SkibidiSigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new SkibidiSigmaInvalidTaskException(taskNumber);
            }
            System.out.println(horizontalLine);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + tasks.get(taskNumber - 1));
            tasks.remove(taskNumber - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(horizontalLine);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SkibidiSigmaInvalidArgException(CommandType.DELETE);
        } catch (NumberFormatException e) {
            throw new SkibidiSigmaNaNException();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine + "\nHello! I'm SkibidiSigma!" + "\nWhat can I do for you?\n" + horizontalLine);

        while (true) {
            String userInput = scanner.nextLine().trim();
            String commandWord = userInput.split(" ")[0].toUpperCase();

            try {
                if (!CommandType.isValidCommand(commandWord)) {
                    throw new SkibidiSigmaUnknownCommandException(userInput);
                }
                CommandType commandType = CommandType.valueOf(commandWord);

                switch (commandType) {
                    case BYE:
                        System.out.println(
                                horizontalLine +
                                        "\nCatch ya on the flip side, my dude! See ya soon!\n" +
                                        horizontalLine);
                        scanner.close();
                        return;
                    case LIST:
                        System.out.println(horizontalLine);
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        System.out.println(horizontalLine);
                        break;
                    case MARK:
                        handleMarkCommand(userInput);
                        break;
                    case UNMARK:
                        handleUnmarkCommand(userInput);
                        break;
                    case TODO:
                        handleTodoCommand(userInput);
                        break;
                    case DEADLINE:
                        handleDeadlineCommand(userInput);
                        break;
                    case EVENT:
                        handleEventCommand(userInput);
                        break;
                    case DELETE:
                        handleDeleteCommand(userInput);
                        break;
                    default:
                        throw new SkibidiSigmaUnknownCommandException(userInput);
                }
            } catch (SkibidiSigmaException e) {
                System.out.println(horizontalLine);
                System.out.println(e);
                System.out.println(horizontalLine);
            } catch (Exception e) {
                System.out.println(horizontalLine);
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println(horizontalLine);
            }
        }
    }
}
