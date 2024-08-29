import java.io.IOException;

public class Agave {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isRunning;

    public Agave(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isRunning = true;
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        while (isRunning) {
            try {
                String userInput = ui.getUserInput("Enter a command: ");
                Parser parser = new Parser(userInput);
                String command = parser.getCommand();

                if (command.equals("bye")) {
                    isRunning = false;
                    ui.showBye();
                } else if (command.equals("list")) {
                    ui.showTasks(tasks.getTasks());
                } else if (command.equals("mark")) {
                    tasks.markTask(parser.getTaskNumber());
                    ui.showMarkedTask(tasks.getTask(parser.getTaskNumber() - 1));
                } else if (command.equals("unmark")) {
                    tasks.unmarkTask(parser.getTaskNumber());
                    ui.showUnmarkedTask(tasks.getTask(parser.getTaskNumber() - 1));
                } else if (command.equals("todo")) {
                    tasks.addTask(parser.parseTodo());
                    ui.showTaskAdded(tasks.getLastTask(), tasks.size(), tasks.getTasks());
                } else if (command.equals("deadline")) {
                    tasks.addTask(parser.parseDeadline());
                    ui.showTaskAdded(tasks.getLastTask(), tasks.size(), tasks.getTasks());
                } else if (command.equals("event")) {
                    tasks.addTask(parser.parseEvent());
                    ui.showTaskAdded(tasks.getLastTask(), tasks.size(), tasks.getTasks());
                } else if (command.equals("delete")) {
                    tasks.deleteTask(parser.getTaskNumber());
                } else {
                    throw new AgaveException("I'm sorry, but I don't understand the command: " + userInput);
                }
                storage.saveTasks(tasks.getTasks());
            } catch (AgaveException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Agave("./data/agave.txt").run();
    }
}