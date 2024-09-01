package jbot;


import jbot.command.JBotCommand;
import jbot.util.Parser;
import jbot.util.Storage;
import jbot.util.Ui;

public class JBot {
    private static boolean isRunning = true;
    private static void init() {
        Storage.init();
        Parser.init();
        Storage.parseData();
    }

    public static void close() {
        isRunning = false;
        Ui.close();
    }

    @SuppressWarnings("FeatureEnvy")
    public static void main(String[] args) {
        init();
        Ui.greetUser();
        while (isRunning) {
            try {
                String userInput = Ui.readInput();
                JBotCommand command = Parser.parse(userInput);
                Ui.display(command, userInput);
                Storage.updateData();

            } catch (Exception e) {
                Ui.handleError(e);
            }
        }
    }
}