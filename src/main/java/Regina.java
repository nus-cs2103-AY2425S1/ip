import java.util.Scanner;

/**
 * The Regina class represents a chatbot designed to help users track their tasks and activities.
 * It provides functionalities to add, mark, unmark, delete, and list tasks.
 */
public class Regina {
    private final static String NAME = "Regina";
    private final static String INDENT = "    ";
    private final static String LINE = INDENT + "********************************************************************";
    private final TaskList listOfTasks;
    private final Marker marker;
    private final Scanner scanner = new Scanner(System.in);

    // Enum to represent task types
    public enum TaskType {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private final String type;

        TaskType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    // types of tasks
    private final String TODO_TYPE = TaskType.TODO.type;
    private final String DEADLINE_TYPE = TaskType.DEADLINE.type;
    private final String EVENT_TYPE = TaskType.EVENT.type;

    /**
     * Constructs a Regina instance containing an empty task list and initializes the marker.
     */
    public Regina() {
        listOfTasks = new TaskList();
        marker = new Marker(listOfTasks);
    }

    /**
     * Greets the user and provides instructions on how to interact with the chatbot.
     */
    public void greet() {
        System.out.printf(LINE + "\n" + INDENT + "Hey there! I'm %s \n" +
                INDENT + "I am a chatbot designed to help you track your activities.\n" +
                INDENT + "You can add tasks using the following formats:\n" +
                INDENT + "1. To add a To-Do task: %s <task_description>\n" +
                INDENT + "   Example: %s Finish homework\n" +
                INDENT + "2. To add a Deadline task: %s <task_description> /by <deadline>\n" +
                INDENT + "   Example: %s Submit report /by 2023-12-01\n" +
                INDENT + "3. To add an Event task: %s <task_description> /from <start_time> /to <end_time>\n" +
                INDENT + "   Example: %s Team meeting /from Mon 2pm /to 4pm\n" +
                INDENT + "You can also:\n" +
                INDENT + "1. Mark a task as done: mark <task_number>\n" +
                INDENT + "   Example: mark 1\n" +
                INDENT + "2. Unmark a task: unmark <task_number>\n" +
                INDENT + "   Example: unmark 1\n" +
                INDENT + "3. Delete a task: delete <task_number>\n" +
                INDENT + "   Example: delete 1\n" +
                INDENT + "4. List tasks: type 'list' to see all your tasks\n" +
                INDENT + "5. For help: type 'help'\n" +
                INDENT + "What can I do for you?\n" + LINE + "\n", NAME, TODO_TYPE, TODO_TYPE, DEADLINE_TYPE, DEADLINE_TYPE, EVENT_TYPE, EVENT_TYPE);
    }

    /**
     * Provides help details about the commands the user can use.
     */
    public void help() {
        System.out.printf(LINE + "\n" + INDENT + "Here are the commands you can use: \n" +
                INDENT + "1. To add a To-Do task: %s <task_description>\n" +
                INDENT + "   Example: %s Finish homework\n" +
                INDENT + "2. To add a Deadline task: %s <task_description> /by <deadline>\n" +
                INDENT + "   Example: %s Submit report /by 2023-12-01\n" +
                INDENT + "3. To add an Event task: %s <task_description> /from <start_time> /to <end_time>\n" +
                INDENT + "   Example: %s Team meeting /from Mon 2pm /to 4pm\n" +
                INDENT + "4. To mark a task as done: mark <task_number>\n" +
                INDENT + "   Example: mark 1\n" +
                INDENT + "5. To unmark a task: unmark <task_number>\n" +
                INDENT + "   Example: unmark 1\n" +
                INDENT + "6. To delete a task: delete <task_number>\n" +
                INDENT + "   Example: delete 1\n" +
                INDENT + "7. To view your tasks: list\n" +
                INDENT + "8. For help: help\n" +
                LINE + "\n", TODO_TYPE, TODO_TYPE, DEADLINE_TYPE, DEADLINE_TYPE, EVENT_TYPE, EVENT_TYPE);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The input string entered by the user.
     */
    public String readInput() {
        return this.scanner.nextLine();
    }

    /**
     * Validates whether a given task type is recognized.
     *
     * @param type The task type to check.
     * @return True if the task type is valid; false otherwise.
     */
    private boolean isValidTaskType(String type) {
        return type.equals(TODO_TYPE) || type.equals(DEADLINE_TYPE) || type.equals(EVENT_TYPE);
    }

    /**
     * Validates if the command for marking or unmarking contains a number.
     *
     * @param parts The parts of the input command split into an array.
     * @throws ReginaException If the command does not properly contain a task number.
     */
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

    /**
     * Adds a new task based on the input command.
     *
     * @param input The user input string containing the task details.
     * @throws ReginaException If the input format is incorrect or invalid.
     */
    public void add(String input) throws ReginaException {;
        String[] parts = input.split(" "); // Split input by spaces
        String taskType = parts[0];
        if (parts.length < 2 && isValidTaskType(taskType)) {
            String message = String.format("OOPS!!! Add your %s task description lah!", taskType);
            throw new ReginaException(message);
        }
        Task task = null;
        switch (taskType) {
            case "todo":
                String todoDescription = input.substring(5).trim();
                task = new ToDosTask(todoDescription);
                break;
            case "deadline":
                String[] deadlineParts = input.substring(9).trim().split(" /by ");
                // check if deadline was added for this task
                if (deadlineParts.length < 2) {
                    throw new ReginaException("So....when's the deadline for this task?");
                }
                String deadlineDescription = deadlineParts[0];
                String deadline = deadlineParts[1];
                task = new DeadlinesTask(deadlineDescription, deadline);
                break;
            case "event":
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

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws ReginaException If the index is out of bounds or if there are no tasks to delete.
     */
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
        System.out.printf("%s\n%sWah shiok!\n%sCan forget about %s liao!\n%sList now has %d task%s!\n%s\n",
                LINE, INDENT, INDENT, task.toString(), INDENT, taskCount, taskCount > 1 ? "s" : "", LINE);
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @throws ReginaException If there are no tasks to display.
     */
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

    /**
     * Marks a task based on the given index.
     *
     * @param index The index of the task to mark as done.
     * @throws ReginaException If the index is out of bounds.
     */
    public void mark(int index) throws ReginaException {
        this.marker.mark(index);
        Task task = this.listOfTasks.get(index);
        System.out.printf("%s\n%sYAY! This task finish liao!:\n%s  %s\n%s\n",
                LINE, INDENT, INDENT, task.toString(), LINE);
    }

    /**
     * Unmarks a task based on the given index.
     *
     * @param index The index of the task to unmark.
     * @throws ReginaException If the index is out of bounds.
     */
    public void unmark(int index) throws ReginaException {
        this.marker.unmark(index);
        Task task = this.listOfTasks.get(index);
        System.out.printf("%s\n%sHais! Need to do this task again!:\n%s  %s\n%s\n",
                LINE, INDENT, INDENT, task.toString(), LINE);
    }

    /**
     * Exits the program and closes the scanner.
     */
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
                        REGINA.mark(index); // Mark the task
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
