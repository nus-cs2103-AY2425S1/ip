package sigmabot.command;

import java.util.Scanner;

public class Greeting extends Command {
    @Override
    public void execute(Scanner sc) {
        System.out.println("Hello! Welcome to SigmaBot. How can I assist you today?");
    }

    public static void greet() {
        System.out.println("Hello! Welcome to SigmaBot. How can I assist you today?");
    }
}
