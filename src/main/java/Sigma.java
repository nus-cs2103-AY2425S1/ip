import java.io.*;
import java.util.*;
public class Sigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String welcomeMessage = "Hello! I'm Sigma \nWhat can I do for you? \n";
        System.out.println(welcomeMessage);
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.contains("bye")) {
                break;
            }
            System.out.println(userInput);
        }
        System.out.println("Bye! See you soon");
    }
}
