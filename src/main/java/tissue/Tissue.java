package tissue;

public class Tissue {
    private final Ui ui;

    public Tissue(String filePath) {
        Storage storage = new Storage(filePath);
        this.ui = new Ui(new Parser(), new TaskList(storage.load()), storage);
    }

    public static void main(String[] args) {
        new Tissue("./data/").run();
    }

    public void run() {
        ui.run();
    }
}
