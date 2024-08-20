package hoodini;

/**
 * Class which handles the main configurations
 * of the Chatbot
 */

public class Hoodini {
    private Storage store;
    private Parser parser;
    private Ui ui;

    /**
     * Constructor to start the Hoodini chatbot, takes no parameters.
     */

    public Hoodini() {
        this.ui = new Ui();
        this.store = new Storage(ui);

        start();


    }


    private void start() {
        ui.showWelcome();
        this.parser = new Parser(store, ui);
        parser.load();
        parser.handleInput();

    }




}
