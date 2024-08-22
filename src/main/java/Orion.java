import java.util.Scanner;

public class Orion {
    static Scanner scanner = new Scanner(System.in);
    static String horizontalLine = "────────────────────────────────────────";
    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Orion");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        label:
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);

            try {
                switch (parts[0]) {
                    case "bye":
                        break label;

                    case "list":
                        validateListCommand(parts);
                        System.out.println(horizontalLine);
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + "." + tasks[i]);
                        }
                        System.out.println(horizontalLine);
                        break;

                    case "mark":
                        validateMarkCommand(parts);
                        int markTaskNumber = Integer.parseInt(parts[1]) - 1;
                        tasks[markTaskNumber].markAsDone();
                        System.out.println(horizontalLine);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[markTaskNumber]);
                        System.out.println(horizontalLine);
                        break;

                    case "unmark":
                        validateUnmarkCommand(parts);
                        int unmarkTaskNumber = Integer.parseInt(parts[1]) - 1;
                        tasks[unmarkTaskNumber].markAsNotDone();
                        System.out.println(horizontalLine);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[unmarkTaskNumber]);
                        System.out.println(horizontalLine);
                        break;

                    case "todo":
                        validateTodoCommand(parts);
                        tasks[taskCount] = new Todo(parts[1]);
                        taskCount++;
                        System.out.println(horizontalLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[taskCount - 1]);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        System.out.println(horizontalLine);
                        break;

                    case "deadline":
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

                        tasks[taskCount] = new Deadline(description.toString().trim(), by.toString().trim());
                        taskCount++;
                        System.out.println(horizontalLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[taskCount - 1]);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        System.out.println(horizontalLine);
                        break;

                    case "event":
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

                        tasks[taskCount] = new Event(eventDescription.toString().trim(), from.toString().trim(), to.toString().trim());
                        taskCount++;
                        System.out.println(horizontalLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[taskCount - 1]);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        System.out.println(horizontalLine);
                        break;

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

    // Validation methods
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
        if (taskNumber < 0 || taskNumber >= taskCount || tasks[taskNumber] == null) {
            throw new ValidationException("Task number " + (taskNumber + 1) + " does not exist.");
        }
    }

    public static void validateUnmarkCommand(String[] parts) throws ValidationException {
        if (parts.length != 2 || !isNumeric(parts[1])) {
            throw new ValidationException("Unmark command requires a valid task number.");
        }

        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= taskCount || tasks[taskNumber] == null) {
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

    // Helper method to check if a string is numeric
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}

