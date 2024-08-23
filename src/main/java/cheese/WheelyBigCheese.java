package cheese;

import cheese.command.Command;

/**
 * Main class of chat bot
 */
public class WheelyBigCheese {
    public static final String LIST_FILE_PATH = "./list.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    WheelyBigCheese() {
        ui = new Ui();
        storage = new Storage(LIST_FILE_PATH);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (CheeseException e) {
            ui.say(e);
            tasks = new TaskList();
        }
    }

    /**
     * Central logic for bot
     */
    public void run() {
        ui.greet();
        boolean exitChat = false;

        //Main logic for bot
        do {
            try {
                //Get user input and basic manipulation of input
                String input = ui.readCommand();
                Command c = Parser.parse(input, tasks.size());
                c.execute(tasks, ui, storage);
                exitChat = c.isExit();
            } catch (CheeseException e) {
                ui.say(e);
            }
        } while (!exitChat);
    }

    public static void main(String[] args) {
        //Variables for bot
        WheelyBigCheese bot = new WheelyBigCheese();
        bot.run();
    }
}
