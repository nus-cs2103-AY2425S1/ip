import java.util.Scanner;
public class YourHelperBuddy {
    public static void main(String[] args) {
        System.out.println("Hello! I'm YourHelperBuddy.");
        System.out.println("How may I assist you today?");
        System.out.println("________________________________________________");
        Scanner myObj = new Scanner(System.in);
        // User enters commands until he types bye
        System.out.println("Command: ");
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("________________________________________________");
            System.out.println("     " + userInput);
            System.out.println("________________________________________________");
            System.out.println("Command: ");
            userInput = myObj.nextLine();
        }
        System.out.println("________________________________________________");
        System.out.println("Goodbye. Take care and see you again!");
        System.out.println("________________________________________________");
    }
}
