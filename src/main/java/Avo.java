import Commands.Command;
import Commands.CommandManager;
import Exceptions.AvoException;
import java.util.Scanner;

import Parser.CommandParser;
import UI.UI;

public class Avo {
    private final UI ui;
    private final CommandParser parser;
    private Avo(UI ui, CommandParser parser) {
        this.ui = ui;
        this.parser = parser;
    }
    private void start() {
        ui.showWelcome();
    }
    private void stop() {
        ui.showExit();
    }
    private void run() {
        try {
            listen();
        } catch (AvoException e) {
            ui.showError(e.getMessage());
            run();
        }
    }
    private void listen() throws AvoException {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readInput();
            Command command = parser.parse(userInput);
            command.execute(userInput);
            isExit = command.isExit();
        }
    }
    public static void main(String[] args) {
        CommandParser parser = new CommandParser(new CommandManager());
        Avo chatBot = new Avo(new UI(), parser);
        chatBot.start();
        chatBot.run();
        chatBot.stop();
    }
}
