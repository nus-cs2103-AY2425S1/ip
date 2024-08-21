import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String name = "Bob";
        System.out.println("Hello! I'm " + name + "!\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            String endKeyword = "bye";

            if (userInput.equalsIgnoreCase(endKeyword)) {
                System.out.println("Bye! Hope to see you again :)");
                break;
            }

            // Echo response
            System.out.println(userInput);
        }

        scanner.close();
    }
}
