package Bot;
import TaskType.TaskBuilder;

public class Parser {
    private ListManager dukeManager;
    private FileManager dukeFileManager;
    public enum TaskType {
        TODO,EVENT,DEADLINE
    }
    public Parser(ListManager dukeManager, FileManager dukeFileManager) {
        this.dukeManager = dukeManager;
        this.dukeFileManager = dukeFileManager;
    }

    public void parseCommand(String command) {
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

    private void handleMarkUnmark(String command, String action) {
        String[] parts = command.split(" ");
        if (parts.length == 2) {
            String numberStr = parts[1];
            try {
                int number = Integer.parseInt(numberStr);
                if (number > 0) {
                    if (action.equals("mark")) {
                        dukeManager.setDone(true, number);
                        System.out.println("Nice! I've marked this task as done:\n" + dukeManager.getItem(number));
                    } else {
                        dukeManager.setDone(false, number);
                        System.out.println("OK, I've marked this task as not done yet:\n" + dukeManager.getItem(number));
                    }
                } else {
                    System.out.println("Please provide a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You didn't type a valid number.");
            }
        } else {
            System.out.println("Invalid command format.");
        }
    }

    private void handleDeadline(String command) {
        String[] part = command.replaceFirst("deadline ", "").split("/by", 2);
        String description = part[0].trim();
        String by = part.length > 1 ? part[1].trim() : "";

        TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.DEADLINE);
        String task = dukeManager.createItem(taskBuilder.by(by)).toString();
        dukeFileManager.writeFile(task);
    }

    private void handleTodo(String command) {
        String description = command.substring("todo".length()).trim();
        TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.TODO);
        String task = dukeManager.createItem(taskBuilder).toString();
        dukeFileManager.writeFile(task);
    }

    private void handleEvent(String command) {
        // Remove the word 'event' and split by '/from'
        String[] part = command.replaceFirst("event ", "").split("/from", 2);
        // The description part
        String description = part[0].trim();
        // The remaining part
        String remaining = part.length > 1 ? part[1].trim() : "";
        // Split the remaining part by '/to'
        String[] dateParts = remaining.split("/to", 2);
        // The 'from' part
        String from = dateParts[0].trim();
        // The 'to' part
        String to = dateParts.length > 1 ? dateParts[1].trim() : "";

        TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.EVENT);
        String task = dukeManager.createItem(taskBuilder.from(from).to(to)).toString();
        dukeFileManager.writeFile(task);
    }

    private void handleDelete(String command) {
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
