package Gutti;

public class Gutti {

    private static final String FILE_PATH = "./data/gutti.txt";
    public TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Gutti() {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        tasks = new TaskList();
    }

    /**
     * The entry point of the program.
     * @param args (not used)
     */
    public static void main(String[] args) {
        new Gutti().run();
    }

    public void run() {
        boolean isExit = false;
        ui.greetings();
        storage.loadTasksFromFile(tasks);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (GuttiException e) {
                ui.generateError(e);
            }
        }
        ui.goodBye();
    }
}