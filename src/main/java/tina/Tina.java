package tina;

public class Tina {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Tina(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    public void run() {
        ui.greet();
        tasks = new TaskList(storage);
        ui.doTask(tasks);
        ui.exit();
    }

    public String getResponse(String input) {
        return ui.runInput(tasks, input);
    }

    public static void main(String[] args) {
        new Tina("./data/tina.txt").run();
    }
}
