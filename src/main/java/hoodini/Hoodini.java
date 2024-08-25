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

    /**
     * Method to start chatbot
     */

    public void start() {

        this.parser = new Parser(store, ui);
        parser.load();


    }

    public String handleInput(String input) {
        return parser.handleInput(input);
    }






}
