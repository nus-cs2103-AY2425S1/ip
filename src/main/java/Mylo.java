import java.util.Objects;
import java.util.Scanner;

public class Mylo {
    private static void separator() {
        System.out.println("--------------------------------");
    }
    public static void main(String[] args) {
        String name = "Mylo";
        String greet = "Hello! Thanks for using " + name + ".";
        String opening_query = "What can I help you?";
        String goodbye = "Goodbye. Have a nice day ahead!";

        System.out.println(greet);
        System.out.println(opening_query);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while(!Objects.equals(input, "bye")) {
            separator();
            System.out.println(input);
            separator();
            input = scanner.next();
        }

        separator();
        System.out.println(goodbye);
        separator();
    }
}
