package src;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String greeting = "Eh wassup la bro, my name is Wen Jie.\n What you want?";
        showLine();
        System.out.println(greeting);
        showLine();
    }

    public void showFarewell() {
        String farewell = "Paiseh bro I zao liao, see you around ah bro.";
        showLine();
        System.out.println(farewell);
        showLine();
    }

    public void showLine() {
        String line =  "____________________________________________________________";
        System.out.println(line);
    }

    public void showError(String errorMessage) {
        showLine();
        System.out.println(errorMessage);
        showLine();
    }

    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }

}
