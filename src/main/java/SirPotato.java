import java.util.Scanner;  // For user inputs

public class SirPotato {

    private Scanner scanner;
    private String logo = "Sir Potato";
    private String horizontal_line = "___________________________ \n";
    private String indent = "   ";

    public SirPotato() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do ya for?\n" + horizontal_line + "\n");
    }

    public void displayByeMessage() {
        System.out.println("Bye mate, see you around.");
    }

    public void startChat() {
        displayWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(horizontal_line + "\n" + indent + "Bye. Thanks mate." + "\n" + horizontal_line + "\n");
                return;
            } else {
                System.out.println(horizontal_line + "\n" + indent + userInput + "\n" + horizontal_line + "\n");
            }
        }
    }

    public static void main(String[] args) {
        
        SirPotato potato = new SirPotato();
        potato.startChat();
    }
}