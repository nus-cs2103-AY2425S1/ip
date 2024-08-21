package tina;

public class Tina {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;
    public Tina(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }
    public void run() {
        ui.greet();
        tasks = new TaskList(storage);
        ui.doTask(tasks);
        ui.exit();
    }
    public static void main(String[] args) {
        new Tina("./src/main/java/tina.txt").run();
    }
}
