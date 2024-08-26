package blob;

import java.io.IOException;

public class Blob {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Blob(String filePath) {
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(this.storage);
        this.ui = new Ui(this.tasklist);
    }

    public void run() {
        this.ui.initialise();
        this.ui.converse();
    }


    public static void main(String[] args) throws IOException {
        new Blob("./src/main/java/database.csv").run();
    }
}
