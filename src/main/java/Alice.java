import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Alice {
    private TaskList list;
    private final Storage storage;
    private static final String path = "./data/alice.txt";
    private Ui ui;
    private Parser parser;

    public Alice(String path) {
        this.storage = new Storage(path);
        this.list = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(ui, list, storage);
        ArrayList<Task> loadedTasks = storage.load();
        for (Task task : loadedTasks) {
            list.addToList(task);
        }
    }

    public static void main(String[] args) {
        Alice alice = new Alice(path);
        Scanner scanner = new Scanner(System.in);
        alice.ui.greet();
        boolean isOnline = true;
        while (isOnline) {
            isOnline = alice.parser.performAction(scanner.nextLine().trim());
        }
        scanner.close();
    }
}
