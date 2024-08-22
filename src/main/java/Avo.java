import Commands.CommandManager;
import Tasks.*;
import Exceptions.AvoException;

import java.util.Objects;
import java.util.Scanner;

public class Avo {
    private final CommandManager manager;
    private Avo() {
        manager = new CommandManager();
    }
    private void start() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    private void stop() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    private void run() {
        try {
            listen();
        } catch (AvoException e) {
            System.out.println(e.getMessage());
            run();
        }
    }
    private void listen() throws AvoException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String command = userInput.split(" ")[0];
            if (command.equals("exit")) break;
            manager.getCommand(command).execute(userInput);
        }
    }
    public static void main(String[] args) {
        Avo chatBot = new Avo();
        chatBot.start();
        chatBot.run();
        chatBot.stop();
    }
}
