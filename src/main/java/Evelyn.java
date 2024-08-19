import java.util.Objects;
import java.util.Scanner;
public class Evelyn {
    private static String chatbotName = "Evelyn";
    private static String horizontalLine = "-----------------------------------------";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = null;
        System.out.println(horizontalLine);
        System.out.println("Hi! I am Evelyn");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        while (!(Objects.equals(text, "bye"))) {
            text = scanner.nextLine();
            System.out.println(horizontalLine);
            System.out.println(text);
            System.out.println(horizontalLine);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
