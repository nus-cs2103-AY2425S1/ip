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
                if (words[0].equals("bye")) {
                    storage.save(taskList.getTasks());
                    ui.showGoodbye();
                    break;
                } else if (words[0].equals("list")) {
                    taskList.listTasks();
                } else if (words[0].equals("mark")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    taskList.getTask(i).markAsDone();
                    ui.showMessage("Nice! I've marked this task as done:");
                    ui.showMessage(taskList.getTask(i).toString());
                } else if (words[0].equals("delete")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    ui.showMessage("Noted. I've removed this task:");
                    ui.showMessage(taskList.getTask(i).toString());
                    taskList.removeTask(i);
                    ui.showMessage("Now you have " + taskList.getSize() + " tasks in the list.");
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
     * 
     * @param args Command-line arguments, not used.
     */
    public static void main(String[] args) {
        new Opus("data/tasks.txt").run();
    }
}
