import java.util.ArrayList;
import java.util.Scanner;

public class Orion {
    static Scanner scanner = new Scanner(System.in);
    static String horizontalLine = "────────────────────────────────────────";
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Orion");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        label:
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            Command command = Command.fromString(parts[0]);

            try {
                switch (command) {
                    case BYE:
                        break label;

                    case LIST:
                        validateListCommand(parts);
                        System.out.println(horizontalLine);
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        System.out.println(horizontalLine);
                        break;

                    case MARK:
                        validateMarkCommand(parts);
                        int markTaskNumber = Integer.parseInt(parts[1]) - 1;
                        tasks.get(markTaskNumber).markAsDone();
                        System.out.println(horizontalLine);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(markTaskNumber));
                        System.out.println(horizontalLine);
                        break;

                    case UNMARK:
                        validateUnmarkCommand(parts);
                        int unmarkTaskNumber = Integer.parseInt(parts[1]) - 1;
                        tasks.get(unmarkTaskNumber).markAsNotDone();
                        System.out.println(horizontalLine);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(unmarkTaskNumber));
                        System.out.println(horizontalLine);
                        break;

                    case TODO:
                        validateTodoCommand(parts);
                        tasks.add(new Todo(parts[1]));
                        System.out.println(horizontalLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(horizontalLine);
                        break;

                    case DEADLINE:
                        validateDeadlineCommand(parts);
                        String[] words = parts[1].split(" ");
                        StringBuilder description = new StringBuilder();
                        StringBuilder by = new StringBuilder();
                        boolean foundBy = false;

                        for (String word : words) {
                            if (word.equals("by")) {
                                foundBy = true;
                            } else if (foundBy) {
                                by.append(word).append(" ");
                            } else {
                                description.append(word).append(" ");
                            }
                        }

                        tasks.add(new Deadline(description.toString().trim(), by.toString().trim()));
                        System.out.println(horizontalLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(horizontalLine);
                        break;

                    case EVENT:
                        validateEventCommand(parts);
                        String[] eventWords = parts[1].split(" ");
                        StringBuilder eventDescription = new StringBuilder();
                        StringBuilder from = new StringBuilder();
                        StringBuilder to = new StringBuilder();
                        boolean foundFrom = false;
                        boolean foundTo = false;

                        for (String word : eventWords) {
                            if (word.equals("from")) {
                                foundFrom = true;
                            } else if (word.equals("to")) {
                                foundTo = true;
                            } else if (foundTo) {
                                to.append(word).append(" ");
                            } else if (foundFrom) {
                                from.append(word).append(" ");
                            } else {
                                eventDescription.append(word).append(" ");
                            }
                        }

                        tasks.add(new Event(eventDescription.toString().trim(), from.toString().trim(), to.toString().trim()));
                        System.out.println(horizontalLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(horizontalLine);
                        break;

                    case DELETE:
                        validateDeleteCommand(parts);
                        int deleteTaskNumber = Integer.parseInt(parts[1]) - 1;
                        Task removedTask = tasks.remove(deleteTaskNumber);
                        System.out.println(horizontalLine);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(horizontalLine);
                        break;

                    case UNKNOWN:
                    default:
                        throw new ValidationException("Unknown command: " + parts[0]);
                }
            } catch (ValidationException e) {
                System.out.println(horizontalLine);
                System.out.println("Error: " + e.getMessage());
                System.out.println(horizontalLine);
            }
        }

        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    // Validation methods remain the same...

    public static void validateListCommand(String[] parts) throws ValidationException {
        if (parts.length > 1) {
            throw new ValidationException("The 'list' command does not accept any arguments.");
        }
    }

    public static void validateMarkCommand(String[] parts) throws ValidationException {
        if (parts.length != 2 || !isNumeric(parts[1])) {
            throw new ValidationException("Mark command requires a valid task number.");
        }

        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size() || tasks.get(taskNumber) == null) {
            throw new ValidationException("Task number " + (taskNumber + 1) + " does not exist.");
        }
    }

    public static void validateUnmarkCommand(String[] parts) throws ValidationException {
        if (parts.length != 2 || !isNumeric(parts[1])) {
            throw new ValidationException("Unmark command requires a valid task number.");
        }

        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size() || tasks.get(taskNumber) == null) {
            throw new ValidationException("Task number " + (taskNumber + 1) + " does not exist.");
        }
    }

    public static void validateTodoCommand(String[] parts) throws ValidationException {
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new ValidationException("Todo command requires a description.");
        }
    }

    public static void validateDeadlineCommand(String[] parts) throws ValidationException {
        if (parts.length != 2 || !parts[1].contains("by")) {
            throw new ValidationException("Deadline command requires a description and a 'by' clause.");
        }
    }

    public static void validateEventCommand(String[] parts) throws ValidationException {
        if (parts.length != 2 || !parts[1].contains("from") || !parts[1].contains("to")) {
            throw new ValidationException("Event command requires a description and both 'from' and 'to' clauses.");
        }
    }

    public static void validateDeleteCommand(String[] parts) throws ValidationException {
        if (parts.length != 2 || !isNumeric(parts[1])) {
            throw new ValidationException("Delete command requires a valid task number.");
        }

        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size() || tasks.get(taskNumber) == null) {
            throw new ValidationException("Task number " + (taskNumber + 1) + " does not exist.");
        }
    }

    // Helper method to check if a string is numeric
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

