import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Zaibot {

    private static final Storage storage = new Storage();

    /**
     * Prints a message, with the horizontal line above and below it
     * @param message   The message to be printed
     */
    public static void printMessage(String message) {
        String line = "-----------------------------------------------------------\n";
        System.out.println(line + message + line);
    }

    /**
     * This takes a command split by spaces, and processes them into separate commands
     * The Hashmap returned contains key-value pairs of {parameter name: argument value}
     *
     * @param command A command
     * @return A Hashmap<String, String> where the key is the parameter name, and the value is the argument.
     */
    public static HashMap<String, String> processAddOptions(String command) throws ZaibotException {
        HashMap<String, String> arguments = new HashMap<>();

        if (command.indexOf(' ') == -1) {
            throw new ZaibotException("Name cannot be empty.");
        }

        String optionString = command.substring(command.indexOf(' '));
        String[] options = optionString.split("/");

        if (optionString.isEmpty() || options[0].isEmpty()) {
            throw new ZaibotException("Name cannot be empty.");
        }
        arguments.put("name", options[0].trim());

        for (int i = 1; i < options.length; i++) {
            String option = options[i];
            String optionName = option.substring(0, option.indexOf(' ')).trim();
            String optionValue = option.substring(option.indexOf(' ')).trim();
            if (optionValue.isEmpty()) {
                throw new ZaibotException(String.format("Option %s cannot be empty.", optionName));
            }
            arguments.put(optionName, optionValue);
        }

        return arguments;
    }

    /**
     * Processes a task addition given the command and the task name.
     * @param command The full length command
     * @param taskName The name of the task
     * @throws ZaibotException throws errors if command is not following the syntax
     */
    public static void addTask(String command, String taskName) throws ZaibotException{
        String taskAddMessage = "Got it. I've added this task\n";
        String taskTotalMessage = "Now you have %d tasks in the list.\n";

        HashMap<String, String> allOptions = processAddOptions(command);
        Task task;

        String name = allOptions.get("name");

        switch (taskName) {
            case "todo":
                task = new ToDoTask(name);
                break;
            case "deadline":
                if (!allOptions.containsKey("by")) {
                    throw new ZaibotException("Deadline must have option /by.");
                }
                String by = allOptions.get("by");
                task = new DeadlineTask(name, by);
                break;
            case "event":
                if (!allOptions.containsKey("from") || !allOptions.containsKey("to")) {
                    throw new ZaibotException("Event must have option /from and /to.");
                }
                String from = allOptions.get("from");
                String to = allOptions.get("to");
                task = new EventTask(name, from, to);
                break;
            default:
                throw new ZaibotException("Invalid task");
        }
        Storage.addTask(task);
        printMessage(taskAddMessage + task.toString() + "\n" +
                String.format(taskTotalMessage, Storage.getNumberOfTasks()));
    }

    /**
     * Processes a command, based on the input.
     * @param command The command input
     */
    public static void processCommand(String command) throws ZaibotException {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        String markTaskMessage = "Nice! I've marked this task as done:\n";
        String unmarkTaskMessage = "OK, I've marked this task as not done yet:\n";
        String taskListMessage = "Here are the tasks in your list:\n";
        String deleteTaskMessage = "Noted. I've removed this task.\n";

        String[] options = command.split("\\s+");

        Task current;

        switch (options[0]) {
            case "bye":
                printMessage(goodbyeMessage);
                break;
            case "list":
                printMessage(taskListMessage + tasksListToString());
                break;
            case "mark":
                current = Storage.getTask(Integer.parseInt(options[1]));
                current.setCompletionStatus(true);
                printMessage(markTaskMessage + current.toString() + "\n");
                break;
            case "unmark":
                current = Storage.getTask(Integer.parseInt(options[1]));
                current.setCompletionStatus(false);
                printMessage(unmarkTaskMessage + current.toString() + "\n");
                break;
            case "delete":
                current = Storage.removeTask(Integer.parseInt(options[1]));
                printMessage(String.format("%s %s\nNow you have %d tasks in the list.\n",
                        deleteTaskMessage, current.toString(), Storage.getNumberOfTasks())
                );
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(command, options[0]);
                break;
            default:
                throw new ZaibotException("Invalid command.\n");
        }
    }

    /**
     * Takes in the list of tasks, and converts it into a string.
     * @return A string of all the tasks, enumerated.
     */
    public static String tasksListToString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= Storage.getNumberOfTasks(); i++) {
            Task task = Storage.getTask(i);
            result.append(i).append(". ").append(task.toString()).append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String greetingMessage = "Hello! I'm zAIbot.\nWhat can I do for you?\n";
        printMessage(greetingMessage);

        String currentCommand = "";
        Scanner input = new Scanner(System.in);

        while (!currentCommand.equals("bye") && input.hasNextLine()) {
            currentCommand = input.nextLine();
            try {
                processCommand(currentCommand);
                storage.saveToFile();
            }
            catch (ZaibotException exception) {
                printMessage(exception.getMessage());
            }
        }

    }
}
