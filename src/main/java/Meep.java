import java.util.Scanner;

public class Meep {
    public static void main(String[] args) {
        System.out.println("""
                -----------------------------------------
                Hello! I'm Meep
                What can I do for you?
                -----------------------------------------
                """);
        Scanner scanner = new Scanner(System.in);
        // keep scanning for user input
        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("""
                        -----------------------------------------
                        Meep: Bye! Have a great day!
                        -----------------------------------------
                        """);
                break;
            } else {
                System.out.println("-----------------------------------------\n" +
                        "Meep: " +
                        input + "\n" +
                        "-----------------------------------------");
            }
        }
    }
}
