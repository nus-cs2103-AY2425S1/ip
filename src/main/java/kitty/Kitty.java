package kitty;

/**
 * Provides an interactive ChatBot named Kitty.
 */
public class Kitty {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Kitty() {
        tasks = new TaskList();
        storage = new Storage();
        ui = new Ui(tasks, storage);
    }

    /**
     * Starts the ChatBot.
     *
     * @param args Program input.
     */
    public static void main(String[] args) {
        new Kitty().run();
    }

    public void run() {
        ui.run();
    }

}
