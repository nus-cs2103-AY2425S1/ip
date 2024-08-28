//imports for user input
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Set;

public class Monique {
    //Create array to store tasks
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    //Constructor for Monique class
    public Monique(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadData());
    }

    public void run() {
        ui.showWelcome();
        boolean isActive = true;
        ui.showLine();

        while (isActive) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList,ui,storage);
                isActive = c.isActive();
            } catch (MoniqueException me) {
                me.advice();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException, MarkException, ParseException, UnknownCommandException, FileNotFoundException {
        new Monique("data/tasks.txt").run();
    }
}
