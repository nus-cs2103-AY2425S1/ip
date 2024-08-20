import java.util.Scanner;

public class Hyperion {
    public static void main(String[] args) {
        String solidLine = "_".repeat(50) + "\n";
        String greet1 = "Hello! I'm Hyperion\n";
        String greet2 = "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!";
        // initialize an array of size 100 to store user input
        String[] allUserInputs = new String[100];
        int count = 0;
        // greets the user
        System.out.println(solidLine + greet1 + greet2 + solidLine);
        // obtains user input and exits only when user types bye
        String input = "";
        do {
            Scanner scannerObj = new Scanner(System.in);
            input = scannerObj.nextLine();
            System.out.println(solidLine);
            if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    String index = String.format("%d. ", i + 1);
                    System.out.println(index + allUserInputs[i] + "\n");
                }
            } else {
                System.out.println("added: " + input);
                allUserInputs[count] = input;
                count++;
            }
            System.out.println(solidLine);
        } while (!input.equals("bye"));
        System.out.println(solidLine + exit + "\n" + solidLine);
    }
}
