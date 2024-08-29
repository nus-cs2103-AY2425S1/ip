public class Bimo {
    public static String NAME = "Bimo";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Bimo(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (BimoException e) {
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.greetUser(NAME);
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.getUserCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isRunning = !c.getIsQuit();
            } catch (BimoException e) {
                ui.showErrorMessage();
            } finally {
                ui.showLine();
            }
        }

    }
    public static void main(String[] args) {
        new Bimo("ip/data/Bimo.txt").run();
    }
}

