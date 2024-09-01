package jbot;


import jbot.command.ByeCommand;
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
        JBot.isRunning = false;
        Ui.close();
    }

    public static void main(String[] args) {
        JBot.init();
        Ui.greetUser();
        while (JBot.isRunning) {
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