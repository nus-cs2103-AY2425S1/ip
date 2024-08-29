public class Nixy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Nixy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NixyException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Main driver for Nixy
     * Entry point to display welcome message and read user input.
     */
    public void run() {
        ui.showWelcome();
        Boolean isExit = false;
        while (!isExit) {
            String input = readInput();
            Parser p =  Parser.of(input);
            Command c = p.getCommand();
            switch (c) {
            case BYE:
                isExit = true;
                break;
            case LIST:
                ui.showList(tasks);
                break;
            case MARK:
                try {
                    Task task = tasks.markTaskAsDone(p.getTaskNumber());
                    ui.showMarkedAsDone(task);
                    storage.save(tasks);
                } catch (NixyException e) {
                    ui.showNixyException(e);
                }
                break;
            case UNMARK:
                try {
                    Task task = tasks.markTaskAsUndone(p.getTaskNumber());
                    ui.showMarkedAsUndone(task);
                    storage.save(tasks);
                } catch (NixyException e) {
                    ui.showNixyException(e);
                }
                break;
            case DELETE:
                try {
                    Task task = tasks.deleteTask(p.getTaskNumber());
                    ui.showDeletedTask(task, tasks.size());
                    storage.save(tasks);
                } catch (NixyException e) {
                    ui.showNixyException(e);
                }
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    Task task = p.getTask();
                    tasks.add(task);
                    ui.showAddedTask(task, tasks.size());
                    storage.save(tasks);
                } catch (NixyException e) {
                    ui.showNixyException(e);
                }
                break;
            case UNKNOWN:
                ui.showUnknownWarning();
                break;
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Nixy("data/tasks.txt").run();
    }
}
