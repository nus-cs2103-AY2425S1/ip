package cheese;

import cheese.command.Command;

/**
 * Main class of chat bot
 */
public class WheelyBigCheese {
    public static final String LIST_FILE_PATH = "./data/list.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean exitChat;

    WheelyBigCheese() {
        ui = new Ui();
        assert !LIST_FILE_PATH.isBlank();
        storage = new Storage(LIST_FILE_PATH);
        exitChat = false;
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (CheeseException e) {
            ui.say(e);
            tasks = new TaskList();
        }
    }

    /**
     * Command to start bot
     * @return String greeting
     */
    public String start() {
        return ui.greet();
    }

    /**
     * Return bot response to input
     * @param input String from user
     * @return String response from bot
     */
    public String getResponse(String input) {
        String response;
        try {
            //Get user input and basic manipulation of input
            Command c = Parser.parse(input, tasks.size());
            response = c.execute(tasks, ui, storage);
            exitChat = c.isExit();
        } catch (CheeseException e) {
            exitChat = true;
            return ui.say(e);
        }
        assert !response.isBlank();
        return response;
    }

    /**
     * Run bot in command line
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

    /**
     * Getter for exitChat
     * @return boolean
     */
    public boolean isExit() {
        return exitChat;
    }

    public static void main(String[] args) {
        //Variables for bot
        WheelyBigCheese bot = new WheelyBigCheese();
        bot.run();
    }
}
