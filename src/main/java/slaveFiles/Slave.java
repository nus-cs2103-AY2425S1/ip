package slaveFiles;

import java.util.LinkedList;

public class Slave {
    private static final LinkedList<Task> list = new LinkedList<>();
    private Storage storage;
    private Ui ui;

    public Slave(String filePath) {
        storage = new Storage(list, filePath);
        ui = new Ui(list, storage);
        storage.load();
    }

    public void run() {
        ui.welcome();
        ui.getUserInputs(storage);
        ui.goodbye();
    }

    public static void main(String args[]) {
        String FILE_PATH = "./src/main/data/savefile.txt";
        Slave slave = new Slave(FILE_PATH);
        slave.run();
    }
}
