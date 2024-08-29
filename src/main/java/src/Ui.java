package src;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String greeting = "Eh wassup la bro, my name is Wen Jie.\n What you want?\n";
        showLine();
        System.out.println(greeting);
        showLine();
    }

    public void showFarewell() {
        String farewell = "Paiseh bro I zao liao, see you around ah bro.\n";
        showLine();
        System.out.println(farewell);
        showLine();
    }

    public void showLine() {
        String line =  "____________________________________________________________\n";
        System.out.println(line);
    }

    public void showLoadingError() {
        String loadingError = "Eh bro i kena error while loading sia gg \n";
        showLine();
        System.out.println(loadingError);
        showLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

}
