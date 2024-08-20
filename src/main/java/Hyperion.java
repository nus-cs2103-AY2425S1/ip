import java.util.Scanner;

public class Hyperion {
    public static void main(String[] args) {
        String solidLine = "_".repeat(50) + "\n";
        String greet1 = "Hello! I'm Hyperion\n";
        String greet2 = "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!";
        // greets the user
        System.out.println(solidLine + greet1 + greet2 + solidLine);
        // obtains user input
        Scanner firstScannerObj = new Scanner(System.in);
        String userInput = firstScannerObj.nextLine();
        System.out.println(solidLine + userInput + "\n" + solidLine);
        // prints out the user input
        while (!userInput.equals("bye")) {
            Scanner scannerObj = new Scanner(System.in);
            userInput = scannerObj.nextLine();
            System.out.println(solidLine + userInput + "\n" + solidLine);
        }
    }
}
