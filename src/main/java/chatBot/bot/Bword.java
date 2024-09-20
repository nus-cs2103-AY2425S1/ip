package chatbot.bot;

import chatbot.command.Command;

/**
 * Bword is a chatbot that helps with planning tasks
 *
 * The constructor method takes in the location of the file to read and write data
 * as a String, and has respective attributes of storage, tasklist and ui
 *
 * The run method loops continuously until a command to exit is detected.
 *
 * The main method initialises the program
 */

public class Bword {
    private static final String FILELOCATION = "./data/bword.txt";
    //public static final String HLINE = "____________________________________________________________\n";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /** Constructor */
    public Bword(String s) {
        this.ui = new Ui();
        this.storage = new Storage(s);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    private void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (Exception e) {
                this.ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            String fullCommand = input;
            //this.ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            if (c != null) {
                output = c.execute(this.taskList, this.ui, this.storage);
                // output = "Successfully executed: " + fullCommand;
            } else {
                output = "commands accepted: todo , deadline ,"
                        + " event , list, mark , unmark , bye , delete, find";
            }
        } catch (Exception e) {
            this.ui.showError(e.getMessage());
        } finally {
            this.ui.showLine();
        }
        return output;
    }

    public static void main(String[] args) {
        Bword bot = new Bword(FILELOCATION);
        bot.run();
    }
}
