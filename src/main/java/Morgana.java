public class Morgana {
    private static final String NAME = "Morgana";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Morgana(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (MorganaException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage(NAME);
        boolean isExit = false;
        while (!isExit) {
            try {
                String line = ui.getUserInput();
                Command command = Parser.parse(line);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MorganaException e) {
                ui.showToUser(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Morgana("./data/morgana.txt").run();
    }
}
