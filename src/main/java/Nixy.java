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
            ui.showNixyException(e);
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
            Parser p;
            try {
                p = new Parser(input);
                Command c = p.getCommand();
            } catch (NixyException e) {
                ui.showNixyException(e);
                continue;
            }
            switch (c) {
            case Command.BYE:
                isExit = true;
                break;
            case Command.LIST:
                ui.showList(tasks);
                break;
            case Command.MARK:
                try {
                    Task task = tasks.markTaskAsDone(p.getTaskNumber());
                    ui.showMarkedAsDone(task);
                    storage.save(tasks);
                } catch (NixyException e) {
                    ui.showNixyException(e);
                }
                break;
            case Command.UNMARK:
                try {
                    Task task = tasks.markTaskAsUndone(p.getTaskNumber());
                    ui.showMarkedAsUndone(task);
                    storage.save(tasks);
                } catch (NixyException e) {
                    ui.showNixyException(e);
                }
                break;
            case Command.DELETE:
                try {
                    Task task = tasks.deleteTask(p.getTaskNumber());
                    ui.showDeletedTask(task, tasks.getTaskCount());
                    storage.save(tasks);
                } catch (NixyException e) {
                    ui.showNixyException(e);
                }
                break;
            case Command.TODO:
            case Command.DEADLINE:
            case Command.EVENT:
                try {
                    Task task = p.getTask();
                    tasks.add(task);
                    ui.showAddedTask(task, tasks.getTaskCount());
                    storage.save(tasks);
                } catch (NixyException e) {
                    ui.showNixyException(e);
                }
                break;
            case INVALID:
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
