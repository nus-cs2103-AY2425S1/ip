package monique;//imports for user input
import monique.command.Command;
import monique.exception.MarkException;
import monique.exception.MoniqueException;
import monique.exception.ParseException;
import monique.exception.UnknownCommandException;
import monique.parser.Parser;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

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
