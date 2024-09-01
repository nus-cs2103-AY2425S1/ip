import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class JBot {
    private static boolean isRunning = true;

    private static TaskList taskList;

    private static void init() {
        Storage.init();
        Parser.init();
        JBot.taskList = Storage.parseData();
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

                if (command.equals(ByeCommand.getInstance())) {
                    JBot.isRunning = false;
                }

            } catch (Exception e) {
                Ui.handleError(e);
            }
        }
    }
}