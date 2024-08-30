package sigmabot.command;

import java.util.Scanner;

public class Terminate extends Command {
    @Override
    public void execute(Scanner sc) {
        System.out.println("Goodbye!");
    }
}
