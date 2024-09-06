package Milo;

import Milo.Parser.Parser;
import Milo.Storage.Storage;
import Milo.Tasks.TaskList;
import Milo.Ui.Ui;

/*
* Represents the task bot programme
* containing components such as
* Storage, TaskList, Ui
 */
public class Milo {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /*
    * Initialise Milo bot and its Ui, Storage, TaskList field
     */
    public Milo() {
        this.ui = new Ui();
        this.storage = new Storage("./src/data/miloData.txt");
        // Reads data from storage
        this.tasks = new TaskList(storage.readData());
        this.parser = new Parser(this.ui);
    }

    /*
    * Running Milo
     */
    public static void main(String[] args) {
        new Milo().run();
    }














    public void run() {
        ui.greetUser();
        runCommandLoopTilBye();
        ui.byeUser();
    }

    // Loop for user input
    public void runCommandLoopTilBye() {
        String input;
        do {
            input = ui.getUserInput();
            parser.readInput(input,this.tasks);
            // Save data to storage
        } while (!input.toLowerCase().strip().equals("bye"));
        storage.saveData(this.tasks);
    }


}
