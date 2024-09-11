package froggy;

public class Froggy {
    private static final String FILE_PATH = "./data/taskList.txt";
    private Storage storage;
    private TaskList tasks;
    //private TaskList previousTasks;
    private Ui ui;
    private Parser parser;

    public Froggy(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        //previousTasks = new TaskList(storage.loadTasks());
        ui = new Ui();
        parser = new Parser();
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            Command c = parser.parse(command);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        ui.showExit();
        storage.saveTasks(tasks.getTasks());
        ui.close();
    }

    public String getGreeting() {
        return ui.getGreeting();
    }

    /**
     * Generates a response for user's chat message
     */
    public String getResponse(String input) {
        Command c = parser.parse(input);
        return c.executeAndGetOutput(tasks, ui, storage);
        /*if (c instanceof UndoCommand) {
            return c.executeAndGetOutput(tasks, ui, storage);
        } else {
            //previousTasks = new TaskList(storage.loadTasks());
            return c.executeAndGetOutput(tasks, ui, storage);
        }*/
    }

    public static void main(String[] args) {
        new Froggy("./data/taskList.txt").run();
    }
}
