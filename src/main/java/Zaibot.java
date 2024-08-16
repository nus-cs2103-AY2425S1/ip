import java.util.Scanner;
import java.util.ArrayList;

public class Zaibot {

    private static final ArrayList<Task> tasks = new ArrayList<>();

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
     * The array returned is a String array of size 3, separating the string
     * If the parameter is not applicable, it is left empty.
     *
     * @param options A command, split by space into an array
     * @return A string array containing 3 strings, the name, to and by
     */
    public static String[] processCommand(String[] options){
        String[] result = new String[]{"", "", ""};

        int current = 0;

        for (int i = 1; i < options.length; i++) {
            String option = options[i];
            if (option.charAt(0) == '/'){
                current++;
                continue;
            }

            result[current] += option + " ";
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        return result;
    }

    public static void addTask(Task task){
        String taskAddMessage = "Got it. I've added this task\n";
        String taskTotalMessage = "Now you have %d tasks in the list.\n";
        tasks.add(task);
        printMessage(taskAddMessage + task.toString() + "\n" +
                String.format(taskTotalMessage, tasks.size()));
    }

    /**
     * Processes a command, based on the input.
     * @param command The command input
     */
    public static void processCommand(String command) {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        String markTaskMessage = "Nice! I've marked this task as done:\n";
        String unmarkTaskMessage = "OK, I've marked this task as not done yet:\n";
        String taskListMessage = "Here are the tasks in your list:\n";


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
                current = tasks.get(Integer.parseInt(options[1]) - 1);
                current.setCompletionStatus(true);
                printMessage(markTaskMessage + current.toString() + "\n");
                break;
            case "unmark":
                current = tasks.get(Integer.parseInt(options[1]) - 1);
                current.setCompletionStatus(false);
                printMessage(unmarkTaskMessage + current.toString() + "\n");
                break;
            case "todo":
                options = processCommand(options);
                current = new ToDoTask(options[0]);
                addTask(current);
                break;
            case "deadline":
                options = processCommand(options);
                current = new DeadlineTask(options[0], options[1]);
                addTask(current);
                break;
            case "event":
                options = processCommand(options);
                current = new EventTask(options[0], options[1], options[2]);
                addTask(current);
                break;
            default:
                printMessage("Invalid event type\n");
        }
    }

    /**
     * Takes in the list of tasks, and converts it into a string.
     * @return A string of all the tasks, enumerated.
     */
    public static String tasksListToString() {
        StringBuilder result = new StringBuilder();
        int current = 0;
        for (Task task : tasks) {
            current++;
            result.append(current).append(". ").append(task.toString()).append("\n");
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
            processCommand(currentCommand);
        }

    }
}
