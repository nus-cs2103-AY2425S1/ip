package joe;

import joe.controller.Controller;
import joe.parser.Parser;
import joe.ui.Ui;
import java.util.Scanner;

public class Joe {
    public static final String CHATBOT_NAME = "Joe";

    public static String input = "";

    private Controller controller;
    private Parser<Controller> parser;
    private Ui ui;
    private Scanner scanner;

    public Joe() {
        this.ui = new Ui(CHATBOT_NAME);
        this.controller = new Controller(ui);
        this.parser = new Parser<>(controller, ui);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        controller.startProgram();
        boolean isRunning = true;
        while (isRunning) {
            isRunning = parser.parse(scanner.nextLine());
        }
        controller.endProgram();
        scanner.close();
    }

    public static void main(String[] args) {
        new Joe().run();
    }
}
