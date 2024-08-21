import java.util.Scanner;

public class Darkpool {
    public static void main(String[] args) {
        final String greeting = "it’s darkpool. what twisted reason dragged me into your misery?";
        final String bye = "contact me again and you'll regret it.";

        output(greeting);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "bye" -> {
                    output(bye);
                    scanner.close();
                    return;
                }
                default -> output(userInput);
            }
        }

    }

    private static void output(String input) {
        System.out.println("\u001B[31m\t—————————————————————————————————————————————————————————————————\n\t" + input + "\n" + "\t—————————————————————————————————————————————————————————————————\u001B[0m");
    }
}
