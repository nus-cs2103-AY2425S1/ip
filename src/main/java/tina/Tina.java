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

    public String getResponse(String input) {
        return ui.runInput(tasks, input);
    }
}
