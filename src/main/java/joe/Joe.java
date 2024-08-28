package joe;

import joe.controller.Controller;
import joe.parser.Parser;
import joe.ui.Ui;

public class Joe {
    public static final String CHATBOT_NAME = "Joe";

    public static String input = "";

    private Controller controller;
    private Parser parser;
    private Ui ui;

    public Joe() {
        this.ui = new Ui(CHATBOT_NAME);
        this.controller = new Controller(ui);
        this.parser = new Parser(controller, ui);
    }

    public void run() {
        controller.startProgram();
        boolean isRunning = true;
        while (isRunning) {
            isRunning = parser.parse();
        }
        controller.endProgram();
    }

    public static void main(String[] args) {
        new Joe().run();
    }
}
