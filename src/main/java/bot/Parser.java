package bot;
import taskType.TaskBuilder;

/**
 * The Parser class is responsible for interpreting user commands and invoking
 * the appropriate methods to handle tasks such as creating, marking, unmarking,
 * deleting, and listing tasks. It interacts with the ListManager to manage the tasks
 * and FileManager to handle file operations.
 */
public class Parser {
    private ListManager chickenManager;
    private FileManager chickenFileManager;

    /**
     * Enumeration representing the types of tasks that can be handled by the Parser.
     */
    public enum TaskType {
        TODO,EVENT,DEADLINE
    }
    public Parser(ListManager chickenManager, FileManager chickenFileManager) {
        this.chickenManager = chickenManager;
        this.chickenFileManager = chickenFileManager;
    }

    /**
     * Parses the user's command and calls the appropriate method to handle it.
     *
     * @param command The user's input command.
     */
    public String parseCommand(String command) {
        assert command != null && !command.trim().isEmpty() : "Command cannot be null or empty";

        String commandLowerCase = command.toLowerCase();
        String[] parts = command.split(" ");
        String firstWord = parts[0].toLowerCase();

        switch (firstWord) {
        case "bye":
        return ("See you again");

        case "list":
        return (chickenManager.listItems(""));

        case "mark", "unmark":
        return handleMarkUnmark(command, firstWord);

        case "deadline":
        return handleDeadline(command);

        case "todo":
        return handleTodo(command);

        case "event":
        return handleEvent(command);

        case "delete":
        return handleDelete(command);

        case "find":
        return handleFind(command);

        default:
        return ("I don't understand your command!");

        }
    }

    /**
     * Handles the "mark" and "unmark" commands to mark or unmark tasks as done.
     *
     * @param command The full input string from the user.
     * @param action  The action to perform ("mark" or "unmark").
     */
    private String handleMarkUnmark(String command, String action) {
        assert action.equals("mark") || action.equals("unmark") : "Action must be 'mark' or 'unmark'";

        int taskNumber = parseTaskNumber(command);
        if (taskNumber == -1) return "Invalid command format. Please use the correct format.";
        // parseTaskNumber returns "invalid..." if invalid command given

        boolean isMarking = action.equals("mark");
        chickenManager.setDone(isMarking, taskNumber);
        String statusMessage = isMarking ? "Nice! I've marked this task as done:\n" : "OK, I've marked this task as not done yet:\n";
        return (statusMessage + chickenManager.getItem(taskNumber));
    }

    /**
     * Parses the task number from the command string.
     *
     * @param command The user's input command.
     * @return The parsed task number, or -1 if parsing fails.
     */
    private int parseTaskNumber(String command) {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid command format. Please use the correct format.");
            return -1;  // Return -1 to indicate failure
        }

        try {
            int number = Integer.parseInt(parts[1]);
            if (number <= 0) {
                System.out.println("Please provide a positive number.");
                return -1;
            }
            return number;
        } catch (NumberFormatException e) {
            System.out.println("You didn't type a valid number.");
            return -1;
        }
    }

    /**
     * Handles the "deadline" command to add a deadline task.
     *
     * @param command The user's input command starting with "deadline".
     * @return A message indicating the result of the deadline task creation.
     */
    private String handleDeadline(String command) {
        assert command.startsWith("deadline") : "Command should start with 'deadline'";

        String[] part = command.replaceFirst("deadline ", "").split("/by", 2);
        String description = part[0].trim();
        String by = part.length > 1 ? part[1].trim() : "";

        TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.DEADLINE);
        try {
            String task = chickenManager.createItem(taskBuilder.by(by)).toString();
            chickenFileManager.writeFile(task);
            return task + " added";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "todo" command to add a to-do task.
     *
     * @param command The user's input command starting with "todo".
     * @return A message indicating the result of the to-do task creation.
     */
    private String handleTodo(String command) {
        assert command.startsWith("todo") : "Command should start with 'todo'";

        String description = command.substring("todo".length()).trim();
        TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.TODO);

        try {
            String task = chickenManager.createItem(taskBuilder).toString();
            chickenFileManager.writeFile(task);
            return task + " added";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }

    /**
     * Handles the "event" command to add an event task.
     *
     * @param command The user's input command starting with "event".
     * @return A message indicating the result of the event task creation.
     */
    private String handleEvent(String command) {
        assert command.startsWith("event") : "Command should start with 'event'";

        // Remove the word 'event' and split by '/from'
        String[] part = command.replaceFirst("event ", "").split("/from", 2);
        String description = part[0].trim();
        String remaining = part.length > 1 ? part[1].trim() : "";
        // Split the remaining part by '/to'
        String[] dateParts = remaining.split("/to", 2);
        String from = dateParts[0].trim();
        String to = dateParts.length > 1 ? dateParts[1].trim() : "";

        TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.EVENT);
        try {
            String task = chickenManager.createItem(taskBuilder.from(from).to(to)).toString();
            chickenFileManager.writeFile(task);
            return task + " added";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "delete" command to remove a task by its index.
     *
     * @param command The user's input command starting with "delete".
     * @return A message indicating the result of the task deletion.
     */
    private String handleDelete(String command) {
        assert command.startsWith("delete") : "Command should start with 'delete'";

        String index = command.substring("delete".length()).trim();
        try {
            int indexNumber = Integer.parseInt(index);
            String tempTask = chickenManager.getItem(indexNumber);
            chickenManager.delete(indexNumber);
            return ("Task deleted: " + tempTask + ". You have " + chickenManager.getItemSize() + " items left.");
        } catch (NumberFormatException e) {
            return ("Invalid task number.");
        }
    }

    /**
     * Handles the "find" command to search for tasks by description.
     *
     * @param command The user's input command starting with "find".
     * @return A string listing the tasks that match the search criteria.
     */
    private String handleFind(String command) {
        String itemName = command.replace("find ", "");
        return (chickenManager.listItems(itemName)) + "found";
    }

}
