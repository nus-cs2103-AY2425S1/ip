package daniel;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import Storage.Storage;
import Task.TaskList;
import Ui.Ui;
import Parse.Parse;

public class Daniel {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Daniel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        File folder = new File("./data");
        if (folder.exists()) {
            storage.createFile();
            this.tasks = new TaskList(storage.loadFile());
        } else {
            storage.createFolder();
            storage.createFile();
            this.tasks = new TaskList(new ArrayList<>());
        }
    }
    public void run() {
        ui.uiGreet();
        Scanner scanner = new Scanner(System.in);
        boolean val = true;
        while(val){
            String input = scanner.nextLine();
            val = Parse.initialParse(input, ui, tasks, storage);
        }
        storage.writeFile(tasks.getArray());
    }

    public static void main(String[] args) {
        new Daniel("data/daniel.txt").run();
    }
}
