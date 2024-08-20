import java.util.Scanner;

public class Hyperion {
    public static void main(String[] args) {
        String solidLine = "_".repeat(50) + "\n";
        String greet1 = "Hello! I'm Hyperion\n";
        String greet2 = "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!";
        // greets the user
        System.out.println(solidLine + greet1 + greet2 + solidLine);
        // obtains user input and exits only when user types bye
        String input = "";
        do {
            Scanner scannerObj = new Scanner(System.in);
            input = scannerObj.nextLine();
            System.out.println(solidLine + input + "\n" + solidLine);
        } while (!input.equals("bye"));
    }
}
