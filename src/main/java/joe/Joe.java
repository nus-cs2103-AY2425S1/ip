package joe;

import joe.controller.Controller;
import joe.parser.Parser;
import joe.ui.Ui;

public class Joe {
    public static final String CHATBOT_NAME = "Joe";

    private Controller controller;
    private Parser<Controller> parser;
    private Ui ui;

    public Joe() {
        this.ui = new Ui(CHATBOT_NAME);
        this.controller = new Controller(ui);
        this.parser = new Parser<>(controller, ui);
    }

    public Controller getController() {
        return controller;
    }

    public Parser<Controller> getParser() {
        return parser;
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Generates a response for the user's chat message.
     * 
     * @param input
     *            The user's chat message.
     */
    public String getResponse(String input) {
        return "Joe heard: " + input;
    }
}
