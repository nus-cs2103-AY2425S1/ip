import java.util.Scanner;  // For user inputs
import java.util.ArrayList; //To create the to-do list

public class SirPotato {

    private Scanner scanner;
    private String logo = "Sir Potato";
    private String horizontal_line = "___________________________ \n";
    private String indent = "   ";
    private ArrayList<String> toDoList;

    public SirPotato() {
        this.scanner = new Scanner(System.in);
        this.toDoList = new ArrayList<String>();
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
            } else if (userInput.equals("list")) {
                System.out.println(horizontal_line + "\n");
                System.out.println(indent + "List: ");
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println(indent + (i+1) + ". " + toDoList.get(i));
                }
                System.out.println(horizontal_line + "\n");
            } else {
                toDoList.add(userInput);
                System.out.println(horizontal_line + "\n" + indent + 
                            "Added to list: " + userInput + "\n" + horizontal_line + "\n");
            }
        }
    }

    public static void main(String[] args) {
        
        SirPotato potato = new SirPotato();
        potato.startChat();
    }
}