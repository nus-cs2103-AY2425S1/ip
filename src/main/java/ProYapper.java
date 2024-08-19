import java.util.Scanner;

public class ProYapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greeting = "Hello! I am Pro Yapper!\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(greeting);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\n" + goodbye);
                break;
            } else {
                System.out.println("\n" + userInput + "\n");
            }
        }
        scanner.close();
    }
}

