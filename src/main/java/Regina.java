import java.util.ArrayList;
import java.util.Scanner;

public class Regina {
    private final static String NAME = "Regina";
    private final static String INDENT = "    ";
    private final static String LINE = INDENT + "-------------------------------";
    private final ArrayList<Task> listOfTasks;
    private final Scanner scanner = new Scanner(System.in);
    // types of tasks
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";

    public Regina() {
        listOfTasks = new ArrayList<>();
    }

    public void greet() {
        System.out.printf(LINE + "\n" + INDENT + "Hey there! I'm %s \n" +
                INDENT + "I am a chatbot designed to help you track your activities.\n" +
                INDENT + "You can add tasks using the following formats:\n" +
                INDENT + "1. To add a To-Do task: %s <task_description>\n" +
                INDENT + "   Example: %s Finish homework\n" +
                INDENT + "2. To add a Deadline task: %s <task_description> /by <deadline>\n" +
                INDENT + "   Example: %s Submit report /by Sunday\n" +
                INDENT + "3. To add an Event task: %s <task_description> /from <start_time> /to <end_time>\n" +
                INDENT + "   Example: %s Team meeting /from Mon 2pm /to 4pm\n" +
                INDENT + "In case you forget, type 'help' to be reminded of the formats again!\n" +
                INDENT + "So how can I help you?\n" + LINE + "\n", NAME, TODO, TODO, DEADLINE, DEADLINE, EVENT, EVENT);
    }

    public void help() {
        System.out.printf(LINE + "\n" + INDENT + "Here are the formats: \n" +
                INDENT + "1. To add a To-Do task: %s <task_description>\n" +
                INDENT + "   Example: %s Finish homework\n" +
                INDENT + "2. To add a Deadline task: %s <task_description> /by <deadline>\n" +
                INDENT + "   Example: %s Submit report /by Sunday\n" +
                INDENT + "3. To add an Event task: %s <task_description> /from <start_time> /to <end_time>\n" +
                INDENT + "   Example: %s Team meeting /from Mon 2pm /to 4pm\n" +
                LINE + "\n", TODO, TODO, DEADLINE, DEADLINE, EVENT, EVENT);
    }

    public String readInput() {
        return this.scanner.nextLine();
    }

    private boolean isValidTaskType(String type) {
        return type.equals(TODO) || type.equals(DEADLINE) || type.equals(EVENT);
    }

    public boolean haveNumber(String[] parts) throws ReginaException {
        if (parts.length < 2) {
            throw new ReginaException("Which task you referring to lah!");
        }
        if (parts.length > 2) {
            throw new ReginaException("Follow the proper format please!\nType 'help' for reference.");
        }
        try {
            Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void add(String input) throws ReginaException {;
        String[] parts = input.split(" "); // Split input by spaces
        String taskType = parts[0];
        if (parts.length < 2 && isValidTaskType(taskType)) {
            String message = String.format("OOPS!!! Add your %s task description lah!", taskType);
            throw new ReginaException(message);
        }
        Task task = null;
        switch (taskType) {
            case TODO:
                String todoDescription = input.substring(5).trim();
                task = new ToDosTask(todoDescription);
                break;
            case DEADLINE:
                String[] deadlineParts = input.substring(9).trim().split(" /by ");
                // check if deadline was added for this task
                if (deadlineParts.length < 2) {
                    throw new ReginaException("So....when's the deadline for this task?");
                }
                String deadlineDescription = deadlineParts[0];
                String deadline = deadlineParts[1];
                task = new DeadlinesTask(deadlineDescription, deadline);
                break;
            case EVENT:
                String[] eventParts = input.substring(6).trim().split(" /");
                int length = eventParts.length;
                // check if there is the expected number of sub-parts
                if (length != 3) {
                    throw new ReginaException("You need to add BOTH the start-time AND the end-time!\n" +
                            INDENT + "Type 'help' for reference.");
                }
                // if correct number of sub-parts then check if format is correct
                if (!(eventParts[1].contains("from") && eventParts[2].contains("to"))) {
                    throw new ReginaException("OI! Use the correct format lah!\n" +
                            INDENT +"Type 'help' for reference.");
                }
                String eventDescription = eventParts[0];
                if (!eventParts[1].contains(" ")) {
                    throw new ReginaException("NEITHER the start-time OR end-time can be left blank!\n" +
                            INDENT + "Type 'help' for reference.");
                }
                String startTime = eventParts[1].substring(5).trim(); // take the substring after the word "from"
                String endTime = eventParts[2].substring(3).trim(); // take the substring after the word "to"
                task = new EventsTask(eventDescription, startTime, endTime);
                break;
            default:
                throw new ReginaException("Unknown task type. Use: todo, deadline, or event.");
        }
        listOfTasks.add(task);
        int noOfTasks = listOfTasks.size();
        System.out.println(LINE + "\n" + INDENT + "Got it. I've added this task: \n  "
                + INDENT
                + task.toString()
                + String.format("\n%sNow you have %d task%s in the list.\n%sJiayous!\n",
                    INDENT,
                    noOfTasks,
                    noOfTasks > 1 ? "s" : "",
                    INDENT) + LINE);
    }

    public void delete(int index) throws ReginaException {
        if (listOfTasks.isEmpty()) {
            throw new ReginaException("No more tasks to delete alr lah!");
        }
        if (index < 0) {
            throw new ReginaException("Choose index greater than 1 please!");
        }
        int taskCount = listOfTasks.size();
        if (index >= taskCount) {
            String message = String.format("You cannot count ah! There %s only %d task%s!",
                    taskCount > 1 ? "are" : "is",
                    taskCount,
                    taskCount > 1 ? "s" : "");
            throw new ReginaException(message);
        }
        Task task = listOfTasks.get(index);
        listOfTasks.remove(index);
        taskCount = listOfTasks.size(); // update the number of tasks
        System.out.printf("%s\n%sWah shiok!\n%sCan forget about %s liao!\n%sList now has %d task%s!\n%s",
                LINE, INDENT, INDENT, task.toString(), INDENT, taskCount, taskCount > 1 ? "s" : "", LINE);
    }

    public void list() throws ReginaException {
        int length = listOfTasks.size();
        if (length == 0) {
            throw new ReginaException("HEHE no tasks for now!");
        }
        StringBuilder inputList = new StringBuilder();
        for (int i = 0; i < length; i++) {
            inputList.append(INDENT)
                    .append(i + 1)
                    .append(".")
                    .append(listOfTasks.get(i).toString())
                    .append("\n"); // get task
        }
        System.out.println(LINE + "\n" + inputList + LINE);
    }

    public void mark(int index) throws ReginaException {
        int taskCount = listOfTasks.size();
        if (index >= taskCount) {
            String message = String.format("You cannot count ah! There %s only %d task%s!",
                    taskCount > 1 ? "are" : "is",
                    taskCount,
                    taskCount > 1 ? "s" : "");
            throw new ReginaException(message);
        }
        if (index < 0) {
            throw new ReginaException("Oops! Please choose an index greater than 0.");
        }
        Task task = listOfTasks.get(index);
        task.checkTask();
        System.out.printf("%s\n%sYAY! This task finish liao!:\n%s  %s\n%s\n",
                LINE, INDENT, INDENT, task.toString(), LINE);
    }

    public void unmark(int index) throws ReginaException {
        int taskCount = listOfTasks.size();
        if (index >= taskCount) {
            String message = String.format("You cannot count ah! There %s only %d task%s",
                    taskCount > 1 ? "are" : "is",
                    taskCount,
                    taskCount > 1 ? "s" : "");
            throw new ReginaException(message);
        }
        if (index < 0) {
            throw new ReginaException("Oops! Please choose an index greater than 0.");
        }
        Task task = listOfTasks.get(index);
        task.uncheckTask();
        System.out.printf("%s\n%sHais! Need to do this task again!:\n%s  %s\n%s\n",
                LINE, INDENT, INDENT, task.toString(), LINE);
    }

    public void exit() {
        System.out.println(LINE + "\n" + INDENT +
                "Bye. Hope to see you again soon!\n" + LINE);
        this.scanner.close();
    }

    public static void main(String[] args) {
        final Regina REGINA = new Regina(); // create instance of Regina chatbot
        REGINA.greet(); // greet
        String userInput;

        while (true) {
            try {
                userInput = REGINA.readInput();   // Read user input
                if (userInput.equals("bye")) {
                    break; // proceed to exit chatbot
                }
                if (userInput.equals("help")) {
                    REGINA.help();
                } else if (userInput.equals("list")) {
                    REGINA.list(); // Print out the list
                } else if (userInput.startsWith("mark")) {
                    String[] parts = userInput.split(" "); // Split input by spaces
                    if (REGINA.haveNumber(parts)) { // Ensure there's an index
                        int index = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
                        REGINA.mark(index); // Unmark the task
                    }
                } else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split(" "); // Split input by spaces
                    if (REGINA.haveNumber(parts)) { // Ensure there's an index
                        int index = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
                        REGINA.unmark(index); // Unmark the task
                    }
                } else if (userInput.startsWith("delete")) {
                    String[] parts = userInput.split(" "); // Split input by spaces
                    if (REGINA.haveNumber(parts)) { // Ensure there's an index
                        int index = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
                        REGINA.delete(index); // Unmark the task
                    }
                } else {
                    REGINA.add(userInput); // Add input to list
                }
            } catch (ReginaException e) {
                System.out.printf("%s\n%s%s\n%s\n", LINE, INDENT, e.getMessage(), LINE);
            }
        }
        // Exit
        REGINA.exit();
    }
}
