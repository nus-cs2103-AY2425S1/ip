import java.util.Objects;
import java.util.Scanner;

public class Mediell {
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Mediell!");
        System.out.println("What can I do for you? :)");
        printLine();
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (true) {
            System.out.println("");
            message = scanner.next();
            if (Objects.equals(message, "bye")) {
                break;
            }
            System.out.println(message);
            printLine();
        }

        System.out.println("Bye. Hope to see you again soon! :(");
        printLine();
    }

    private static void printLine() {
        System.out.println("-------------------------------------------");
    }
}
