import java.util.ArrayList;
import java.util.Scanner;

public class Chatsy {
    static final String name = "Chatsy";
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);
    static final ArrayList<Task> tasks = new ArrayList<>();

    public static void greet() {
        System.out.println(line);
        System.out.println("\tHello! I'm " + name + "\n\tWhat can I do for you?");
        System.out.println(line);
    }

    public static void output(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }

    public static void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
        scanner.close(); // Close the scanner to prevent resource leaks
    }

    public static void addTask(Task task) {
        tasks.add(task);
        output("\tGot it. I've added this task:\n\t  " + task + "\n\tNow you have " + tasks.size() + " tasks in the list.");
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            output("\tNo tasks to list.");
        } else {
            StringBuilder listOutput = new StringBuilder("\tHere are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                listOutput.append("\t").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            output(listOutput.toString());
        }
    }

    public static void markTask(int index) throws InvalidTaskNumberException {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsDone();
            output("\tNice! I've marked this task as done:\n\t  " + tasks.get(index - 1));
        } else {
            throw new InvalidTaskNumberException();
        }
    }

    public static void unmarkTask(int index) throws InvalidTaskNumberException {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsNotDone();
            output("\tOK, I've marked this task as not done yet:\n\t  " + tasks.get(index - 1));
        } else {
            throw new InvalidTaskNumberException();
        }
    }

    public static void nextCommand() {
        try {
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 2);
            String action = parts[0];

            switch (action) {
                case "bye":
                    exit();
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    if (parts.length > 1) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        markTask(taskNumber);
                    } else {
                        output("\tPlease specify the task number to mark.");
                    }
                    break;
                case "unmark":
                    if (parts.length > 1) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        unmarkTask(taskNumber);
                    } else {
                        output("\tPlease specify the task number to unmark.");
                    }
                    break;
                case "todo":
                    if (parts.length > 1) {
                        addTask(new Todo(parts[1]));
                    } else {
                        throw new EmptyDescriptionException("todo");
                    }
                    break;
                case "deadline":
                    if (parts.length > 1) {
                        String[] deadlineParts = parts[1].split(" /by ", 2);
                        if (deadlineParts.length > 1) {
                            addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                        } else {
                            output("\tPlease specify the deadline in the format: description /by deadline");
                        }
                    } else {
                        throw new EmptyDescriptionException("deadline");
                    }
                    break;
                case "event":
                    if (parts.length > 1) {
                        String[] eventParts = parts[1].split(" /from | /to ", 3);
                        if (eventParts.length == 3) {
                            addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
                        } else {
                            output("\tPlease specify the event in the format: description /from start_time /to end_time");
                        }
                    } else {
                        throw new EmptyDescriptionException("event");
                    }
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (ChatsyException e) {
            output("\tOOPS!!! " + e.getMessage());
        } catch (NumberFormatException e) {
            output("\tPlease enter a valid number for task selection.");
        } catch (Exception e) {
            output("\tAn unexpected error occurred: " + e.getMessage());
        } finally {
            nextCommand();
        }
    }

    public static void main(String[] args) {
        greet();
        nextCommand();
    }
}


class ChatsyException extends Exception {
    public ChatsyException(String message) {
        super(message);
    }
}

class EmptyDescriptionException extends ChatsyException {
    public EmptyDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty.");
    }
}

class InvalidCommandException extends ChatsyException {
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}

class InvalidTaskNumberException extends ChatsyException {
    public InvalidTaskNumberException() {
        super("The task number you provided is invalid.");
    }
}