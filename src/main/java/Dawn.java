import java.util.Scanner;

public class Dawn {
    public static void main(String[] args) {
        String logo = "Dawn 🌙";
        String divider = "--".repeat(30);
        Scanner scanner = new Scanner(System.in);

        System.out.println(divider);
        System.out.printf("%s speaking, what can I do for you? \n", logo);
        respond(scanner);
        System.out.println(divider);
    }

    private static void respond(Scanner scanner) {
        String input = scanner.nextLine();
        if (input.equals("bye")) {
            System.out.println("Byeeee~ nice chatting with you! See you next time, tschüss :)");
        } else {
            System.out.println(input);
            respond(scanner);
        }
    }
}
