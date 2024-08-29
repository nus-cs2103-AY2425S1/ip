
public class Garfield {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Garfield(String saveFilePath){
        this.ui = new Ui();
        this.storage = new Storage(saveFilePath);
        this.taskList = new TaskList(this.storage.load());
    }

    public void run() {
        this.ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (GarfieldException e) {
                this.ui.showError(e.getMessage());
            }
        }
        this.ui.showEnding();
    }

    public static void main(String[] args) {
        new Garfield("./data/save.txt").run();
    }
}