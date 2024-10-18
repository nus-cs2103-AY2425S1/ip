package opus;

/**
 * The Opus class is the main entry point of the Duke task manager application.
 * It initializes the necessary components (UI, storage, task list) and runs
 * the main loop to interact with the user, handle commands, and manage tasks.
 */
public class Opus {

    private Storage storage;
    private TaskList taskList;
    private boolean isExit = false;

    /**
     * Initializes the Opus application with the specified file path for storage.
     * Loads tasks from storage and sets up UI and task list.
     *
     * @param filePath Path to the file where tasks are stored.
     */
    public Opus(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Handles the bye command by saving tasks to storage and setting the exit flag.
     *
     * @return The response message indicating the application will exit.
     * @throws OpusException If an error occurs while saving tasks.
     */
    private String handleByeCommand() throws OpusException {
        storage.save(taskList.getTasks());
        isExit = true; // Set the exit flag
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns whether the application should exit.
     *
     * @return True if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Handles the list command by displaying all tasks in the task list.
     *
     * @return The response message containing all tasks in the list.
     */
    private String handleListCommand() {
        String response = "";
        for (Task task : taskList.getTasks()) {
            response += task.toString() + "\n";
        }
        return response;
    }
    /**
     * Handles the mark command by marking a task as done.
     *
     * @param words The command split into words.
     * @return The response message indicating the task has been marked as done.
     * @throws OpusException If the task number is not specified or is invalid.
     */
    private String handleMarkCommand(String[] words) throws OpusException {
        if (words.length < 2) {
            throw new OpusException("Please specify the task number to mark.");
        }
        int i = Integer.parseInt(words[1]) - 1;
        assert i >= 0 && i < taskList.getSize() : "Index out of bounds: " + i;
        taskList.getTask(i).markAsDone();
        return "Nice! I've marked this task as done:\n" + taskList.getTask(i).toString();
    }
    /**
     * Handles the delete command by removing a task from the list.
     *
     * @param words The command split into words.
     * @return The response message indicating the task has been deleted.
     * @throws OpusException If the task number is not specified or is invalid.
     */
    private String handleDeleteCommand(String[] words) throws OpusException {
        String response = "";
        if (words.length < 2) {
            throw new OpusException("Please specify the task number to delete.");
        }
        int i = Integer.parseInt(words[1]) - 1;
        assert i >= 0 && i < taskList.getSize() : "Index out of bounds: " + i;
        response = "Noted. I've removed this task:\n" + taskList.getTask(i).toString() + "\n";
        taskList.removeTask(i);
        response += "Now you have " + taskList.getSize() + " tasks in the list.";
        return response;
    }
    /**
     * Handles the help command by displaying a list of available commands.
     *
     * @return The response message containing the list of commands.
     */
    private String handleHelpCommand() {
        String response = "Here are the commands you can use:\n";
        response += "1. list - List all tasks\n";
        response += "2. mark <index> - Mark a task as done\n";
        response += "3. delete <index> - Delete a task\n";
        response += "4. deadline <task> /by <end-date> - Add a deadline by the end date\n";
        response += "5. event <task> /from <start-date> /to <end-date>";
        response += " - Add an event with start and end dates\n";
        response += "6. todotask <> - Add a Todo\n";
        response += "7. bye - Exit the application";
        return response;
    }


    /**
     * Main method to launch the Opus application.
     * @param args Command-line arguments, not used.
     */
    public static void main(String[] args) {
        new Opus("data/tasks.txt");
    }

    public String getResponse(String input) {
        String[] words = Parser.parse(input);
        String response = "";
        try {
            assert words.length > 0 : "Command cannot be empty";
            if (words[0].equals("bye")) {
                response = handleByeCommand();
            } else if (words[0].equals("list")) {
                response = handleListCommand();
            } else if (words[0].equals("mark")) {
                response = handleMarkCommand(words);
            } else if (words[0].equals("delete")) {
                response = handleDeleteCommand(words);
            } else if (words[0].equals("help")) {
                response = handleHelpCommand();
            } else {
                if (words[0].equals("todo")) {
                    assert words.length > 1 : "The description of a todo cannot be empty";
                    if (words.length <= 1) {
                        throw new OpusEmptyDescriptionException("The description of a todo cannot be empty.");
                    }
                    Task todo = new ToDo(words[1]);
                    taskList.addTask(todo);
                } else if (words[0].equals("deadline")) {
                    assert words.length > 1 : "Deadline details are missing";
                    String[] parts = Parser.parseDeadlineDetails(words[1]);
                    Task deadline = new Deadline(parts[0], parts[1]);
                    taskList.addTask(deadline);
                } else if (words[0].equals("event")) {
                    assert words.length > 1 : "Event details are missing";
                    String[] parts = Parser.parseEventDetails(words[1]);
                    Task event = new Event(parts[0], parts[1], parts[2]);
                    taskList.addTask(event);
                } else {
                    throw new OpusUnknownCommandException("I'm sorry, but I don't know what that means.");
                }
                response = "Got it. I've added this task:\n";
                response += taskList.getTask(taskList.getSize() - 1).toString() + "\n";
                response += "Now you have " + taskList.getSize() + " tasks in the list.";
                return response;
            }
        } catch (OpusException e) {
            return e.getMessage();
        }
        return response;
    }
}
