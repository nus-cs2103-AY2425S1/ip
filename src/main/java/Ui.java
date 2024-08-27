import java.util.Scanner;

public class Ui {

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the chatbot!");
    }

    public void displayGoodbyeMessage() {
        System.out.println("Goodbye! Have a great day!");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printError(String message) {
        System.out.println("Error: " + message);
    }

}
