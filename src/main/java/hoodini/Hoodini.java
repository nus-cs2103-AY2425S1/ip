package hoodini;

/**
 * Handles the main configurations
 * of the Chatbot
 */

public class Hoodini {
    private Storage store;
    private Parser parser;
    private Ui ui;

    /**
     * Starts the Hoodini chatbot, takes no parameters.
     */

    public Hoodini() {
        this.ui = new Ui();
        this.store = new Storage(ui);
        start();




    }

    /**
     * Starts the chatbot
     */

    public void start() {

        this.parser = new Parser(store, ui);
        parser.load();


    }

    public String handleInput(String input) {
        return parser.handleInput(input);
    }






}
