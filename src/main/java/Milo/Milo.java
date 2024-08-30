package Milo;

/*
* Represents the task bot programme
* containing components such as
* Storage, TaskList, Ui
 */
public class Milo {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /*
    * Initialise Milo bot and its Ui, Storage, TaskList field
     */
    public Milo() {
        this.ui = new Ui();
        this.storage = new Storage("./src/data/miloData.txt");
        // Reads data from storage
        this.tasks = new TaskList(storage.readData());

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
            Parser.readInput(input,this.tasks.getList());
            // Save data to storage
        } while (!input.toLowerCase().strip().equals("bye"));
        storage.saveData(this.tasks.getList());
    }


}
