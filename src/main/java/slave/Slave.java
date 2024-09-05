package slave;

import java.util.LinkedList;

/**
 * It is the main class for the program
 */
public class Slave {
    private static final LinkedList<Task> list = new LinkedList<>();
    private Storage storage;
    private Ui ui;

    /**
     * creates a new Slave object which will save user interactions to
     * the specified file path
     *
     * @param filePath is the address of the save file
     */
    public Slave(String filePath) {
        storage = new Storage(list, filePath);
        ui = new Ui(list);
        storage.load();
    }

    /**
     * runs the program
     */
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

    public static void main(String[] args) {
        String filePath = "./src/main/data/savefile.txt";
        Slave slave = new Slave(filePath);
        slave.run();
    }
}
