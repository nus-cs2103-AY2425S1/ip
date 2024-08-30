package Tools;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm chatbot lisWhat can I do for you?");
    }

    public void close() {
        scanner.close();
    }
}
