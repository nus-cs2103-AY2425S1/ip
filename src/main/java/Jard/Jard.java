package Jard;

import java.util.List;

/**
 * The Jard class represents the main application that interacts with the user.
 * It handles the core functionality of the chatbot, including task management,
 * user input processing, and file storage.
 */
public class Jard {
    private Storage storage;
    private List<Task> tasks;
    private Ui ui;

    /**
     * Constructs a Jard instance with the specified file path for task storage.
     *
     * @param filePath The path of the file where tasks will be loaded from and saved to.
     */
    public Jard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.loadTasks();
    }

    /**
     * Starts the Jard chatbot application, displaying the welcome message and
     * entering the main loop to process user commands.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String input = ui.readCommand();
            String[] inputParts = Parser.parseCommand(input);
            String command = inputParts[0];

            try {
                if (command.equals("bye")) {
                    ui.showBye();
                    break;
                } else if (command.equals("list")) {
                    ui.showTaskList(tasks);
                } else if (command.equals("mark")) {
                    int taskIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task task = tasks.get(taskIndex);
                    task.markAsDone();
                    ui.showMarkTask(task);
                } else if (command.equals("unmark")) {
                    int taskIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task task = tasks.get(taskIndex);
                    task.markAsNotDone();
                    ui.showUnmarkTask(task);
                } else if (command.equals("delete")) {
                    int taskIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task task = tasks.remove(taskIndex);
                    ui.showDeleteTask(task, tasks.size());
                } else {
                    Task task = Parser.parseTask(input);
                    tasks.add(task);
                    ui.showAddTask(task, tasks.size());
                }
                storage.saveTasks(tasks);
            } catch (JardException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main method serves as the entry point for the Jard chatbot application.
     *
     * @param args Command line arguments, if any.
     */
    public static void main(String[] args) {
        new Jard("./data/jard.txt").run();
    }
}
