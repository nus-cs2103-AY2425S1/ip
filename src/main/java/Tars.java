import java.util.Objects;
import java.util.Scanner;

public class Tars {

    public static String echo(String input) {
        return switch (input) {
            case "bye" -> ("Bye. Hope to see you again soon!");
            case "humour", "humour setting" -> ("100%");
            case "caution" -> ("Cooper, this is no time for caution!");
            default -> input;
        };
    }
    public static void main(String[] args) {
        System.out.println("____________________________________");
        System.out.println("Hello! I'm TARS");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;
        String output;
        do {
            input = scanner.nextLine();
            output = echo(input);
            System.out.println("____________________________________");
            System.out.println(output);
            System.out.println("____________________________________");
        } while (!input.equals("bye"));

        scanner.close();


    }
}
