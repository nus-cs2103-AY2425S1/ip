package milutrock;

import milutrock.exceptions.UnknownCommandException;

/**
 * A task management program that interacts with users through a
 * command-line interface.
 */
public class MiluTrock {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    public MiluTrock() {
        this.taskList = new TaskList();
        this.ui = new Ui("MiluTrock", this.taskList);
        this.parser = new Parser(this.taskList, this.ui);
        this.storage = new Storage("./data.txt", this.parser);
    }

    /**
     * Initialize the milutrock class.
     */
    public String initializeAndGetBanner() {
        this.storage.loadTasks();

        return this.ui.printBanner();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        this.storage.storeCommand(input);
        
        try {
            return this.parser.parseCommand(input);
        } catch (UnknownCommandException e) {
            return e.getMessage();
        }
    }
}
