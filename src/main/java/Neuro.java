import java.util.Scanner;

public class Neuro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("___________________________________________________");
        System.out.println("Hi, I'm Neuro, your chatbot assistant!");
        System.out.println("How can I help you?");
        System.out.println("___________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println("___________________________________________________");
                System.out.println(input);
                System.out.println("___________________________________________________");
            }
        }

        System.out.println("___________________________________________________");
        System.out.println("Goodbye!");
        System.out.println("___________________________________________________");
    }
}
