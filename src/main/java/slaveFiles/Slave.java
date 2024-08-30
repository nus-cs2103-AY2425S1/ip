package slaveFiles;

import java.util.LinkedList;

public class Slave {
    private static final LinkedList<Task> list = new LinkedList<>();
    private Storage storage;
    private Ui ui;

    public Slave(String filePath) {
        storage = new Storage(list, filePath);
        ui = new Ui(list);
        storage.load();
    }

    public void run() {
        ui.welcome();
        boolean hasMoreInputs;
        do {
            Pair<Boolean, Boolean> result = ui.getUserInputs();
            if (result.getSecond()) {
                storage.save();
            }
            hasMoreInputs = result.getFirst();
        } while (hasMoreInputs);
        ui.goodbye();
    }

    public static void main(String args[]) {
        String FILE_PATH = "./src/main/data/savefile.txt";
        Slave slave = new Slave(FILE_PATH);
        slave.run();
    }
}
