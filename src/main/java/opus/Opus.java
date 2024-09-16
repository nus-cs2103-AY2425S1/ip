package opus;

/**
 * The Opus class is the main entry point of the Duke task manager application.
 * It initializes the necessary components (UI, storage, task list) and runs
 * the main loop to interact with the user, handle commands, and manage tasks.
 */
public class Opus {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initializes the Opus application with the specified file path for storage.
     * Loads tasks from storage and sets up UI and task list.
     *
     * @param filePath Path to the file where tasks are stored.
     */
    public Opus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    private String handleByeCommand() throws OpusException {
        storage.save(taskList.getTasks());
        return "Bye. Hope to see you again soon!";
    }
    private String handleListCommand() {
        String response = "";
        for (Task task : taskList.getTasks()) {
            response += task.toString() + "\n";
        }
        return response;
    }
    private String handleMarkCommand(String[] words) throws OpusException {
        if (words.length < 2) {
            throw new OpusException("Please specify the task number to mark.");
        }
        int i = Integer.parseInt(words[1]) - 1;
        assert i >= 0 && i < taskList.getSize() : "Index out of bounds: " + i;
        taskList.getTask(i).markAsDone();
        return "Nice! I've marked this task as done:\n" + taskList.getTask(i).toString();
    }
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
     * Starts the main loop of the application, interacting with the user and processing
     * commands until the user issues the "bye" command. Commands such as adding tasks,
     * marking tasks, listing tasks, and deleting tasks are handled.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String fullCommand = ui.readCommand();
            String[] words = Parser.parse(fullCommand);

            try {
                if (words[0].equals("find")) {
                    String keyword = words[1];
                    taskList.findTasks(keyword);
                } else if (words[0].equals("help")) {
                    ui.showHelp();
                } else {
                    if (words[0].equals("todo")) {
                        if (words.length <= 1) {
                            throw new OpusEmptyDescriptionException("The description of a todo cannot be empty.");
                        }
                        Task todo = new ToDo(words[1]);
                        taskList.addTask(todo);
                    } else if (words[0].equals("deadline")) {
                        String[] parts = Parser.parseDeadlineDetails(words[1]);
                        Task deadline = new Deadline(parts[0], parts[1]);
                        taskList.addTask(deadline);
                    } else if (words[0].equals("event")) {
                        String[] parts = Parser.parseEventDetails(words[1]);
                        Task event = new Event(parts[0], parts[1], parts[2]);
                        taskList.addTask(event);
                    } else {
                        throw new OpusUnknownCommandException("I'm sorry, but I don't know what that means.");
                    }
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(taskList.getTask(taskList.getSize() - 1).toString());
                    ui.showMessage("Now you have " + taskList.getSize() + " tasks in the list.");
                }
            } catch (OpusException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * Main method to launch the Opus application.
     * @param args Command-line arguments, not used.
     */
    public static void main(String[] args) {
        new Opus("data/tasks.txt").run();
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
