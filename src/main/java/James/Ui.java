package james;

import java.util.Scanner;

class Ui {
    private final Scanner SCANNER;

    public Ui() {
        SCANNER = new Scanner(System.in);
    }

    public void showGreeting() {
        System.out.println("Hello Big Boy! I'm James\nHow can I assist you today?\n");
    }

    public void showExitMessage() {
        System.out.println("Goodbye. Come back anytime!\n");
    }

    public String readCommand() {
        System.out.print("> ");
        return SCANNER.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void close() {
        SCANNER.close();
    }
}
