package tissue;

public class Tissue {
    private final Ui ui;

    public Tissue(String filePath, String fileName) {
        Storage storage = new Storage(filePath, fileName);
        this.ui = new Ui(new Parser(), new TaskList(storage.load()), storage);
    }

    public static void main(String[] args) {
        new Tissue("./data/", "tissue.csv").run();
    }

    private void run() {
        ui.run();
    }
}
