package Bot;
import TaskType.TaskBuilder;

/**
 * The Parser class is responsible for interpreting user commands and invoking
 * the appropriate methods to handle tasks such as creating, marking, unmarking,
 * deleting, and listing tasks. It interacts with the ListManager to manage the tasks
 * and FileManager to handle file operations.
 */
public class Parser {
    private ListManager dukeManager;
    private FileManager dukeFileManager;

    /**
     * Enumeration representing the types of tasks that can be handled by the Parser.
     */
    public enum TaskType {
        TODO,EVENT,DEADLINE
    }
    public Parser(ListManager dukeManager, FileManager dukeFileManager) {
        this.dukeManager = dukeManager;
        this.dukeFileManager = dukeFileManager;
    }

    /**
     * Parses the user's command and calls the appropriate method to handle it.
     *
     * @param command The user's input command.
     */
    public void parseCommand(String command) {
        assert command != null && !command.trim().isEmpty() : "Command cannot be null or empty";

        String commandLowerCase = command.toLowerCase();
        String[] parts = command.split(" ");
        String firstWord = parts[0].toLowerCase();

        switch (firstWord) {
            case "bye":
            System.out.println("See you again");
            break;

            case "list":
            System.out.println(dukeManager.listItems(""));
            break;

            case "mark", "unmark":
            handleMarkUnmark(command, firstWord);
            break;

            case "deadline":
            handleDeadline(command);
            break;

            case "todo":
            handleTodo(command);
            break;

            case "event":
            handleEvent(command);
            break;

            case "delete":
            handleDelete(command);
            break;

            case "find":
            handleFind(command);
            break;

            default:
            System.out.println("I don't understand your command!");
            break;

        }
    }

    /**
     * Handles the "mark" and "unmark" commands to mark or unmark tasks as done.
     *
     * @param command The full input string from the user.
     * @param action  The action to perform ("mark" or "unmark").
     */
    private void handleMarkUnmark(String command, String action) {
        assert action.equals("mark") || action.equals("unmark") : "Action must be 'mark' or 'unmark'";

        int taskNumber = parseTaskNumber(command);
        if (taskNumber == -1) return;  // parseTaskNumber returns -1 if invalid command given

        boolean isMarking = action.equals("mark");
        dukeManager.setDone(isMarking, taskNumber);
        String statusMessage = isMarking ? "Nice! I've marked this task as done:\n" : "OK, I've marked this task as not done yet:\n";
        System.out.println(statusMessage + dukeManager.getItem(taskNumber));
    }

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


    private void handleDeadline(String command) {
        assert command.startsWith("deadline") : "Command should start with 'deadline'";

        String[] part = command.replaceFirst("deadline ", "").split("/by", 2);
        String description = part[0].trim();
        String by = part.length > 1 ? part[1].trim() : "";

        TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.DEADLINE);
        String task = dukeManager.createItem(taskBuilder.by(by)).toString();
        dukeFileManager.writeFile(task);
    }

    private void handleTodo(String command) {
        assert command.startsWith("todo") : "Command should start with 'todo'";

        String description = command.substring("todo".length()).trim();
        TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.TODO);

        String task = dukeManager.createItem(taskBuilder).toString();
        dukeFileManager.writeFile(task);
    }

    private void handleEvent(String command) {
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
        String task = dukeManager.createItem(taskBuilder.from(from).to(to)).toString();
        dukeFileManager.writeFile(task);
    }

    private void handleDelete(String command) {
        assert command.startsWith("delete") : "Command should start with 'delete'";

        String index = command.substring("delete".length()).trim();
        try {
            int indexNumber = Integer.parseInt(index);
            String tempTask = dukeManager.getItem(indexNumber);
            dukeManager.delete(indexNumber);
            System.out.println("Task deleted: " + tempTask + ". You have " + dukeManager.getItemSize() + " items left.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }

    private void handleFind(String command) {
        String itemName = command.replace("find ", "");
        System.out.println(dukeManager.listItems(itemName));
    }

}
