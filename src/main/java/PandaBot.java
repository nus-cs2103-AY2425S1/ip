import java.io.IOException;

/**
 * PandaBot is a simple task management bot that allows users to add, list, mark, unmark,
 * and delete tasks. It supports different types of tasks including ToDos, Deadlines, and Events.
 */
public class PandaBot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public PandaBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.loadTaskList());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tempTasks = new TaskList();
        }
        this.tasks = tempTasks;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (InputException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Error saving tasks: " + e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new PandaBot("./data/PandaBot.txt").run();
    }
}
